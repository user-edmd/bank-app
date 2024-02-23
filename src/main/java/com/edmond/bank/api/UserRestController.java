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
@CrossOrigin("*")
@RequestMapping("api/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Object> getUser(JwtAuthenticationToken auth) {
        String email = (String) auth.getToken().getClaims().get("email");
        User result = userService.findUserByEmail(email); //Use optional

        if (result == null) {
            return ResponseHandler.generateResponse("Register Required", HttpStatus.ACCEPTED, null);
        } else if (result.getUsername().equalsIgnoreCase(email)) {
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, result);
        } else {
            return ResponseHandler.generateResponse("Unauthorized Access", HttpStatus.UNAUTHORIZED, null);
        }
    }

    /**
     * TODO: This is an endpoint that should only be adminstered by an ADMIN. Put in AccountRestController instead
     * @param user
     * @param auth
     * @return
     */
    @PostMapping
    public User addUser(@RequestBody User user, JwtAuthenticationToken auth) {
        return userService.createUser(user);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId, JwtAuthenticationToken auth) {
        //TODO: Throw an error if not authenticated or authorized inside isUserAuthenticated method
        if(isUserAuthenticated(userId, auth))
            userService.deleteById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User user, JwtAuthenticationToken auth) {
        if(isUserAuthenticated(userId, auth))
            this.userService.editUser(user);

        //TODO: Throw an error if not authenticated or authorized inside isUserAuthenticated method
        return user;
    }

    private boolean isUserAuthenticated(int userId, JwtAuthenticationToken auth) {
        String email = (String) auth.getToken().getClaims().get("email");
        User result = userService.findUserByEmail(email); //Use optional

        return result.getUsername().equalsIgnoreCase(email);
    }
}
