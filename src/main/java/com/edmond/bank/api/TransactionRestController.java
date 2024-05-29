package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.entity.User;
import com.edmond.bank.exception.ResponseHandler;
import com.edmond.bank.model.AccountTransfer;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.TransactionsService;
import com.edmond.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getTransaction(@PathVariable int transactionId, Authentication auth) {
        Transactions transactions = this.transactionsService.findById(transactionId);
        Account account = this.accountService.findById(transactions.getAccountId());
        User user = this.userService.findById(account.getUserId());
        if (user.getUsername().equalsIgnoreCase(auth.getName()))
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, transactionsService.findById(transactionId));
        return ResponseHandler.generateResponse("Unauthorized Access", HttpStatus.UNAUTHORIZED, null);
    }

    @GetMapping("account/{accountId}/transactions")
    public Page<Transactions> getAllTransactions(@PathVariable int accountId, Pageable pageable) {
        return transactionsService.findByAccountId(accountId, pageable);
    }

    @PostMapping("account/{accountId}/transactions")
    public Transactions addTransaction(@RequestBody Transactions transactions) {
        Account account = accountService.findById(transactions.getAccountId());
        transactions.setAccount(account);
        transactionsService.save(transactions);
        return transactions;
    }

    @DeleteMapping("account/{accountId}/transactions/{transactionId}")
    public void deleteTransaction(@PathVariable int transactionId) {
        transactionsService.deleteById(transactionId);
    }

    @PutMapping("account/{accountId}/transactions/{transactionId}")
    public Transactions updateTransaction(@PathVariable int transactionId, @RequestBody Transactions transactions) {
        Transactions updatedTransaction = transactionsService.findById(transactionId);
        updatedTransaction.setAmount(transactions.getAmount());
        updatedTransaction.setTransactionType(transactions.getTransactionType());
        updatedTransaction.setDate(transactions.getDate());
        transactionsService.save(updatedTransaction);
        return updatedTransaction;
    }

    @PostMapping("transactions/transferMoney")
    public void transferToAccount(@RequestBody AccountTransfer accountTransfer) {
        transactionsService.transferBetweenAccounts(accountService.findById(accountTransfer.getAccountIdFrom()),
                accountService.findById(accountTransfer.getAccountIdTo()), accountTransfer.getAmountToTransfer());
    }
}
