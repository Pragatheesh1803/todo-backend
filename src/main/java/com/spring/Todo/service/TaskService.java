package com.spring.Todo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Todo.JwtUtil;
import com.spring.Todo.dto.AddTaskForm;
import com.spring.Todo.model.Task;
import com.spring.Todo.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Task saveTask(AddTaskForm form, HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        String token = authHeader.substring(7);

        String email = jwtUtil.extractEmail(token);

        form.setEmail(email);
        form.setStatus("Pending");
        form.setIsCompleted(false);
        Task task = new Task(
                form.getEmail(),
                form.getTaskTitle(),
                form.getDescription(),
                form.getCategory(),
                form.getPriority(),
                form.getDate(),
                form.getTime(),
                form.getStatus(),
                form.getIsCompleted());

        return taskRepository.save(task);
    }

    public List<Task> getTask(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        String token = authHeader.substring(7);

        String email = jwtUtil.extractEmail(token);

        List<Task> task = taskRepository.findByEmail(email);

        return task;

    }

    public Task taskDone(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("No task found" + id));
        task.setStatus("completed");
        return taskRepository.save(task);
    }

    public Task deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found " + id));
        taskRepository.delete(task);
        return task;
    }

    public List<Task> getPending(String status, HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        String token = authHeader.substring(7);

        String email = jwtUtil.extractEmail(token);

        if (status.equals("pending")) {
            return taskRepository.findByEmailAndStatus(email, status);
        }
        return taskRepository.findByEmail(email);
    }

    public List<Task> getCompleted(String status, HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        String token = authHeader.substring(7);

        String email = jwtUtil.extractEmail(token);

        if (status.equals("completed")) {
            return taskRepository.findByEmailAndStatus(email, status);
        }

        return taskRepository.findByEmail(email);

    }

    public Long totalTask(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        String token = authHeader.substring(7);

        String email = jwtUtil.extractEmail(token);

        Long count = taskRepository.countByEmail(email);

        return count;

    }

    public Long completedTask(String status, HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        String token = authHeader.substring(7);

        String email = jwtUtil.extractEmail(token);

        if (!status.equals("completed")) {
            throw new IllegalArgumentException("Hel naaaaaaah");
        }

        return taskRepository.countByEmailAndStatus(email, status);

    }

    public Long pendingTask(String status, HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        String token = authHeader.substring(7);

        String email = jwtUtil.extractEmail(token);

        if(!status.equals("pending")) {
            throw new IllegalArgumentException("Hell naaaaaaah");
        }

        return taskRepository.countByEmailAndStatus(email, status);

    }

}
