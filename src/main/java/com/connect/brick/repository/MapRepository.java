package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.DpMaterial;
import com.connect.brick.model.DpPopular;
import com.connect.brick.model.data.ApartmentAll;

@Transactional
public interface MapRepository extends JpaRepository<ApartmentAll, Long> {
	List<ApartmentAll> findByNo(Long no);
	List<ApartmentAll> findByaddrSd(String sd);
	List<ApartmentAll> findByaddrSg(String sg);
	List<ApartmentAll> findByaddrGu(String gu);
	List<ApartmentAll> findByaddrEmd(String emd);
	List<ApartmentAll> findByaddrJb(String jb);
	List<ApartmentAll> findBynameApart(String apart);
	List<ApartmentAll> findAll();
}
