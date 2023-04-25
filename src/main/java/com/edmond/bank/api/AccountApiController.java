package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userId}/account/")

public class AccountApiController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable int id) {
        return accountService.findById(id);
    }

    @PostMapping("/")
    public Account addAccount(@PathVariable("userId") int userId, @RequestBody Account account) {
        User user = userService.findById(userId);
        account.setUser(user);
        accountService.save(account);
        return account;
    }

}
