package com.connect.brick.model.dto;

import com.connect.brick.model.EstimateDoc;

public class _EstimateDTO {

	private Long orderNo;
	private Long mtContentsNo;
	private Long costSheetNo;
	private Double areaCons;
	
	private EstimateDoc estimateDoc;
	
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getMtContentsNo() {
		return mtContentsNo;
	}
	public void setMtContentsNo(Long mtContentsNo) {
		this.mtContentsNo = mtContentsNo;
	}
	public Long getCostSheetNo() {
		return costSheetNo;
	}
	public void setCostSheetNo(Long costSheetNo) {
		this.costSheetNo = costSheetNo;
	}
	public Double getAreaCons() {
		return areaCons;
	}
	public void setAreaCons(Double areaCons) {
		this.areaCons = areaCons;
	}
	public EstimateDoc getEstimateDoc() {
		return estimateDoc;
	}
	public void setEstimateDoc(EstimateDoc estimateDoc) {
		this.estimateDoc = estimateDoc;
	}
	
}
