package com.example.TaskManager.service;

import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void getAllTasks_ShouldCacheResults() {
        // Arrange
        Task task = new Task();
        task.setTitle("Test Task");
        when(taskRepository.findAll()).thenReturn(List.of(task));

        // Act & Assert (First call - DB)
        taskService.getAllTasks();
        verify(taskRepository, times(1)).findAll();

        // Act & Assert (Second call - Cache)
        taskService.getAllTasks();
        verify(taskRepository, times(1)).findAll();

        // Verify cache
        assertNotNull(cacheManager.getCache("tasks").get("TaskManager::tasks"));
    }
}