package com.ex.BankingApplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

    @Bean
    UserDetailsService userService(PasswordEncoder encoder) {
		
		UserDetails admin = User.withUsername("nikita").password(encoder.encode("Bhoyar@456678")).roles("ADMIN").build(); 
		UserDetails user = User.withUsername("sagar").password(encoder.encode("Sagar@4589")).roles("USER").build(); 
		
		return new InMemoryUserDetailsManager(admin, user);
	}
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                    .requestMatchers("/users/**").permitAll()
                    .requestMatchers("/accounts/**").authenticated()
                    .and()
                .formLogin()
                    .and()
                .build();
    }

    
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	
//    	return http.csrf().disable()
//    	.authorizeHttpRequests()
//    	.requestMatchers("/users/**").permitAll()
//    	.and().authorizeHttpRequests().requestMatchers("/accounts/**").authenticated()
//    	.and().formLogin().and().build();
//    }

    @Bean
    PasswordEncoder pswdEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
}

//but here to write pswd like this not safe
//@Bean
//public UserDetailsService userService() {
//	
//	UserDetails admin = User.withUsername("nikita").password("Bhoyar@456678").roles("ADMIN").build(); 
//	UserDetails user = User.withUsername("sagar").password("Sagar@89456").roles("USER").build(); 
//
//}
