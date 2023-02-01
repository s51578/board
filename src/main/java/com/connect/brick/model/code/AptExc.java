package com.connect.brick.model.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CD_APT_EXC")
public class AptExc {
	
	@Id
	@Column(name="no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "exc_name")
	private String excName;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getExcName() {
		return excName;
	}

	public void setExcName(String excName) {
		this.excName = excName;
	}

	
}
