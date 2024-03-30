package com.auca.quizapp.service;

import com.auca.quizapp.dao.UserDao;
import com.auca.quizapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userDao.save(user);
    }

    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }





    // Add other service methods as needed
}
