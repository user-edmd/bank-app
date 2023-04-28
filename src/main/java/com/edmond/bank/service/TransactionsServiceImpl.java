package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import com.edmond.bank.model.TransactionsForm;
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
	private UserService userService;

	@Autowired
	private AccountService accountService;

	public List<Transactions> findAll() {
		return transactionsRepository.findAll();
	}

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
		if (accountIdFrom.getId() == accountIdTo.getId())
			throw new RuntimeException("Cannot transfer to same account");

		User userFrom = userService.findById(accountIdFrom.getUserId());
		accountIdFrom.setUser(userFrom);

		if (amount > accountIdFrom.getAccountBalance())
			throw new RuntimeException("Cannot transfer to same account");

		Transactions transactionFrom = new Transactions();
		transactionFrom.setAccount(accountIdFrom);
		transactionFrom.setAccountId(accountIdFrom.getId());
		transactionFrom.setAmount(amount * -1);
		transactionFrom.setTransactionType("Transfer to Account (..." + accountIdTo.lastFourDigitsAcctNumber() + ")");

		User userTo = userService.findById(accountIdTo.getUserId());
		accountIdTo.setUser(userTo);
		Transactions transactionTo = new Transactions();
		transactionTo.setAccount(accountIdTo);
		transactionTo.setAccountId(accountIdTo.getId());
		transactionTo.setAmount(amount);
		transactionTo.setTransactionType("Transfer from Account (..." + accountIdFrom.lastFourDigitsAcctNumber() + ")");

		this.save(transactionFrom);
		this.save(transactionTo);
	}

	public void createTransaction(int accountId, int userId, TransactionsForm transactionsForm) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);

		Transactions transaction = new Transactions();
		if (Double.parseDouble(transactionsForm.getAmount().replaceAll(",","")) <= 0)
			throw new RuntimeException("Amount must be greater than $0.00");
		transaction.setAmount(Double.parseDouble(transactionsForm.getAmount().replaceAll(",","")));
		transaction.setTransactionType(transactionsForm.getTransactionType());
		transaction.setAccount(account);
		account.setUser(user);
		save(transaction);
	}
}
