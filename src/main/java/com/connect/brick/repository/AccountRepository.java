package com.connect.brick.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.Image;
import com.connect.brick.model.access.Account;

@Transactional
public interface AccountRepository extends JpaRepository<Account, String>{
	
	Account findByEmail(String email);
	
}
