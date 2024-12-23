package com.microservice.TodoService.TodoService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @Author : Samyam Kafle
 * @Portfolio : https://samyamkafle.com.np
 * @Project : microserviceDemo
 * @CreatedDate : 22/12/2024, Sunday
 **/
@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
