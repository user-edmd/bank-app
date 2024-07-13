package com.edmond.bank.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import com.edmond.bank.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edmond.bank.dao.TransactionsRepository;
import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;

@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	private TransactionsRepository transactionsRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	public Optional<Transactions> findById(int id) {
		return transactionsRepository.findById(id);
	}

	public Transactions save(Transactions transactions) {
		Optional<Account> account = accountService.findById(transactions.getAccountId());
		if (account.isEmpty())
			throw new RuntimeException("Cannot find account");

		transactions.setAccount(account.get());

		if (transactions.getTransactionType().equalsIgnoreCase("withdraw")) {
			if (account.get().getAccountBalance() < transactions.getAmount()) {
				throw new RuntimeException("Not enough balance in account to withdraw.");
			}
			transactions.setAmount(transactions.getAmount() * -1);
		}

		if (transactions.getDate() == null)
			transactions.setDate(LocalDateTime.now(ZoneOffset.UTC));
		return transactionsRepository.save(transactions);
	}

	public void deleteById(int id) {
		Optional<Transactions> transaction = transactionsRepository.findById(id);
		if (transaction.isPresent())
			transactionsRepository.deleteById(id);
	}

	public void transferBetweenAccounts(Account accountFrom, Account accountTo, Double amount) {
		Optional<User> userFrom = userService.findById(accountFrom.getUserId());
		Optional<User> userTo = userService.findById(accountTo.getUserId());

		if (userFrom.isEmpty() || userTo.isEmpty())
			throw new RuntimeException("Unable to find user");

		if (amount <= 0)
			throw new RuntimeException("Transfer amount must be greater than zero");

		if (accountFrom.getId() == accountTo.getId())
			throw new RuntimeException("Cannot transfer to same account");

		if (amount > accountFrom.getAccountBalance())
			throw new RuntimeException("Unable to transfer due to insufficient funds in account (..." + accountFrom.lastFourDigitsAcctNumber() + ")");

		accountFrom.setUser(userFrom.get());
		accountTo.setUser(userTo.get());

		Transactions transactionFrom = Transactions.builder()
				.account(accountFrom)
				.accountId(accountFrom.getId())
				.amount(amount * -1)
				.transactionType("Transfer to " + accountTo.getAccountType() +
						" Account (..." + accountTo.lastFourDigitsAcctNumber() + ") " +
						accountTo.getUser().getFirstName().toUpperCase().charAt(0) + ". " +
						accountTo.getUser().getLastName().toUpperCase())
				.build();

		Transactions transactionTo = Transactions.builder()
				.account(accountTo)
				.accountId(accountTo.getId())
				.amount(amount)
				.transactionType("Transfer from " + accountFrom.getAccountType() +
						" Account (..." + accountFrom.lastFourDigitsAcctNumber() + ") " +
						accountFrom.getUser().getFirstName().toUpperCase().charAt(0) + ". " +
						accountFrom.getUser().getLastName().toUpperCase())
				.build();

		save(transactionFrom);
		save(transactionTo);
	}

	@Override
	public Page<Transactions> findByAccountId(int accountId, Pageable pageable) {
		return this.transactionsRepository.findTransactionsByAccountId(accountId, pageable);
	}

	public Page<Transactions> findAll(Pageable pageable) {
		return this.transactionsRepository.findAll(pageable);
	}

	public List<Transactions> findAll() {
		return this.transactionsRepository.findAll();
	}
}
