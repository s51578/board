package com.sale.korea.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sale.korea.model.BoardModel;



@Transactional
public interface BoardRepository extends JpaRepository<BoardModel, Long>{
	
	List<BoardModel> findAllByOrderByDateDesc();
	BoardModel findBoardModelBynumber(Long no);
}
