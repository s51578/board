package com.connect.brick.repository.code;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.code.AptExc;
import com.connect.brick.model.code.ClassLg;
import com.connect.brick.model.data.ApartmentEtc;

@Transactional
public interface AptExcRepository extends JpaRepository<AptExc, Long>{
	
	public boolean existsByExcName(String word);
	
}
