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
//@DiscriminatorValue("mtPkg")
@Entity
@Table(name = "_MATERIAL_PKG")
public class MaterialPkg {
	
	@Id
	@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;
	
	@Column(name = "pcs_box")
	private Long pcsBox;
	
	@Column(name = "kg_box")
	private BigDecimal kgBox;
	
	@Column(name = "m2_box")
	private BigDecimal meter2Box;
	
	@Column(name = "box_plt")
	private Long boxPlt;

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

	public Long getPcsBox() {
		return pcsBox;
	}

	public void setPcsBox(Long pcsBox) {
		this.pcsBox = pcsBox;
	}

	public BigDecimal getKgBox() {
		return kgBox;
	}

	public void setKgBox(BigDecimal kgBox) {
		this.kgBox = kgBox;
	}

	public BigDecimal getMeter2Box() {
		return meter2Box;
	}

	public void setMeter2Box(BigDecimal meter2Box) {
		this.meter2Box = meter2Box;
	}

	public Long getBoxPlt() {
		return boxPlt;
	}

	public void setBoxPlt(Long boxPlt) {
		this.boxPlt = boxPlt;
	}
	
	
}
