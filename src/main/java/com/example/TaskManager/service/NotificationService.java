package com.example.TaskManager.service;

import com.example.TaskManager.config.RabbitMQConfig;
import com.example.TaskManager.model.Task;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleTaskCreated(Task task) {
        System.out.println("### НОВАЯ ЗАДАЧА СОЗДАНА ###");
        System.out.println("ID: " + task.getId());
        System.out.println("Название: " + task.getTitle());
        System.out.println("Описание: " + task.getDescription());
    }
}