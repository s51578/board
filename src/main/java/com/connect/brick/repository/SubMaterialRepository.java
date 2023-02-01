package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.SubMaterial;
import com.connect.brick.model.code.ClassLg;

@Transactional
public interface SubMaterialRepository extends JpaRepository<SubMaterial, Long>{

	List<SubMaterial> findAllByOrderByRegDateDesc();
	SubMaterial findSubMaterialByNo(Long no);
	List<SubMaterial> findSubMaterialByClassLg(ClassLg cl);
}
