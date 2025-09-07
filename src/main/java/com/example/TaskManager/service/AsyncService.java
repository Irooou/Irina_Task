package com.example.TaskManager.service;

import com.example.TaskManager.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    @Async
    public void processTaskInBackground(Task task) {
        log.info("Фоновая обработка задачи: {}", task.getTitle());

    }
}