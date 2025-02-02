package org.some.todolist.service;

import lombok.extern.log4j.Log4j2;
import org.some.todolist.model.Task;
import org.some.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> getTaskById(Long id) {
        log.info("Get task by ID " + id);
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks ()    {
        log.info("Get all tasks");
        var result = taskRepository.findAll();
        System.out.println(result);
        return taskRepository.findAll();
    }
    @Transactional
    public void createTask (Task task) {
        log.info("Saving task: " + task);
        taskRepository.saveAndFlush(task);
    }
    @Transactional
    public void updateTaskById(Long id, Task updatedTask) {
        log.info("Update task " + updatedTask);
        taskRepository.findById(id)
                .map(task -> {
                    task.setName(updatedTask.getName());
                    task.setDescription(updatedTask.getDescription());
                    task.setStatus(updatedTask.getStatus());
                    task.setDueDate(updatedTask.getDueDate());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }
    @Transactional
    public void deleteTaskById (Long id)    {
        log.info("Deleting task " + id);
        taskRepository.deleteById(id);
    }

}
