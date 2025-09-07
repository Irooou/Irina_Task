package com.example.TaskManager.service;

import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements Serializable {
    private final TaskRepository taskRepository;

    @Cacheable(value = "tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @CacheEvict(value = "tasks", allEntries = true)
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @CacheEvict(value = "tasks", allEntries = true)
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}