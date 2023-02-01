package com.connect.brick.model.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CD_MAIN_COLOR")
public class MainColor {
	
	@Id
	@Column(name="main_color_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "main_color_name")
	private String mainColorName;

	@Column(name = "main_color_hex")
	private String mainColorHex;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getMainColorName() {
		return mainColorName;
	}

	public void setMainColorName(String mainColorName) {
		this.mainColorName = mainColorName;
	}

	public String getMainColorHex() {
		return mainColorHex;
	}

	public void setMainColorHex(String mainColorHex) {
		this.mainColorHex = mainColorHex;
	}
	
}
