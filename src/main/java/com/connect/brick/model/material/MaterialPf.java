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
//@DiscriminatorValue("mtPf")
@Entity
@Table(name = "_MATERIAL_PF")
public class MaterialPf {
	
	@Id
	@Column(name ="no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;
	
	
	@Column(name = "waterabsrp")
	private Boolean waterAbsrp;
	
	@Column(name = "water_absrp_rate")
	private BigDecimal waterAbsrpRate;
	
	@Column(name = "resist")
	private String resist;
	
	@Column(name = "certified")
	private String certified;

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

	public BigDecimal getWaterAbsrpRate() {
		return waterAbsrpRate;
	}

	public void setWaterAbsrpRate(BigDecimal waterAbsrpRate) {
		this.waterAbsrpRate = waterAbsrpRate;
	}

	public String getResist() {
		return resist;
	}

	public void setResist(String resist) {
		this.resist = resist;
	}

	public String getCertified() {
		return certified;
	}

	public void setCertified(String certified) {
		this.certified = certified;
	}

	public Boolean getWaterAbsrp() {
		return waterAbsrp;
	}

	public void setWaterAbsrp(Boolean waterAbsrp) {
		this.waterAbsrp = waterAbsrp;
	}
	
	
}
