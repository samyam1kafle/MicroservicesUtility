package com.microservice.TodoService.TodoService.service;

import com.microservice.TodoService.TodoService.entity.CustomUserDetails;
import com.microservice.TodoService.TodoService.entity.User;
import com.microservice.TodoService.TodoService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author : Samyam Kafle
 * @Portfolio : https://samyamkafle.com.np
 * @Project : microserviceDemo
 * @CreatedDate : 22/12/2024, Sunday
 **/
@Service
public class DetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}

