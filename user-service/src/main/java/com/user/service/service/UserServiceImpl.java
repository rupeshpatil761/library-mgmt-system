package com.user.service.service;

import com.user.service.config.UserConfiguration;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.model.User;
import com.user.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserConfiguration userConfiguration;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        //For testing the RefreshScope feature
        /*System.out.println(userConfiguration.getValue()+" <<<<<<<<< userConfiguration getValue");
        System.out.println(userConfiguration.getLocalProperty()+" <<<<<<<<< userConfiguration getLocalProperty");*/
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public User updateUser(Long id, User requestedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        user.setName(requestedUser.getName());
        user.setEmail(requestedUser.getEmail());
        user.setUserType(requestedUser.getUserType());
        return userRepository.save(user);
        //return new GenericResponse(200, true, "Customers found", customers );
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
