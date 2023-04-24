package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/{userId}/account/")

public class AccountApiController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable int id) {
        return accountService.findById(id);
    }

}
