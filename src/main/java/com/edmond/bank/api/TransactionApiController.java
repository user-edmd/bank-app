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
    AccountService accountService;
    @Autowired
    TransactionsService transactionsService;

    @GetMapping("/{transactionId}")
    public Transactions getTransaction(@PathVariable int transactionId) {
        return transactionsService.findById(transactionId);
    }

    @GetMapping("/")
    public List<Transactions> getAllTransactions(@PathVariable int accountId) {
        Account account = accountService.findById(accountId);
        return account.getTransactionsList();
    }

    @PostMapping("/")
    public Transactions addTransaction(@PathVariable("accountId") int accountId, @RequestBody Transactions transactions) {
        Account account = accountService.findById(accountId);
        transactions.setAccount(account);
        transactionsService.save(transactions);
        return transactions;
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable int transactionId) {
        transactionsService.deleteById(transactionId);
    }

    @PutMapping("/{transactionId}")
    public Transactions updateTransaction(@PathVariable int transactionId, @RequestBody Transactions transactions) {
        Transactions updatedTransaction = transactionsService.findById(transactionId);
        updatedTransaction.setAmount(transactions.getAmount());
        updatedTransaction.setTransactionType(transactions.getTransactionType());
        updatedTransaction.setDate(transactions.getDate());
        transactionsService.save(updatedTransaction);
        return updatedTransaction;
    }
}
