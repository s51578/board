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
@Table(name = "TB_CUSTOMER")
public class Customer {
	
	@Id
	@Column(name="no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;
	
	@Column(name = "address_detail")
	private String addressDetail;

	@Column(name = "phone_num")
	private String phoneNum;

	@Column(name = "email")
	private String email;
	
	@Column(name = "funnel")
	private String funnel;
	
	@Column(name = "privacy")
	private Boolean privacy;
	
	@Column(name = "reg_date", updatable=false)
	private LocalDateTime regDate;

	@Transient
	private String regFormatDate;
	
	@PostLoad
	public void postLoad() {

		LocalDateTime posted = this.regDate;

		this.regFormatDate = TimeUtils.getTimeFormatDate(posted);
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFunnel() {
		return funnel;
	}

	public void setFunnel(String funnel) {
		this.funnel = funnel;
	}

	public Boolean getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Boolean privacy) {
		this.privacy = privacy;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getRegFormatDate() {
		return regFormatDate;
	}

	public void setRegFormatDate(String regFormatDate) {
		this.regFormatDate = regFormatDate;
	}

	
}
