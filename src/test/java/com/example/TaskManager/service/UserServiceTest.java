package com.example.TaskManager.service;

import com.example.TaskManager.model.User;
import com.example.TaskManager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void getAllUsers_ShouldReturnUsers() {
        // Arrange
        User user = new User();
        user.setName("Alice");
        when(userRepository.findAll()).thenReturn(List.of(user));

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
    }
}