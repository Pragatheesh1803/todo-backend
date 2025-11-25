package com.spring.Todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "task")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String email;
    String taskTitle;
    String description;
    String category;
    String priority;
    String date;
    String time;
    String status;
    Boolean isCompleted;

    public Task() {}

    public Task(
        String email,
        String taskTitle,
        String description, 
        String category, 
        String priority, 
        String date, 
        String time, 
        String status, 
        Boolean isCompleted
        ) {
            this.email = email;
            this.taskTitle = taskTitle;
            this.description = description;
            this.category = category;
            this.priority = priority;
            this.date = date;
            this.time = time;
            this.status = status;
            this.isCompleted = isCompleted;
        }

}
