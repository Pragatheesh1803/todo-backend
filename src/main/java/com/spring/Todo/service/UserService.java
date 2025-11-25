package com.spring.Todo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.Todo.JwtUtil;
import com.spring.Todo.dto.LoginRequest;
import com.spring.Todo.dto.RegisterRequest;
import com.spring.Todo.model.User;
import com.spring.Todo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<?> registerUser(RegisterRequest request) {

        User user = new User(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            passwordEncoder.encode(request.getPassword()),
            passwordEncoder.encode(request.getConfirmPassword())
        );

        userRepository.save(user);

        return ResponseEntity.ok("Register Successfull");

    }

    public ResponseEntity<?> loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if(user == null) {
            return ResponseEntity.status(401).body("User not found");
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(405).body("Wrong Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of(
           "token", token,
           "user", user 
        ));

    }

    

}
