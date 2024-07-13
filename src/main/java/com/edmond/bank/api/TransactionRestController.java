package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.model.AccountTransfer;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.TransactionsService;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api")
public class TransactionRestController {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionsService transactionsService;

    @GetMapping("account/{accountId}/transactions/{transactionId}")
    public Optional<Transactions> getTransaction(@PathVariable int transactionId) {
        return transactionsService.findById(transactionId);
    }

    @GetMapping("account/{accountId}/transactions")
    public Page<Transactions> getAllTransactions(@PathVariable int accountId, Pageable pageable) {
        return transactionsService.findByAccountId(accountId, pageable);
    }

    @PostMapping("account/{accountId}/transactions")
    public Transactions addTransaction(@RequestBody Transactions transactions) {
        return transactionsService.save(transactions);
    }

    @DeleteMapping("transactions/{transactionId}")
    public void deleteTransaction(@PathVariable int transactionId) {
        transactionsService.deleteById(transactionId);
    }

    @PutMapping("account/{accountId}/transactions/{transactionId}")
    public Transactions updateTransaction(@RequestBody Transactions transactions) {
        return transactionsService.save(transactions);
    }

    @PostMapping("transactions/transferMoney")
    public void transferToAccount(@RequestBody AccountTransfer accountTransfer) {
        Optional<Account> accountFrom = accountService.findById(accountTransfer.getAccountIdFrom());
        Optional<Account> accountTo = accountService.findById(accountTransfer.getAccountIdTo());

        if (accountFrom.isPresent() && accountTo.isPresent()) {
            transactionsService.transferBetweenAccounts(accountFrom.get(),
                   accountTo.get(), accountTransfer.getAmountToTransfer());
        }
    }
}
