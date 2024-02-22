package com.ex.BankingApplication.security;

import java.util.List;
import java.util.Optional;

import com.ex.BankingApplication.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ex.BankingApplication.repo.UserRepository;
import com.ex.BankingApplication.security.UserInfoUserDetails;

@Service
public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	private PasswordEncoder pswdEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<UserInfo> userInfo = userRepo.findByUsername(username);

		return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not Found"));

	}

	public UserInfo createUser(UserInfo user) {
		// TODO Auto-generated method stub
		user.setPassword(pswdEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	public List<UserInfo> getUserDetails() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	public Optional<UserInfo> getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepo.findById(userId);

	}

}
