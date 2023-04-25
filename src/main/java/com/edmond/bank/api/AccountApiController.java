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

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable int accountId) {
        return accountService.findById(accountId);
    }

    @PostMapping("/")
    public Account addAccount(@PathVariable("userId") int userId, @RequestBody Account account) {
        User user = userService.findById(userId);
        account.setUser(user);
        accountService.save(account);
        return account;
    }

    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable int accountId, @RequestBody Account account) {
        Account updatedAccount = accountService.findById(accountId);
        updatedAccount.setAccountType(account.getAccountType());
        updatedAccount.setAccountNumber(account.getAccountNumber());
        accountService.save(updatedAccount);
        return updatedAccount;
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable("accountId") int accountId) {
        accountService.deleteById(accountId);
    }
}
