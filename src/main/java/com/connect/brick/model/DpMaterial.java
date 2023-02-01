package com.connect.brick.model;

import java.time.LocalDateTime;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.connect.brick.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable
@Entity
@Table(name = "DP_MATERIAL")
public class DpMaterial {

	@Id
	@Column(name = "dp_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dpNo;

	@OneToOne
	@JoinColumn(name = "material_no")
	private Material material;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "dpMaterial", orphanRemoval = true)
	private DpPopular dpPopular;
	
	@Column(name = "reg_date", updatable=false)
	private LocalDateTime regDate;
	
	@Transient
	private String regFormatDate;

	@PostLoad
	public void postLoad() {
		LocalDateTime posted = this.regDate;
		this.regFormatDate = TimeUtils.getTimeFormatDate(posted);
	}

	public Long getDpNo() {
		return dpNo;
	}


	public void setDpNo(Long dpNo) {
		this.dpNo = dpNo;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public DpPopular getDpPopular() {
		return dpPopular;
	}

	public void setDpPopular(DpPopular dpPopular) {
		this.dpPopular = dpPopular;
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
