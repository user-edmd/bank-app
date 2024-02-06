package com.edmond.bank.api;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.model.AccountTransfer;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class TransactionRestController {

    @Autowired
    AccountService accountService;
    @Autowired
    TransactionsService transactionsService;

    @GetMapping("account/{accountId}/transactions/{transactionId}")
    public Transactions getTransaction(@PathVariable int transactionId) {
        return transactionsService.findById(transactionId);
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
