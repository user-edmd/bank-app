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
import java.util.Optional;

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
        Optional<Account> account = accountService.findById(accountId);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        if (account.get().getUser().getUsername().equalsIgnoreCase(auth.getName()))
            return ResponseHandler.generateResponse(HttpStatus.OK, accountService.findById(accountId));
        else
            return ResponseHandler.generateResponse(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/getAccounts")
    public List<Account> getAccountsFromUser(Authentication auth) {
        Optional<User> user = userService.findUserByEmail(auth.getName());
        return user.map(User::getAccountList).orElse(null);
//        return (user.isPresent()) ? user.get().getAccountList() : nullya
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAllAccounts(@PathVariable int userId, Authentication auth) {
        Optional<User> result = userService.findById(userId);
        if (result.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = result.get();
        List<Account> userAccountList = user.getAccountList();
        String email = auth.getName();
        if (user.getUsername().equalsIgnoreCase(email))
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, userAccountList);
        else
            return ResponseHandler.generateResponse("Unauthorized Access", HttpStatus.UNAUTHORIZED, null);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    // ADMIN ONLY

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable("accountId") int accountId) {
        Optional<Account> account = accountService.findById(accountId);
        if (account.isPresent()) {
            if (account.get().getTransactionsList() == null)
                accountService.deleteById(accountId);
        }
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{accountId}")
    public Account updateAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAccountById/{accountId}")
    public Optional<Account> getAccountById(@PathVariable int accountId) {
        return this.accountService.findById(accountId);
    }
}
