package com.ex.BankingApplication.service;

import com.ex.BankingApplication.entity.Account;
import com.ex.BankingApplication.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository acntRepo;

    @Override
    public Account createAccount(Account a) {
        return acntRepo.save(a);
    }

    @Override
    public List<Account> getAccountDetails() {
        return acntRepo.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
    	return acntRepo.findById(id);
    }

	@Override
	public Account deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = getAccountById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		account.setBalance(account.getBalance()+amount);
		return acntRepo.save(account);
	}

	@Override
	public Account withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = getAccountById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		account.setBalance(account.getBalance() - amount);
		return acntRepo.save(account);
	}
}
