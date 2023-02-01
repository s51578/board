package com.connect.brick.model.material;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.connect.brick.model.Material;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable
@Entity
@Table(name ="_MATERIAL_COLOR")
public class MaterialColor {
	
	@Id
	@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "material_no")
	private Material material;
	
	@Column(name = "color_hex")
	private String colorHex;

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

	public String getColorHex() {
		return colorHex;
	}

	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}
	
	
	
}
