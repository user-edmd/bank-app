package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userId}/account/")

public class AccountApiController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable int id) {
        return accountService.findById(id);
    }

    @PostMapping("/")
    public Account addAccount(@RequestBody Account account) {
        accountService.save(account);
        return account;
    }

}
