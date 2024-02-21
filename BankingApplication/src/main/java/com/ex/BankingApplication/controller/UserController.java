package com.ex.BankingApplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ex.BankingApplication.entity.User;
import com.ex.BankingApplication.service.UserService;
import com.ex.BankingApplication.service.UserServiceImpl;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/users")
public class UserController {
	
	 @Autowired
	    private UserService userService;
	 
	    @GetMapping("/")
	    public String getUser() {
	        return "userForm";
	    }

	    @GetMapping("/userform")
	    public String getUserForm() {
	        return "userForm";
	    }

	    @PostMapping("/api/user/create")
	    public String createUser(@ModelAttribute User user, Model model) {
	        userService.createUser(user);
	        model.addAttribute("user", user);
	        return "userForm";
	    }

//	    @GetMapping("/userdetails/{id}")
//	    public String getUserDetails(@PathVariable Long id, Model model) {
//	        Optional<User> userOptional = userService.getUserById(id);
//	        userOptional.ifPresent(user -> model.addAttribute("user", user));
//	        return userOptional.map(user -> "userList").orElse("userNotFound");
//	    }
	    
	    //to work this annotation we need to annotate method class with @EnableMthodSecurity
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    @GetMapping("/all")
	    public String getAll(Model model) {
	    	List<User> list = userService.getUserDetails();
	    	model.addAttribute("usersList", list);
	    	return "userList";
	    }
	    
	    @GetMapping("/userdetails/{id}")
	    @PreAuthorize("hasAuthority('ROLE_USER')")
	    public String getUserDetails(@PathVariable Long id, Model model) {
	        Optional<User> userOptional = userService.getUserById(id);
	        userOptional.ifPresent(user -> model.addAttribute("user", user));
	        return userOptional.isPresent() ? "userList" : "redirect:/users/userNotFound";
	    }
	    
	    @GetMapping("/userNotFound")
	    public String getNotFoundPage() {
	        
	        return "/userNotFound";
	    }
	    
}
