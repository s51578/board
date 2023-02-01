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

import com.connect.brick.model.material.MaterialContents;
import com.connect.brick.util.TimeUtils;

@Entity
@Table(name = "TB_CONTACT")
public class Contact {
	
	@Id
	@Column(name="no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@OneToOne
	@JoinColumn(name="material_contents_no")
	private MaterialContents mtContents;
	
	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_num")
	private String phoneNum;
	
	@Column(name = "memo")
	private String memo;
	
	@Column(name = "privacy")
	private Boolean privacy;
	
	@Column(name = "contact_date")
	private LocalDateTime contactDate;

	@Transient
	private String contactFormatDate;
	
	@PostLoad
	public void postLoad() {

		LocalDateTime posted = this.contactDate;

		this.contactFormatDate = TimeUtils.getTimeFormatDate(posted);
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}
	
	public MaterialContents getMtContents() {
		return mtContents;
	}

	public void setMtContents(MaterialContents mtContents) {
		this.mtContents = mtContents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Boolean privacy) {
		this.privacy = privacy;
	}

	public LocalDateTime getContactDate() {
		return contactDate;
	}

	public void setContactDate(LocalDateTime contactDate) {
		this.contactDate = contactDate;
	}

	public String getContactFormatDate() {
		return contactFormatDate;
	}

	public void setContactFormatDate(String contactFormatDate) {
		this.contactFormatDate = contactFormatDate;
	}
	
}
