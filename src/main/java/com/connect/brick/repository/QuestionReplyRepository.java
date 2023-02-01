package com.connect.brick.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.QuestionReply;

@Transactional
public interface QuestionReplyRepository extends JpaRepository<QuestionReply, Long> {

}
