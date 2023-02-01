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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.connect.brick.model.Material;
import com.connect.brick.model.code.MainColor;
import com.connect.brick.model.code.SurfaceTexture;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable
//@DiscriminatorValue("mtSpec")
@Entity
@Table(name = "_MATERIAL_SPEC")
public class MaterialSpec {
	
	@Id
	@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;
	
	@Column(name = "fullbody")
	private Boolean fullbody;
	
	@Column(name = "non_slip")
	private Boolean nonslip;
	
	@Column(name = "surface")
	private String surface;

	@ManyToOne
	@JoinColumn(name = "surface_texture_no")
	private SurfaceTexture surfaceTexture;
	
//	@Column(name = "surface_texture")
//	private String surfaceTexture;
	
	@Column(name = "pattern")
	private String pattern;
	
	@Column(name = "pattern_num")
	private Long patternNum;
	
	@ManyToOne
	@JoinColumn(name = "main_color_no")
	private MainColor mainColor;
	
//	@Column(name = "color")
//	private String color;

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

	public Boolean getFullbody() {
		return fullbody;
	}

	public void setFullbody(Boolean fullbody) {
		this.fullbody = fullbody;
	}

	public Boolean getNonslip() {
		return nonslip;
	}

	public void setNonslip(Boolean nonslip) {
		this.nonslip = nonslip;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public SurfaceTexture getSurfaceTexture() {
		return surfaceTexture;
	}

	public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
		this.surfaceTexture = surfaceTexture;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Long getPatternNum() {
		return patternNum;
	}

	public void setPatternNum(Long patternNum) {
		this.patternNum = patternNum;
	}

	public MainColor getMainColor() {
		return mainColor;
	}

	public void setMainColor(MainColor mainColor) {
		this.mainColor = mainColor;
	}

	
}
