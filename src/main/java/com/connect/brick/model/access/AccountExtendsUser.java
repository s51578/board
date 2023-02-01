package com.connect.brick.model.access;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountExtendsUser extends User implements UserDetails {

	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;

	private String ip;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public AccountExtendsUser(Account account) {
		super(account.getEmail(), account.getPassword(), makeGrantedAuthorities(account.getRoles()));
	}

	private static List<GrantedAuthority> makeGrantedAuthorities(List<Role> roles) {
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		return list;
	} 


}