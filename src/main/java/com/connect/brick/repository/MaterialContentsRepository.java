package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.material.MaterialContents;

@Transactional
public interface MaterialContentsRepository extends JpaRepository<MaterialContents, Long>{
	List<MaterialContents> findByEstimateTileRankBetweenOrderByEstimateTileRank(int start,int end);
}
