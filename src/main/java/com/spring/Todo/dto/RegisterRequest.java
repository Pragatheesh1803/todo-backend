package com.spring.Todo.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String confirmPassword;
    
}
