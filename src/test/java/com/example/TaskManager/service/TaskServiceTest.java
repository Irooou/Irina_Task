package com.example.TaskManager.service;

import com.example.TaskManager.config.RabbitMQConfig;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceTest {

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TaskService taskService;

    @Test
    void createTask_ShouldSendRabbitMQMessage() {
        // Arrange
        Task task = new Task();
        task.setTitle("Test Task");
        when(taskRepository.save(any())).thenReturn(task);

        // Act
        taskService.createTask(task);

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(RabbitMQConfig.EXCHANGE_NAME),
                eq(RabbitMQConfig.ROUTING_KEY),
                any(Task.class)
        );
    }
}