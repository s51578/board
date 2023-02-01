package com.connect.brick.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.CostSheet;

@Transactional
public interface CostSheetRepository extends JpaRepository<CostSheet, Long>{
	
	CostSheet findByIsActive(boolean isActive);
	
}
