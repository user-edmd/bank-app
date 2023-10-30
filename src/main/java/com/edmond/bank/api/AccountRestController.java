package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.User;
import com.edmond.bank.exception.ResponseHandler;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
    public Account getAccount(@PathVariable int accountId) {
        return accountService.findById(accountId);
    }

//    @GetMapping("/user/{userId}")
//    public List<Account> getAllAccounts(@PathVariable int userId) {
//        return userService.findById(userId).getAccountList();
//    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAllAccounts(@PathVariable int userId, JwtAuthenticationToken auth) {
        User user = userService.findById(userId);
        List<Account> result = user.getAccountList();
        String email = (String) auth.getToken().getClaims().get("email");
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
        accountService.deleteById(accountId);
    }
}
