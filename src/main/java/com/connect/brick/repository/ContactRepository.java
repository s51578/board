package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.Contact;

@Transactional
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	List<Contact> findAllByOrderByContactDateDesc();
	
}
