package com.example.TaskManager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "\"task\"") // Экранирование двойными кавычками
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}