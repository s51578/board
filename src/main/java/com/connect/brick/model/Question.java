package com.connect.brick.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.connect.brick.util.TimeUtils;

@Entity
@Table(name = "TB_QUESTION")
public class Question {
	
	@Id
	@Column(name="question_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "question", orphanRemoval = true)
	private QuestionReply questionReply;
	
	@Column(name = "writer")
	private String writer;

	@Column(name = "email")
	private String email;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;
	
	//@ColumnTransformer(write = "HEX(AES_ENCRYPT(?, 'password'))", read = "AES_DECRYPT(UNHEX(password),'password')")
	@Column(name = "password")
	private String password;
	
	@Column(name = "qna_date")
	private LocalDateTime qnaDate;

	@Transient
	private String qnaFormatDate;
	
	@PostLoad
	public void postLoad() {

		LocalDateTime posted = this.qnaDate;

		this.qnaFormatDate = TimeUtils.getTimeFormatDate(posted);
	}
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getQnaDate() {
		return qnaDate;
	}

	public void setQnaDate(LocalDateTime qnaDate) {
		this.qnaDate = qnaDate;
	}

	public String getQnaFormatDate() {
		return qnaFormatDate;
	}

	public void setQnaFormatDate(String qnaFormatDate) {
		this.qnaFormatDate = qnaFormatDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public QuestionReply getQuestionReply() {
		return questionReply;
	}

	public void setQuestionReply(QuestionReply questionReply) {
		this.questionReply = questionReply;
	}
	
}
