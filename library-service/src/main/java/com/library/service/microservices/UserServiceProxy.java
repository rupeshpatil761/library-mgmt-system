package com.library.service.microservices;

import com.library.service.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="user-service",url="localhost:9091") //Without Ribbon/ClientLoadBalancer
//@FeignClient(name="user-service") //With Ribbon/ClientLoadBalancer
//@LoadBalancerClient(name="user-service")
public interface UserServiceProxy {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") Long id);

    @GetMapping("/users")
    List<User> getAllUsers();

    @PostMapping("/users")
    User addUser(@RequestBody User user);

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable(name="id", required = true) Long id);

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable(name="id", required = true) Long id, @RequestBody User user);
}
