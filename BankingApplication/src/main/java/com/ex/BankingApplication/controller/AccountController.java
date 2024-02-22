package com.ex.BankingApplication.controller;

import com.ex.BankingApplication.entity.Account;
import com.ex.BankingApplication.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImpl acntServiceImpl;

    @GetMapping("/all")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Account> getAll() {
        return acntServiceImpl.getAccountDetails();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create")
    public String saveAccounts(@RequestBody Account a) {
     acntServiceImpl.createAccount(a);
     return "One more account is created";
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Account getAccountById(@PathVariable Long id) {
    	return acntServiceImpl.getAccountById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/{id}/deposit")
    public Account addDeposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
    	//@RequestBody Map<String, Double> request: This annotation binds the HTTP request body to a Map<String, Double>. 
    	//The request body is expected to be a JSON object with a key "amount" representing the amount to withdraw.
    	Double amount = request.get("amount");
    	return acntServiceImpl.deposit(id, amount);
    }
    
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/{id}/withdraw")
    public Account withdrawAmount(@PathVariable Long id, @RequestBody Map<String, Double> request) {
    	Double amount = request.get("amount");
    	return acntServiceImpl.withdraw(id, amount);
    }
}
