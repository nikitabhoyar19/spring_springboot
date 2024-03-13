package com.ex.BankingApplication.controller;

import java.util.List;
import java.util.Optional;

import com.ex.BankingApplication.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ex.BankingApplication.security.UserServiceImpl;

import org.springframework.ui.Model;

@RestController
@RequestMapping("/users")
public class UserController {
	
	 @Autowired
	    private UserServiceImpl userService;
	 
	    @GetMapping("/")
	    public String getUser() {
	        return "userForm";
	    }

	    @GetMapping("/userform")
	    public String getUserForm() {
	        return "user";
	    }

		@PostMapping("/create")
		public String createUsers(@RequestBody UserInfo user) {
			userService.createUser(user);
			return "user created";
		}

		@GetMapping("/all")
		public List<UserInfo> getAllUsers() {
			return userService.getUserDetails();
		}

	@GetMapping("/{id}")
	public Optional<UserInfo> getUserDetails(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	@PostMapping("/api/user/create")
	public String createUser(@ModelAttribute UserInfo user, Model model) {
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
//	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	    @GetMapping("/all")
//	    public String getAll(Model model) {
//	    	List<UserInfo> list = userService.getUserDetails();
//	    	model.addAttribute("usersList", list);
//	    	return "userList";
//	    }
	    
//	    @GetMapping("/userdetails/{id}")
//	    @PreAuthorize("hasAuthority('ROLE_USER')")
//	    public String getUserDetails(@PathVariable Long id, Model model) {
//	        Optional<UserInfo> userOptional = userService.getUserById(id);
//	        userOptional.ifPresent(user -> model.addAttribute("user", user));
//	        return userOptional.isPresent() ? "userList" : "redirect:/users/userNotFound";
//	    }
	    
	    @GetMapping("/userNotFound")
	    public String getNotFoundPage() {
	        
	        return "/userNotFound";
	    }
	    
}
