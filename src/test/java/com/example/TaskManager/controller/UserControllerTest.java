package com.example.TaskManager.controller;

import com.example.TaskManager.model.User;
import com.example.TaskManager.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getAllUsers_ReturnsUsersList() {
        // Arrange
        User user = new User(1L, "Alice", "alice@example.com");
        when(userService.getAllUsers()).thenReturn(List.of(user));

        // Act
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void createUser_ReturnsCreatedUser() {
        // Arrange
        User newUser = new User(null, "Bob", "bob@example.com");
        User savedUser = new User(1L, "Bob", "bob@example.com");
        when(userService.createUser(newUser)).thenReturn(savedUser);

        // Act
        ResponseEntity<User> response = userController.createUser(newUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }
}