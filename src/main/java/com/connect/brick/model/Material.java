package com.connect.brick.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.connect.brick.model.material.MaterialClass;
import com.connect.brick.model.material.MaterialColor;
import com.connect.brick.model.material.MaterialContents;
import com.connect.brick.model.material.MaterialDelv;
import com.connect.brick.model.material.MaterialDtd;
import com.connect.brick.model.material.MaterialPf;
import com.connect.brick.model.material.MaterialPkg;
import com.connect.brick.model.material.MaterialSales;
import com.connect.brick.model.material.MaterialSpace;
import com.connect.brick.model.material.MaterialSpec;
import com.connect.brick.model.material.MaterialStd;
import com.connect.brick.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable
//@DiscriminatorColumn(name="material")
//@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@Table(name = "TB_MATERIAL")
public class Material {

	@Id
	@Column(name = "material_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@Column(name = "cb_code")
	private String cbCode;

	@Column(name = "cb_name")
	private String cbName;

//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
//	private Contents contents;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialContents mtContents;
	
//	@OneToMany
//	@JoinTable(name = "MATERIAL_HAS_SUB_MATERIAL", 
//	joinColumns = @JoinColumn(name = "material_no"), 
//	inverseJoinColumns = @JoinColumn(name = "sub_no"))
//	private List<SubMaterial> subs;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "RL_MATERIAL_HAS_IMAGE", 
	joinColumns = @JoinColumn(name = "material_no"), 
	inverseJoinColumns = @JoinColumn(name = "image_no"))
	private List<Image> images;

	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "RL_MATERIAL_HAS_VR_VIEW_IMAGE", 
	joinColumns = @JoinColumn(name = "material_no"), 
	inverseJoinColumns = @JoinColumn(name = "image_no"))
	private List<Image> vrViewImages;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "RL_MATERIAL_HAS_VR_PRODUCT_IMAGE", 
	joinColumns = @JoinColumn(name = "material_no"), 
	inverseJoinColumns = @JoinColumn(name = "image_no"))
	private List<Image> vrProductImages;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "RL_MATERIAL_HAS_REAL_PRODUCT_IMAGE", 
	joinColumns = @JoinColumn(name = "material_no"), 
	inverseJoinColumns = @JoinColumn(name = "image_no"))
	private List<Image> realProductImages;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "RL_MATERIAL_HAS_REAL_CONS_IMAGE", 
	joinColumns = @JoinColumn(name = "material_no"), 
	inverseJoinColumns = @JoinColumn(name = "image_no"))
	private List<Image> realConsImages;
	
	@OneToOne
	@JoinColumn(name = "link_image_no")
	private Image linkImage;
	
	@OneToOne
	@JoinColumn(name = "main_image_no")
	private Image mainImage;

	@OneToOne
	@JoinColumn(name = "view_image_no")
	private Image viewImage;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	@JsonIgnore
	private DpMaterial dpMaterial;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialClass mtClass;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private List<MaterialColor> mtColors;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialDelv mtDelv;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialDtd mtDtd;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialPf mtPf;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialPkg mtPkg;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialSales mtSales;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialSpace mtSpace;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialSpec mtSpec;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
	private MaterialStd mtStd;

	@Column(name = "reg_date", updatable=false)
	private LocalDateTime regDate;

	@Column(name = "mod_date")
	private LocalDateTime modDate;
	
	@Transient
	private String regFormatDate;
	
	@Transient
	private String modFormatDate;
	
	@PostLoad
	public void postLoad() {

		LocalDateTime posted = this.regDate;
		LocalDateTime modposted = this.modDate;
		this.regFormatDate = TimeUtils.getTimeFormatDate(posted);
		this.modFormatDate = TimeUtils.getTimeFormatDate(modposted);
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getCbCode() {
		return cbCode;
	}

	public void setCbCode(String cbCode) {
		this.cbCode = cbCode;
	}

	public String getCbName() {
		return cbName;
	}

	public void setCbName(String cbName) {
		this.cbName = cbName;
	}
//
//	public Contents getContents() {
//		return contents;
//	}
//
//	public void setContents(Contents contents) {
//		this.contents = contents;
//	}

//	public List<SubMaterial> getSubs() {
//		return subs;
//	}
//
//	public void setSubs(List<SubMaterial> subs) {
//		this.subs = subs;
//	}

	public MaterialContents getMtContents() {
		return mtContents;
	}

	public void setMtContents(MaterialContents mtContents) {
		this.mtContents = mtContents;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Image> getVrViewImages() {
		return vrViewImages;
	}

	public void setVrViewImages(List<Image> vrViewImages) {
		this.vrViewImages = vrViewImages;
	}

	public List<Image> getVrProductImages() {
		return vrProductImages;
	}

	public void setVrProductImages(List<Image> vrProductImages) {
		this.vrProductImages = vrProductImages;
	}

	public List<Image> getRealProductImages() {
		return realProductImages;
	}

	public void setRealProductImages(List<Image> realProductImages) {
		this.realProductImages = realProductImages;
	}

	public List<Image> getRealConsImages() {
		return realConsImages;
	}

	public void setRealConsImages(List<Image> realConsImages) {
		this.realConsImages = realConsImages;
	}

	public Image getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(Image linkImage) {
		this.linkImage = linkImage;
	}

	public Image getMainImage() {
		return mainImage;
	}

	public void setMainImage(Image mainImage) {
		this.mainImage = mainImage;
	}

	public Image getViewImage() {
		return viewImage;
	}

	public void setViewImage(Image viewImage) {
		this.viewImage = viewImage;
	}
	
	public DpMaterial getDpMaterial() {
		return dpMaterial;
	}

	public void setDpMaterial(DpMaterial dpMaterial) {
		this.dpMaterial = dpMaterial;
	}

	public MaterialClass getMtClass() {
		return mtClass;
	}

	public void setMtClass(MaterialClass mtClass) {
		this.mtClass = mtClass;
	}

	public List<MaterialColor> getMtColors() {
		return mtColors;
	}

	public void setMtColors(List<MaterialColor> mtColors) {
		this.mtColors = mtColors;
	}

	public MaterialDelv getMtDelv() {
		return mtDelv;
	}

	public void setMtDelv(MaterialDelv mtDelv) {
		this.mtDelv = mtDelv;
	}

	public MaterialDtd getMtDtd() {
		return mtDtd;
	}

	public void setMtDtd(MaterialDtd mtDtd) {
		this.mtDtd = mtDtd;
	}

	public MaterialPf getMtPf() {
		return mtPf;
	}

	public void setMtPf(MaterialPf mtPf) {
		this.mtPf = mtPf;
	}

	public MaterialPkg getMtPkg() {
		return mtPkg;
	}

	public void setMtPkg(MaterialPkg mtPkg) {
		this.mtPkg = mtPkg;
	}

	public MaterialSales getMtSales() {
		return mtSales;
	}

	public void setMtSales(MaterialSales mtSales) {
		this.mtSales = mtSales;
	}

	public MaterialSpace getMtSpace() {
		return mtSpace;
	}

	public void setMtSpace(MaterialSpace mtSpace) {
		this.mtSpace = mtSpace;
	}

	public MaterialSpec getMtSpec() {
		return mtSpec;
	}

	public void setMtSpec(MaterialSpec mtSpec) {
		this.mtSpec = mtSpec;
	}

	public MaterialStd getMtStd() {
		return mtStd;
	}

	public void setMtStd(MaterialStd mtStd) {
		this.mtStd = mtStd;
	}

	public LocalDateTime getModDate() {
		return modDate;
	}

	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}

	public String getModFormatDate() {
		return modFormatDate;
	}

	public void setModFormatDate(String modFormatDate) {
		this.modFormatDate = modFormatDate;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getRegFormatDate() {
		return regFormatDate;
	}

	public void setRegFormatDate(String regFormatDate) {
		this.regFormatDate = regFormatDate;
	}
	
}
