package com.edmond.bank.api;

import com.edmond.bank.entity.User;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        return userService.findById(userId);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User user) {
        User updateUser = userService.findById(userId);
        updateUser.setFirstName(user.getFirstName());
        userService.save(updateUser);
        return updateUser;
    }
}
