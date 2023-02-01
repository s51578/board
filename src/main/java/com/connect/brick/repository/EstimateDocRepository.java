package com.connect.brick.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.EstimateDoc;

@Transactional
public interface EstimateDocRepository extends JpaRepository<EstimateDoc, Long>{
	
}
