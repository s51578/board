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
import com.connect.brick.model.code.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable
//@DiscriminatorValue("mtDtd")
@Entity
@Table(name ="_MATERIAL_DTD")
public class MaterialDtd {
	
	@Id
	@Column(name ="no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;
	
	@Column(name ="dtd_com_no")
	private String dtdComNo;
	
	//@Column(name ="phy_folder_path")
	//private String phyFolderPath;
	
	@Column(name ="dtd_code")
	private String dtdCode;
	
	@Column(name ="dtd_mt_name")
	private String dtdMtName;
	
	@Column(name ="dtd_mt_desc")
	private String dtdMtDesc;
	
	@Column(name ="dtd_com_url")
	private String dtdComUrl;
	
	@Column(name ="mfc_com_no")
	private String mfcComNo;
	
	@Column(name ="brand_name")
	private String brandName;
	
	@ManyToOne
	@JoinColumn(name = "country_no")
	private Country country;
	
//	@Column(name ="country")
//	private String country;
	
	@Column(name ="warehouse")
	private String wareHouse;

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

	public String getDtdComNo() {
		return dtdComNo;
	}

	public void setDtdComNo(String dtdComNo) {
		this.dtdComNo = dtdComNo;
	}

	public String getDtdCode() {
		return dtdCode;
	}

	public void setDtdCode(String dtdCode) {
		this.dtdCode = dtdCode;
	}

	public String getDtdMtName() {
		return dtdMtName;
	}

	public void setDtdMtName(String dtdMtName) {
		this.dtdMtName = dtdMtName;
	}

	public String getDtdMtDesc() {
		return dtdMtDesc;
	}

	public void setDtdMtDesc(String dtdMtDesc) {
		this.dtdMtDesc = dtdMtDesc;
	}

	public String getDtdComUrl() {
		return dtdComUrl;
	}

	public void setDtdComUrl(String dtdComUrl) {
		this.dtdComUrl = dtdComUrl;
	}

	public String getMfcComNo() {
		return mfcComNo;
	}

	public void setMfcComNo(String mfcComNo) {
		this.mfcComNo = mfcComNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	
	
}
