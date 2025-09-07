package com.example.TaskManager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "\"user\"") // Экранирование двойными кавычками
// Или: @Table(name = "[user]") // Для H2 можно и так
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}