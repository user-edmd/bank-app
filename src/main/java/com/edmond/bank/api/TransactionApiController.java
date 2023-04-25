package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.TransactionsService;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/account/{accountId}/transactions")
public class TransactionApiController {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionsService transactionsService;

    @GetMapping("/{id}")
    public Transactions getTransactions(@PathVariable int id) {
        return transactionsService.findById(id);
    }

    @GetMapping("/")
    public List<Transactions> getAllTransactions() {
        return transactionsService.findAll();
    }

    @PostMapping("/")
    public Transactions addTransaction(@PathVariable("accountId") int accountId, @RequestBody Transactions transactions) {
        Account account = accountService.findById(accountId);
        transactions.setAccount(account);
        transactionsService.save(transactions);
        return transactions;
    }
}
