package com.ex.BankingApplication.repo;

import java.util.Optional;

import com.ex.BankingApplication.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long>{

	Optional<UserInfo> findByUsername(String username);

}
