package com.microservice.TodoService.TodoService.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservice.TodoService.TodoService.entity.Todo;
import com.microservice.TodoService.TodoService.entity.User;
import com.microservice.TodoService.TodoService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @Author : Samyam Kafle
 * @Portfolio : https://samyamkafle.com.np
 * @Project : microserviceDemo
 * @CreatedDate : 22/12/2024, Sunday
 **/
@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verifyUser(user);
    }

    @GetMapping("/show-todo-list")
    public List<Todo> showTodo(Authentication authentication) {
        return userService.getTodoList(authentication);
    }

    @PostMapping("/add-todo")
    public Todo addTodo(Authentication authentication,@RequestBody Todo todo) {
        return userService.createTodo(authentication,todo);
    }
    @PutMapping("todo/edit/{todoId}")
    public ResponseEntity<Todo> editTodo(Authentication authentication,
                                         @PathVariable Long todoId,
                                         @RequestBody Todo updatedTodo) {
        Todo editedTodo = userService.editTodo(authentication, todoId, updatedTodo);
        return ResponseEntity.ok(editedTodo);
    }
    @DeleteMapping("todo/delete/{todoId}")
    public String deleteTodoRecord(Authentication authentication,@PathVariable Long todoId) {
        return userService.deleteTodo(authentication,todoId);
    }
}
