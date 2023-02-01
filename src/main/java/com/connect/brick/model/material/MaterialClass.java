package com.connect.brick.model.material;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.connect.brick.model.Material;
import com.connect.brick.model.code.ClassLg;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable
//@DiscriminatorValue("mtClass")
@Entity
@Table(name ="_MATERIAL_CLASS")
public class MaterialClass {
	
	@Id
	@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;
	
//	@Column(name = "class_lg")
//	private String classLg;
	
	@ManyToOne
	@JoinColumn(name = "class_lg_no")
	private ClassLg classLg;
	
	@Column(name = "class_md")
	private String classMd;
	
	@Column(name = "class_sm")
	private String classSm;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public ClassLg getClassLg() {
		return classLg;
	}

	public void setClassLg(ClassLg classLg) {
		this.classLg = classLg;
	}

	public String getClassMd() {
		return classMd;
	}

	public void setClassMd(String classMd) {
		this.classMd = classMd;
	}

	public String getClassSm() {
		return classSm;
	}

	public void setClassSm(String classSm) {
		this.classSm = classSm;
	}
	
	
}
