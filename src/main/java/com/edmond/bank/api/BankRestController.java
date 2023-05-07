package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.TransactionsService;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController("/")
public class BankRestController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionsService transactionsService;


    @GetMapping("user/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("accounts/all")
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

//    @GetMapping("transactions/all")
//    public List<Transactions> getAllTransactions() {
//        return transactionsService.findAll();
//    }

@GetMapping("transactions/all")
    public Page<Transactions> getAllTransactions(Pageable pageable) {
        return transactionsService.findAll(pageable);
    }
}
