package com.connect.brick.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.connect.brick.model.access.Account;
import com.connect.brick.model.access.AccountExtendsUser;
import com.connect.brick.repository.AccountRepository;

@Service
@ComponentScan("com.connect.brick.config")
public class AccountService implements UserDetailsService {

	@Autowired
    private AccountRepository accountRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		Account account = accountRepository.findByEmail(id);
        return new AccountExtendsUser(account);
				
	}
	
	@Transactional
	public Account getAccountById(String id) throws UsernameNotFoundException {
		
		Account account = accountRepository.findByEmail(id);
        return account;
				
	}
	
	
}
