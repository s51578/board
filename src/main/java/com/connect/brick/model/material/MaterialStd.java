package com.connect.brick.model.material;

import java.math.BigDecimal;

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
//@DiscriminatorValue("mtStd")
@Entity
@Table(name = "_MATERIAL_STD")
public class MaterialStd {
	
	@Id
	@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;
	
	@Column(name = "size_w")
	private Long sizeW;
	
	@Column(name = "size_h")
	private Long sizeH;
	
	@Column(name = "thick")
	private BigDecimal thick;
	
	@Column(name = "samp_size_w")
	private Long sampSizeW;
	
	@Column(name = "samp_size_h")
	private Long sampSizeH;

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

	public Long getSizeW() {
		return sizeW;
	}

	public void setSizeW(Long sizeW) {
		this.sizeW = sizeW;
	}

	public Long getSizeH() {
		return sizeH;
	}

	public void setSizeH(Long sizeH) {
		this.sizeH = sizeH;
	}

	public BigDecimal getThick() {
		return thick;
	}

	public void setThick(BigDecimal thick) {
		this.thick = thick;
	}

	public Long getSampSizeW() {
		return sampSizeW;
	}

	public void setSampSizeW(Long sampSizeW) {
		this.sampSizeW = sampSizeW;
	}

	public Long getSampSizeH() {
		return sampSizeH;
	}

	public void setSampSizeH(Long sampSizeH) {
		this.sampSizeH = sampSizeH;
	}
	
	
}
