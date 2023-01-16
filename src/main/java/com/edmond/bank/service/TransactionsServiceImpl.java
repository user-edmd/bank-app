package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmond.bank.dao.TransactionsRepository;
import com.edmond.bank.dao.UserRepository;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.entity.User;

@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	private TransactionsRepository transactionsRepository;
	
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
    	transactionsRepository.save(theTransactions);
    }
    
    public void deleteById(int theId) {
    	transactionsRepository.deleteById(theId);
    }
    
    public Double findTotalByAccountId(int accountId) {
    	return transactionsRepository.findTotalByAccountId(accountId);
    }
}
