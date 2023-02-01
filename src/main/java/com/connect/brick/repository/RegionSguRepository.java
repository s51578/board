package com.connect.brick.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.data.RegionSgu;

@Transactional
public interface RegionSguRepository extends JpaRepository<RegionSgu, String>{

	
	
}
