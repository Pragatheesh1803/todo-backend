package com.spring.Todo.dto;

import lombok.Data;

@Data
public class AddTaskForm {
    
    String email;
    String taskTitle;
    String description;
    String category;
    String priority;
    String date;
    String time;
    String status = "Pending";
    Boolean isCompleted = false;

}
