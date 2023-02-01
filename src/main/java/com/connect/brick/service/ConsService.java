package com.connect.brick.service;



import java.time.LocalDateTime;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.brick.model.ConsReview;

import com.connect.brick.model.Material;

import com.connect.brick.repository.ConsReviewRepository;
import com.connect.brick.repository.MaterialRepository;

@Service
public class ConsService {
	
	@Autowired
    private ConsReviewRepository consReviewRepository;
	
	
	@Autowired
    private MaterialRepository materialRepository;
	
	@Transactional
	public List<ConsReview> getConsReviewAll() {
		
		return consReviewRepository.findAllByOrderByConsReviewDateDesc();
	}
	
	@Transactional
	public ConsReview _reg_ConsReview(ConsReview consReview) {
		
		LocalDateTime now = LocalDateTime.now();
		
		consReview.setConsReviewDate(now);
		
		
		
		return consReviewRepository.save(consReview);
	}
	
	@Transactional
	public void _del_ConsReview(Long no) {
		consReviewRepository.deleteById(no);
	}
	
	@Transactional
	public ConsReview getConsReviewByNo(Long no) {
		return consReviewRepository.findConsReviewByconsReviewNo(no);
	}
	
	@Transactional
	public ConsReview _mod_ConsReview(ConsReview consReview) {
//		Long yetNo = consReview.getConsReviewNo();
		
//		ConsReview yet = getConsReviewByNo(yetNo);
//		
//		consReview.setConsReviewDate(yet.getConsReviewDate());
		
		
		
		return consReviewRepository.save(consReview);
	}
	
	
	
	@Transactional
	public List<Material> getMaterialAll() {
		
		return materialRepository.findAllByOrderByRegDateDesc();
	}
	
}
