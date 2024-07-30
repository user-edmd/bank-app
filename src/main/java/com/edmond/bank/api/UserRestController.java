package com.edmond.bank.api;

import com.edmond.bank.entity.User;
import com.edmond.bank.exception.ResponseHandler;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/getUser")
    public ResponseEntity<Object> getUser(Authentication auth) {
        String email = auth.getName();
        Optional<User> user = userService.findUserByEmail(email);

        if (user.isEmpty()) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND);
        } else if (user.get().getUsername().equalsIgnoreCase(email)) {
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, user);
        } else {
            return ResponseHandler.generateResponse("Unauthorized Access", HttpStatus.UNAUTHORIZED, null);
        }
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.editUser(user);
    }

    @GetMapping("/testGetUser/{username}")
//    @PreAuthorize("hasAuthority('Admin')")
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @PreAuthorize("#username == authentication.name")
//    @PostAuthorize("returnObject.username == authentication.name")
    public Optional<User> testGetUser(@PathVariable String username) {
        return userService.findUserByEmail(username);
    }

    //ADMIN ONLY

    @GetMapping("/getUserById/{userId}")
    @PreAuthorize("hasAuthority('Admin')")
    public Optional<User> getUserById(@PathVariable int userId) { return this.userService.findById(userId); }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteUser(@PathVariable int userId) {
        this.userService.deleteById(userId);
    }
}
