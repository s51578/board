package com.connect.brick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "_ESTIMATE_SUBS")
public class EstimateSub {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "estimate_no")
	private Estimate estimate;
	
//	@Transient
//	private Long subsNo;
	
	@OneToOne
	@JoinColumn(name = "subs_no")
	private SubMaterial subMaterial;
	
	@Column(name = "sub_box_amt")
	private Integer subBoxAmount;
	
	@Column(name = "sub_box_cost")
	private Integer subBoxCost;
	
	@Column(name = "sub_box_amt_cost")
	private Integer subBoxAmountCost;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Estimate getEstimate() {
		return estimate;
	}

	public void setEstimate(Estimate estimate) {
		this.estimate = estimate;
	}

	public SubMaterial getSubMaterial() {
		return subMaterial;
	}

	public void setSubMaterial(SubMaterial subMaterial) {
		this.subMaterial = subMaterial;
	}

	public Integer getSubBoxAmount() {
		return subBoxAmount;
	}

	public void setSubBoxAmount(Integer subBoxAmount) {
		this.subBoxAmount = subBoxAmount;
	}

	public Integer getSubBoxCost() {
		return subBoxCost;
	}

	public void setSubBoxCost(Integer subBoxCost) {
		this.subBoxCost = subBoxCost;
	}

	public Integer getSubBoxAmountCost() {
		return subBoxAmountCost;
	}

	public void setSubBoxAmountCost(Integer subBoxAmountCost) {
		this.subBoxAmountCost = subBoxAmountCost;
	}

	
	
}
