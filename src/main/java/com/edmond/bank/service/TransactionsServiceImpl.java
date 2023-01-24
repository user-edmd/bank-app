package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmond.bank.dao.TransactionsRepository;
import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.entity.User;

@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	private TransactionsRepository transactionsRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	public List<Transactions> findAll() { return transactionsRepository.findAll(); }
    
	public Transactions findById(int theId) {
        Optional<Transactions> result = transactionsRepository.findById(theId);
        Transactions transactions = null;
        if (result.isPresent()) {
        	transactions = result.get();
        } else {
            throw new RuntimeException("Did not find transaction id");
        }
        return transactions;
    }

    
    public void save(Transactions theTransactions) {
    	if (theTransactions.getTransactionType().equalsIgnoreCase("withdraw")) {
    		if (theTransactions.getAccount().getAccountBalance() < theTransactions.getAmount()) {
    			throw new RuntimeException("Not enough balance in account to withdraw.");
    		}
    		Double temp = theTransactions.getAmount();
    		temp *= -1;
    		theTransactions.setAmount(temp);
    	}
    	
    	if (theTransactions.getDate() == null)
    		theTransactions.setDate(DateTime.now().toLocalDate().toString());
    	transactionsRepository.save(theTransactions);
    }
    
    public void deleteById(int theId) {
    	transactionsRepository.deleteById(theId);
    }
    
    public Double findTotalByAccountId(int accountId) {
    	return transactionsRepository.findTotalByAccountId(accountId);
    }
    
    public void transferBetweenAccounts(Account accountIdFrom, Account accountIdTo, Double amount) {
    	User userFrom = userService.findById(accountIdFrom.getUserId());
    	accountIdFrom.setUser(userFrom);
    	Transactions transactionFrom = new Transactions();
    	transactionFrom.setAccount(accountIdFrom);
    	transactionFrom.setAccountId(accountIdFrom.getId());
    	transactionFrom.setAmount(amount * -1);
    	transactionFrom.setTransactionType("Transfer from: " + accountIdFrom.getHiddenAccountNumber());
    	
    	User userTo = userService.findById(accountIdTo.getUserId());
    	accountIdTo.setUser(userTo);
    	Transactions transactionTo = new Transactions();
    	transactionTo.setAccount(accountIdTo);
    	transactionTo.setAccountId(accountIdTo.getId());
    	transactionTo.setAmount(amount);
    	transactionTo.setTransactionType("Transfer to: " + accountIdTo.getHiddenAccountNumber());
    	
    	this.save(transactionFrom);
    	this.save(transactionTo);
    }
}
