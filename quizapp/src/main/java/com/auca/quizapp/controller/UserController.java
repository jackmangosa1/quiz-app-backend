package com.auca.quizapp.controller;

import com.auca.quizapp.model.User;
import com.auca.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean isAuthenticated = userService.login(user.getUsername(), user.getPassword());
        if (isAuthenticated) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }


}
