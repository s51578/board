package com.connect.brick.model.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CD_CLASS_LG")
public class ClassLg {
	
	@Id
	@Column(name="class_lg_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "class_lg_name")
	private String className;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	
	
}
