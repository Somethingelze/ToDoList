package org.some.todolist.service;

import org.some.todolist.model.Task;
import org.some.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks ()    {
        return taskRepository.findAll();
    }

    public Task createTask (Task task) {
        return taskRepository.save(task);
    }

    public Task updateTaskById (Long id, Task updatedTask)  {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(updatedTask.getName());
                    task.setDescription(updatedTask.getDescription());
                    task.setStatus(updatedTask.getStatus());
                    task.setDueDate(updatedTask.getDueDate());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public void deleteTaskById (Long id)    {
        taskRepository.deleteById(id);
    }

}
