package com.spring.Todo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Todo.dto.AddTaskForm;
import com.spring.Todo.dto.LoginRequest;
import com.spring.Todo.dto.RegisterRequest;
import com.spring.Todo.model.Task;
import com.spring.Todo.service.TaskService;
import com.spring.Todo.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

    @PostMapping("/addtask")
    public ResponseEntity<?> addTask(@RequestBody AddTaskForm form, HttpServletRequest request) {
        Task task = taskService.saveTask(form, request);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/gettask")
    public ResponseEntity<?> getTask(HttpServletRequest request) {
        List<Task> task = taskService.getTask(request);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/task-completed")
    public ResponseEntity<?> taskDone(Long id) {
        Task task = taskService.taskDone(id);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/task-deleted")
    public ResponseEntity<?> deleteTask(Long id) {
        Task task = taskService.deleteTask(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/pending-task")
    public ResponseEntity<?> pendingTask(@RequestParam String status, HttpServletRequest request) {
        List<Task> task = taskService.getPending(status, request);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/get-completed-task")
    public ResponseEntity<?> getCompleted(@RequestParam String status, HttpServletRequest request) {
        List<Task> task = taskService.getCompleted(status, request);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/count-total-task")
    public ResponseEntity<Long> countTotalTask(HttpServletRequest request) {
        Long count = taskService.totalTask(request);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count-completed-task")
    public ResponseEntity<Long> countCompletedTask(@RequestParam String status, HttpServletRequest request) {
        Long count = taskService.completedTask(status, request);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count-pending-task")
    public ResponseEntity<Long> countPendingTask(@RequestParam String status, HttpServletRequest request) {
        Long count = taskService.pendingTask(status, request);
        return ResponseEntity.ok(count);
    }

}
