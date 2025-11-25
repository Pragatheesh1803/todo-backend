package com.spring.Todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Todo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEmail(String email);
    List<Task> findByStatus(String status);
    List<Task> findByEmailAndStatus(String email, String status);
    Long countByEmail(String email);
    Long countByEmailAndStatus(String email, String password);
}
