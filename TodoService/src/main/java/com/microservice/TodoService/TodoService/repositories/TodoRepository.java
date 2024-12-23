package com.microservice.TodoService.TodoService.repositories;

import com.microservice.TodoService.TodoService.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Samyam Kafle
 * @Portfolio : https://samyamkafle.com.np
 * @Project : microserviceDemo
 * @CreatedDate : 22/12/2024, Sunday
 **/
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> { }
