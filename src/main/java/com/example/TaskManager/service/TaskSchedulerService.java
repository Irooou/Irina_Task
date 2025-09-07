package com.example.TaskManager.service;

import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskSchedulerService {
    private final TaskRepository taskRepository;

    // Проверка каждые 5 минут
    @Scheduled(fixedRate = 300_000)
    public void checkOverdueTasks() {
        LocalDateTime now = LocalDateTime.now();
        List<Task> overdueTasks = taskRepository.findByDueDateBeforeAndCompletedIsFalse(now);

        if (!overdueTasks.isEmpty()) {
            log.warn("Найдено просроченных задач: {}", overdueTasks.size());

        }
    }
}