package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmond.bank.dao.AccountRepository;
import com.edmond.bank.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> findAll() { return accountRepository.findAll(); }
    
	public Account findById(int theId) {
        Optional<Account> result = accountRepository.findById(theId);
        Account account = null;
        if (result.isPresent()) {
        	account = result.get();
        } else {
            throw new RuntimeException("Did not find account id");
        }
        return account;
		
    }

    
    public void save(Account theAccount) {
    	accountRepository.save(theAccount);
    }
    
    public void deleteById(int theId) {
    	accountRepository.deleteById(theId);
    }
   
}
