package com.edmond.bank.api;

import com.edmond.bank.entity.User;
import com.edmond.bank.exception.ResponseHandler;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Object> getUser(Authentication auth) {
        String email = auth.getName();
        User result = userService.findUserByEmail(email); //Use optional

        if (result == null) {
            return ResponseHandler.generateResponse("Register Required", HttpStatus.ACCEPTED, null);
        } else if (result.getUsername().equalsIgnoreCase(email)) {
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, result);
        } else {
            return ResponseHandler.generateResponse("Unauthorized Access", HttpStatus.UNAUTHORIZED, null);
        }
    }

    @PostMapping
    public User addUser(@RequestBody User user, JwtAuthenticationToken auth) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId, JwtAuthenticationToken auth) {
        this.userService.deleteById(userId);
    }

    @PutMapping
    public User updateUser(@RequestBody User user, JwtAuthenticationToken auth) {
        this.userService.editUser(user);
        return user;
    }

    @GetMapping("/testGetUser/{username}")
//    @PreAuthorize("hasAuthority('Admin')")
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @PreAuthorize("#username == authentication.name")
//    @PostAuthorize("returnObject.username == authentication.name")
    public User testGetUser(@PathVariable String username) {
        return userService.findById(1);
    }
}
