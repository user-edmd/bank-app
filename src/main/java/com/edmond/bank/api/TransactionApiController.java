package com.edmond.bank.api;

import com.edmond.bank.entity.Transactions;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/account/{accountId}/transactions")
public class TransactionApiController {

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
}
