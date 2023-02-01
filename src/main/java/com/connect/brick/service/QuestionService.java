package com.connect.brick.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.connect.brick.model.Question;
import com.connect.brick.model.QuestionReply;
import com.connect.brick.repository.QuestionReplyRepository;
import com.connect.brick.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuestionReplyRepository questionReplyRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Question> getQuestionAll() {

		return questionRepository.findAllByOrderByQnaDateDesc();
	}

	// 글 보기 기능
	public Question getQuestion(Long no) {
		
		return questionRepository.findById(no).get();
	}

	// 글 쓰기 기능
	public Question regQuestion(Question qna) {

		LocalDateTime now = LocalDateTime.now();

		qna.setQnaDate(now);
		qna.setPassword(passwordEncoder.encode(qna.getPassword()));

		return questionRepository.save(qna);
	}

	// 글 삭제 기능
	public void deleteQuestion(Long no) {

		questionRepository.deleteById(no);
	}

	// 비밀글 비밀번호 체크
	public boolean checkPwd(Long no, String password) {

		// Question의 객체 board를 만들고 board안에 JPA함수를 통해 얻은 값을 넣어줌
		Question board = questionRepository.findById(no).get();

		if (board != null) {

			// boolean 객체 선언후 false값 할당
			boolean check = false;
			/*
			 * System.out.println(board.getPassword()); System.out.println(
			 * "#############################################################");
			 * System.out.println(board.getPassword()); System.out.println(
			 * "#############################################################");
			 * System.out.println(board.getPassword());
			 */
			// 선언된 check 객채에 SpringSecurity에서 제공하는 암호 복호하 기능의 결과를 boolean값으로 넣어줌 true일시
			// 비밀번호가 맞다는것이고 false일시 틀리다는 의미
			// if 조건문에서는 순서대로 체크하기 때문에 첫번째 값이 Null이면 뒤에 Null 조건을 걸어도 오류가 발생하기 때문에
			// 항상 값이 넘어오는 예외를 걸때는 Null 조건을 맨 앞으로 해야 함
			if (board.getPassword() != null && !board.getPassword().equals(""))
				check = passwordEncoder.matches(password, board.getPassword());
			else
				check = false;
			// board가 Null 아닌 경우에만 check의 값 변경
			if (check)
				check = true;
			else
				check = false;

			return check;

		} else
			return false;

	}

	public QuestionReply getAnswer(Long no) {

		return questionReplyRepository.findById(no).get();
	}

	public QuestionReply regAnswer(QuestionReply reply) {

		LocalDateTime now = LocalDateTime.now();

		reply.setReplyDate(now);
		reply.setWriter("관리자");

		return questionReplyRepository.save(reply);
	}
	
	public QuestionReply modReply(QuestionReply reply) {
		
		LocalDateTime now = LocalDateTime.now();
		
		reply.setReplyUpdateDate(now);
		
		return questionReplyRepository.save(reply);
		
	}
}
