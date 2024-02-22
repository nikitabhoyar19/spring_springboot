package com.ex.BankingApplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

    @Bean
    UserDetailsService userService() {
    	return new UserServiceImpl();
	}

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                // ...
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/accounts/**").hasRole("ADMIN")
		).formLogin(withDefaults());

        return http.build();
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/accounts/**").permitAll()
//                                .requestMatchers("/users/**").hasAuthority("ADMIN").anyRequest().authenticated()
//                )
//                .formLogin(withDefaults());
//
//        // Disable CSRF for specific requests
//        http.csrf(csrf -> csrf.ignoringRequestMatchers("/users/**"));
//
//        return http.build();
//    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService());
        authenticationProvider.setPasswordEncoder(pswdEncoder());
        return authenticationProvider;
    }


    @Bean
    PasswordEncoder pswdEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
}

//2nd
//basic without using DB
//@Bean 
//UserDetailsService userService(PasswordEncoder encoder) {
//	UserDetails admin = User.withUsername("nikita").password(encoder.encode("Bhoyar@456678")).roles("ADMIN").build(); 
//	UserDetails user = User.withUsername("sagar").password(encoder.encode("Sagar@4589")).roles("USER").build(); 
//	return new InMemoryUserDetailsManager(admin, user);
	
//	return new UserServiceImpl();
//}

// 1st
//but here to write pswd like this not safe
//@Bean
//public UserDetailsService userService() {
//	
//	UserDetails admin = User.withUsername("nikita").password("Bhoyar@456678").roles("ADMIN").build(); 
//	UserDetails user = User.withUsername("sagar").password("Sagar@89456").roles("USER").build(); 
//
//}
