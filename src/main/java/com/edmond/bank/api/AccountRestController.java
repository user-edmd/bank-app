package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.User;
import com.edmond.bank.exception.ResponseHandler;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/account")

public class AccountRestController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Object> getAccount(@PathVariable int accountId, Authentication auth) {
        if (accountService.findById(accountId).getUser().getUsername().equalsIgnoreCase(auth.getName()))
            return ResponseHandler.generateResponse(HttpStatus.OK, accountService.findById(accountId));
        else
            return ResponseHandler.generateResponse(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/getAccounts")
    public List<Account> getAccountsFromUser(Authentication auth) {
        User user = userService.findUserByEmail(auth.getName());
        return (user != null) ? user.getAccountList() : null;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAccountById/{accountId}")
    public Account getAccountById(@PathVariable int accountId) {
        return this.accountService.findById(accountId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAllAccounts(@PathVariable int userId, Authentication auth) {
        User user = userService.findById(userId);
        List<Account> result = user.getAccountList();
        String email = auth.getName();
        if (user.getUsername().equalsIgnoreCase(email))
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, result);
        else
            return ResponseHandler.generateResponse("Unauthorized Access", HttpStatus.UNAUTHORIZED, null);
    }

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
        Account account = accountService.findById(accountId);
        if (account.getTransactionsList() == null)
            accountService.deleteById(accountId);
    }

    private boolean isOwnerOfAccount(int accountId, Authentication auth) {
        Account account = this.accountService.findById(accountId);
        return account.getUser().getUsername().equalsIgnoreCase(auth.getName());
    }
}
