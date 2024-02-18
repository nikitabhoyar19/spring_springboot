package com.ex.BankingApplication.controller;

import com.ex.BankingApplication.entity.Account;
import com.ex.BankingApplication.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private AccountServiceImpl acntServiceImpl;

    @GetMapping("/accounts")
    public List<Account> getAll() {
        return acntServiceImpl.getAccountDetails();
    }

    @PostMapping("/accounts")
    public String saveAccounts(@RequestBody Account a) {
     acntServiceImpl.createAccount(a);
     return "One more account is created";
    }
    
    @GetMapping("/accounts/{id}")
    public Account getAccountById(@PathVariable Long id) {
    	return acntServiceImpl.getAccountById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }
    
	// On Browser http://localhost:8080/empp/empnames/nikita?amount=50000
    @PostMapping("/accounts/{id}/deposit")
    public Account addDeposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
    	//@RequestBody Map<String, Double> request: This annotation binds the HTTP request body to a Map<String, Double>. 
    	//The request body is expected to be a JSON object with a key "amount" representing the amount to withdraw.
    	Double amount = request.get("amount");
    	return acntServiceImpl.deposit(id, amount);
    }
    
    @PostMapping("/accounts/{id}/withdraw")
    public Account withdrawAmount(@PathVariable Long id, @RequestBody Map<String, Double> request) {
    	Double amount = request.get("amount");
    	return acntServiceImpl.withdraw(id, amount);
    }
}
