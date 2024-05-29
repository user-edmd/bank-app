package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.entity.User;
import com.edmond.bank.exception.ResponseHandler;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.TransactionsService;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController("/")
public class BankRestController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionsService transactionsService;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("api/user/all")
    public ResponseEntity<Object> getAllUsers() {
        List<User> userList = userService.findAll();
        return ResponseHandler.generateResponse("OK", HttpStatus.OK, userList);
    }

    @GetMapping("accounts/all")
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("transactions/all")
    public Page<Transactions> getAllTransactions(Pageable pageable) {
        return transactionsService.findAll(pageable);
    }
}
