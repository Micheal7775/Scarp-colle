package com.example.scrap.collection.controller;


import com.example.scrap.collection.model.User;
import com.example.scrap.collection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Register User
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully.";
    }

    // Get User by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Update User
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        userService.updateUser(id, updatedUser);
        return "User updated successfully.";
    }

    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully.";
    }
}
