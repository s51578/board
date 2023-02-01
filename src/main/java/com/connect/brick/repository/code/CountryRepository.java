package com.connect.brick.repository.code;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.code.Country;

@Transactional
public interface CountryRepository extends JpaRepository<Country, Long>{
	
}
