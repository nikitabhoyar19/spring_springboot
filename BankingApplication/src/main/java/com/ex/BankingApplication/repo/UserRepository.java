package com.ex.BankingApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.BankingApplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
