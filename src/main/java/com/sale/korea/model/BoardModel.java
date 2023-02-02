package com.sale.korea.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sale.korea.utils.TimeUtils;

@Entity
@Table(name = "TB_BOARD")
public class BoardModel {
	
	@Id
	@Column(name = "number")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;	

	@Column(name = "date")
	private LocalDateTime date;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Transient
	private String FormatDate;
	
	public String getFormatDate() {
		return FormatDate;
	}

	public void setFormatDate(String FormatDate) {
		this.FormatDate = FormatDate;
	}

	@PostLoad
	public void postLoad() {
		LocalDateTime posted = this.date;
		this.FormatDate = TimeUtils.getTimeFormatDate(posted);
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	
	
	
}
