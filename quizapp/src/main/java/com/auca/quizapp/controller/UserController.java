package com.auca.quizapp.controller;

import com.auca.quizapp.model.User;
import com.auca.quizapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("User registration request received for username: {}", user.getUsername());
        User newUser = userService.createUser(user);
        logger.info("User registered successfully with username: {}", newUser.getUsername());
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpSession session) {
        logger.info("Login request received for username: {}", user.getUsername());
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            session.setAttribute("loggedInUserId", loggedInUser.getId());
            session.setAttribute("userRole", loggedInUser.getRole().toString()); // Store user role in session
            Map<String, String> responseData = new HashMap<>();
            responseData.put("sessionId", loggedInUser.getId().toString());
            responseData.put("userRole", loggedInUser.getRole().toString());
            logger.info("User logged in successfully with username: {}", loggedInUser.getUsername());
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            logger.warn("Invalid login attempt for username: {}", user.getUsername());
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        System.out.println(session.getAttribute("loggedInUserId"));
        String username = null;
        if (session != null) {
            // Get username from session
            Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");
            if (loggedInUserId != null) {
                // Retrieve the username using loggedInUserId
                User user = userService.getUserById(loggedInUserId.longValue());
                if (user != null) {
                    username = user.getUsername();
                }
            }
            session.invalidate();
            if (username != null) {
                logger.info("User '{}' logged out successfully", username);
                return new ResponseEntity<>("User '" + username + "' logged out successfully", HttpStatus.OK);
            } else {
                logger.warn("User logged out successfully");
                return new ResponseEntity<>("User logged out successfully", HttpStatus.OK);
            }
        } else {
            logger.warn("No active session found for logout");
            return new ResponseEntity<>("No user logged in", HttpStatus.UNAUTHORIZED);
        }
    }











}
