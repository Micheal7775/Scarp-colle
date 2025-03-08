package com.example.scrap.collection.service;

import com.example.scrap.collection.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private com.example.scrap.collection.repo.UserRepository userRepository;

    // Register a new user
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        return userRepository.save(user);
    }

    // Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update user details
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        return userRepository.save(existingUser);
    }

    // Delete user
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
