package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account")

public class AccountRestController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable int accountId) {
        return accountService.findById(accountId);
    }

    @GetMapping("/user/{userId}")
    public List<Account> getAllAccounts(@PathVariable int userId) {
        return userService.findById(userId).getAccountList();
    }

//    @PostMapping
//    public Account addAccount(@RequestBody Account account) {
//        User user = userService.findById(account.getUserId());
//        account.setUser(user);
//        accountService.save(account);
//        return account;
//    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
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
