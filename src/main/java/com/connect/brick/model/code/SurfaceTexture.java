package com.connect.brick.model.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CD_SURFACE_TEXTURE")
public class SurfaceTexture {
	
	@Id
	@Column(name="sf_texture_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "sf_texture_name")
	private String sfTextureName;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getSfTextureName() {
		return sfTextureName;
	}

	public void setSfTextureName(String sfTextureName) {
		this.sfTextureName = sfTextureName;
	}

	
}
