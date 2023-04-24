package com.edmond.bank.api;

import com.edmond.bank.entity.User;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class UserAPIController {

    @Autowired
    UserService userService;

    ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

}
