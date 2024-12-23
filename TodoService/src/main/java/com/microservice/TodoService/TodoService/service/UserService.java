package com.microservice.TodoService.TodoService.service;

import com.microservice.TodoService.TodoService.entity.Todo;
import com.microservice.TodoService.TodoService.entity.User;
import com.microservice.TodoService.TodoService.repositories.TodoRepository;
import com.microservice.TodoService.TodoService.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactoryFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * @Author : Samyam Kafle
 * @Portfolio : https://samyamkafle.com.np
 * @Project : microserviceDemo
 * @CreatedDate : 22/12/2024, Sunday
 **/
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;
    public User register(User user){
        return userRepository.save(user);
    }

    public String verifyUser(User user) {

        User existingUser = userRepository.findByUsername(user.getUsername());
        log.info("Got User {}", existingUser);
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Authentication Successful";
        }

        return "Authentication Failed";
    }

    public List<Todo> getTodoList(Authentication authentication) {
        return getUserInfo(authentication.getName()).getTodos();
    }

    public Todo createTodo(Authentication authentication, Todo todo) {
        User user = getUserInfo(authentication.getName());
        todo.setUser(user);
        user.getTodos().add(todo);
        return todoRepository.save(todo);
    }
    public Todo editTodo(Authentication authentication, Long todoId, Todo updatedTodo) {
        User currentUser = getUserInfo(authentication.getName());
        Todo toUpdateTodo = todoRepository.findById(todoId).orElseThrow(
                () -> new RuntimeException("Todo not Found")
        );
        // verify if the logged in user is authenticated to update the todo information
        if(!toUpdateTodo.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not authorized to update this Info!!");
        }
        toUpdateTodo.setNote(updatedTodo.getNote());
        toUpdateTodo.setTitle(updatedTodo.getTitle());
        return todoRepository.save(toUpdateTodo);
    }

    public String deleteTodo(Authentication authentication, Long todoId) {
        User currentUser = getUserInfo(authentication.getName());
        Todo currentTodoRecord = todoRepository.findById(todoId).orElseThrow(
                () -> new RuntimeException("Invalid Id!")
        );
        if(!currentTodoRecord.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not authorized to remove the Record!!");
        }
        todoRepository.delete(currentTodoRecord);
        boolean isRemoved = todoRepository.findById(todoId).isEmpty();
        if (isRemoved) {
            return "Record Deleted Successfully";
        }
        return "The record was not removed!!";
    }

    private User getUserInfo(String username){
        return userRepository.findByUsername(username);
    }



}
