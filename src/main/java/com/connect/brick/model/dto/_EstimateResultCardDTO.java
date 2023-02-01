package com.connect.brick.model.dto;

import com.connect.brick.model.Estimate;
import com.connect.brick.model.Material;

public class _EstimateResultCardDTO {

	private Estimate estimate;
	private Material material;
	
	public Estimate getEstimate() {
		return estimate;
	}
	public void setEstimate(Estimate estimate) {
		this.estimate = estimate;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
}
