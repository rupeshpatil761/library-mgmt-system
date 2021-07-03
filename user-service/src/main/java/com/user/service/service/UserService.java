package com.user.service.service;

import com.user.service.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User addUser(User book);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User book);

    ResponseEntity<?> deleteUser(Long id);
}
