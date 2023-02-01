package com.connect.brick.model.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CD_COUNTRY")
public class Country {
	
	@Id
	@Column(name="country_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "country_name")
	private String countryName;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
	
}
