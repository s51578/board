package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.Question;

@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long>{
	
	List<Question> findAllByOrderByQnaDateDesc();
	
}
