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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.connect.brick.model.Material;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable
//@DiscriminatorValue("mtDelv")
@Entity
@Table(name = "_MATERIAL_DELV")
public class MaterialDelv {
	
	@Id
	@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;
	
	@Column(name = "exc_purchase")
	private Boolean excPurchase;
	
	@Column(name = "how_delv")
	private String howDelv;
	
	@Column(name = "prepay_delv")
	private String prepayDelv;
	
	@Column(name = "pay")
	private Integer pay;

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

	public Boolean getExcPurchase() {
		return excPurchase;
	}

	public void setExcPurchase(Boolean excPurchase) {
		this.excPurchase = excPurchase;
	}

	public String getHowDelv() {
		return howDelv;
	}

	public void setHowDelv(String howDelv) {
		this.howDelv = howDelv;
	}

	public String getPrepayDelv() {
		return prepayDelv;
	}

	public void setPrepayDelv(String prepayDelv) {
		this.prepayDelv = prepayDelv;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	
	
}
