package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> updateUser(UUID id, UserEntity updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setId(id);
            return Optional.of(userRepository.save(updatedUser));
        }
        return Optional.empty();
    }

    public boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}