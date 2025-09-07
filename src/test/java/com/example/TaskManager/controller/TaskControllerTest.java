package com.example.TaskManager.controller;

import com.example.TaskManager.model.Task;
import com.example.TaskManager.service.TaskService;
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
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void getAllTasks_ReturnsTasksList() {
        // Arrange
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setCompleted(false);

        when(taskService.getAllTasks()).thenReturn(List.of(task));

        // Act
        ResponseEntity<List<Task>> response = taskController.getAllTasks();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Task", response.getBody().get(0).getTitle());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void createTask_ReturnsCreatedTask() {
        // Arrange
        Task newTask = new Task();
        newTask.setTitle("New Task");
        newTask.setDescription("New Description");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle(newTask.getTitle());
        savedTask.setDescription(newTask.getDescription());

        when(taskService.createTask(any(Task.class))).thenReturn(savedTask);

        // Act
        ResponseEntity<Task> response = taskController.createTask(newTask);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
        assertEquals("New Task", response.getBody().getTitle());
        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    void deleteTask_ReturnsNoContent() {
        // Act
        ResponseEntity<Void> response = taskController.deleteTask(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService, times(1)).deleteTask(1L);
    }
}