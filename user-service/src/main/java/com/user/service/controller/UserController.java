package com.user.service.controller;

import com.user.service.model.User;
import com.user.service.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name="id", required = true) Long id){
        logger.info("GetUserById user id : {}",id);
        return userService.getUserById(id);
    }

    @GetMapping()
    public List<User> getAllUsers(){
        logger.info(" info <<<< getAllUsers");
        logger.debug(" debug <<<< getAllUsers");
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name="id", required = true) Long id){
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(name="id", required = true) Long id, @RequestBody User user){
        return userService.updateUser(id,user);
    }
}
