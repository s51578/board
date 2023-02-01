package com.connect.brick.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.data.RegionSg;

@Transactional
public interface RegionSgRepository extends JpaRepository<RegionSg, String>{

	//INSERT INTO tb_region_sg (addr_sg, rcode)
	//SELECT DISTINCT r.addr_sg, substring(r.rcode, 1, 4) FROM tb_region r WHERE substring(r.rcode, 3, 2) != "00" AND substring(r.rcode, 5) = "000000"
	
	
}
