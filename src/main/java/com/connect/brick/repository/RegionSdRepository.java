package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.data.RegionSd;

@Transactional
public interface RegionSdRepository extends JpaRepository<RegionSd, String>{

	//INSERT INTO tb_region_sd (addr_sd, rcode)
	//SELECT DISTINCT r.addr_sd, substring(r.rcode, 1, 2) FROM tb_region r WHERE substring(r.rcode, 3) = "00000000"
	
	
	List<RegionSd> findAllByOrderBySortNumAsc();
}
