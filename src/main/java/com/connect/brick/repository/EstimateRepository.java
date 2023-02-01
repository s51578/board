package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.Estimate;

@Transactional
public interface EstimateRepository extends JpaRepository<Estimate, Long>{
	
	List<Estimate> findAllByOrderByEstimateDateDesc();
	
}
