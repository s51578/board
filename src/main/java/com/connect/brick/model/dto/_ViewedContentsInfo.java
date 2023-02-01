package com.connect.brick.model.dto;

import com.connect.brick.model.DpMaterial;

public class _ViewedContentsInfo {
	
	private Long dpNo;
	private DpMaterial dpMaterial;
	private String viewedDate;
	
	public Long getDpNo() {
		return dpNo;
	}
	public void setDpNo(Long dpNo) {
		this.dpNo = dpNo;
	}
	public DpMaterial getDpMaterial() {
		return dpMaterial;
	}
	public void setDpMaterial(DpMaterial dpMaterial) {
		this.dpMaterial = dpMaterial;
	}
	public String getViewedDate() {
		return viewedDate;
	}
	public void setViewedDate(String viewedDate) {
		this.viewedDate = viewedDate;
	}

	
	
}
