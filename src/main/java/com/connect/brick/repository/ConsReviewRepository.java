package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.ConsReview;



@Transactional
public interface ConsReviewRepository extends JpaRepository<ConsReview, Long>{
	
	List<ConsReview> findAllByOrderByConsReviewDateDesc();
	ConsReview findConsReviewByconsReviewNo(Long no);
}
