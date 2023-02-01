package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.data.Region;

@Transactional
public interface RegionRepository extends JpaRepository<Region, String>{

	//UPDATE tb_region r SET r.addr_gu = SUBSTRING_INDEX(r.addr_sg," ", -1), r.addr_sg = SUBSTRING_INDEX(r.addr_sg," ", 1)  WHERE r.addr_sg LIKE "% %"
	//#SELECT SUBSTRING_INDEX(r.addr_sg," ", 1) FROM tb_region r WHERE r.addr_sg LIKE "% %"
}
