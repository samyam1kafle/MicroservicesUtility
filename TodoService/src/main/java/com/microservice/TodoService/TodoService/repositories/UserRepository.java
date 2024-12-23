package com.microservice.TodoService.TodoService.repositories;

import com.microservice.TodoService.TodoService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Samyam Kafle
 * @Portfolio : https://samyamkafle.com.np
 * @Project : microserviceDemo
 * @CreatedDate : 22/12/2024, Sunday
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
