package com.connect.brick.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.connect.brick.util.TimeUtils;

@Entity
@Table(name = "TB_QUESTION_REPLY")
public class QuestionReply {
	
	@Id
	@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@OneToOne
	@JoinColumn(name = "question_no", referencedColumnName = "question_no")
	private Question question;
	
	@Column(name = "writer")
	private String writer;
	
	@Column(name = "contents")
	private String contents;
	
	@Column(name = "reply_date")
	private LocalDateTime replyDate;
	
	@Column(name = "reply_update_date")
	private LocalDateTime replyUpdateDate;
	
	@Transient
	private String replyFormatDate;
	
	@Transient
	private String replyFormatUpdateDate;
	
	@PostLoad
	public void postLoad() {
		LocalDateTime posted = this.replyDate;
		
		this.replyFormatDate = TimeUtils.getTimeFormatDate(posted);
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(LocalDateTime replyDate) {
		this.replyDate = replyDate;
	}
	
	public LocalDateTime getReplyUpdateDate() {
		return replyUpdateDate;
	}

	public void setReplyUpdateDate(LocalDateTime replyUpdateDate) {
		this.replyUpdateDate = replyUpdateDate;
	}

	public String getReplyFormatDate() {
		return replyFormatDate;
	}

	public void setReplyFormatDate(String replyFormatDate) {
		this.replyFormatDate = replyFormatDate;
	}

	public String getReplyFormatUpdateDate() {
		return replyFormatUpdateDate;
	}

	public void setReplyFormatUpdateDate(String replyFormatUpdateDate) {
		this.replyFormatUpdateDate = replyFormatUpdateDate;
	}
	
	
}
