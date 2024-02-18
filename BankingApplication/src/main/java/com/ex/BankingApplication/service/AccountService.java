package com.ex.BankingApplication.service;

import com.ex.BankingApplication.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

  Account createAccount(Account a);
  List<Account> getAccountDetails();
  Optional<Account> getAccountById(Long id);
  Account deposit(Long id, double amount);
  Account withdraw(Long id, double amount);

}
