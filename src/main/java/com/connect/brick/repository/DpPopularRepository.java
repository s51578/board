package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.DpMaterial;
import com.connect.brick.model.DpPopular;

@Transactional
public interface DpPopularRepository extends JpaRepository<DpPopular, Long> {

//	Popular findByContents(Contents c);
	DpPopular findByDpMaterial(DpMaterial dm);
	List<DpPopular> findAllByOrderByRegDateDesc();
	List<DpPopular> findAllByOrderByPopularRank();
	List<DpPopular> findAllByOrderByPopularRankDesc();
	List<DpPopular> findTop4ByOrderByPopularRank();
	List<DpPopular> findTop8ByOrderByPopularRank();
	List<DpPopular> findAllByPopularRankGreaterThan(Integer rank);
	DpPopular findByPopularRank(int rank);
}
