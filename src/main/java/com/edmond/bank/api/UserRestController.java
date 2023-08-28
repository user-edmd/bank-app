package com.edmond.bank.api;

import com.edmond.bank.entity.User;
import com.edmond.bank.exception.ResponseHandler;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserRestController {

    @Autowired
    UserService userService;

//    @GetMapping("/{userId}")
//    public User getUser(@PathVariable int userId, JwtAuthenticationToken auth) {
//        String email = (String) auth.getToken().getClaims().get("email");
//        User searchUser = userService.findById(userId);
//        if (searchUser.getUsername().equalsIgnoreCase(email))
//            return userService.findById(userId);
//        else
//            throw new UnauthorizedException("You are not authorized");
//    }


    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable int userId, JwtAuthenticationToken auth) {
        User result = userService.findById(userId); //Use optional
        String email = (String) auth.getToken().getClaims().get("email");
        if (result.getUsername().equalsIgnoreCase(email))
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, result);
        else
            return ResponseHandler.generateResponse("Unauthorized Access", HttpStatus.UNAUTHORIZED, null);
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
    public User updateUser(@RequestBody User user) {
        this.userService.editUser(user);
        return user;
    }
}
