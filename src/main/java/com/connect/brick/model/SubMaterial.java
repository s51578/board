package com.connect.brick.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.connect.brick.model.code.ClassLg;
import com.connect.brick.util.TimeUtils;

@Entity
@Table(name = "TB_SUB_MATERIAL")
public class SubMaterial {
	
	@Id
	@Column(name="sub_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "country")
	private String country;
	
//	@Column(name = "class_lg")
//	private String classLg;
	
	@ManyToOne
	@JoinColumn(name = "class_lg_no")
	private ClassLg classLg;
	
	@Column(name = "sub_name")
	private String subName;
	
	@Column(name = "mf_com")
	private String mfCom;
	
	@Column(name = "method")
	private String method;
	
	@Column(name = "tools")
	private String tools;
	
	@Column(name = "kg_box")
	private Double kgBox;
	
	@Column(name = "mix_ratio")
	private String mixRatio;

	@Column(name = "usage_type")
	private String usageType = "NA";
	
	@Column(name = "usage_per_m2")
	private Double usagePerm2 = 0.0;
	
	@Column(name = "usage_add")
	private Integer usageAdd = 0;
	
	@Column(name = "cost")
	private Integer cost;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "RL_SUB_MATERIAL_HAS_IMAGE", 
	joinColumns = @JoinColumn(name = "sub_no"), 
	inverseJoinColumns = @JoinColumn(name = "image_no"))
	private List<Image> images;

	@OneToOne
	@JoinColumn(name="main_image_no")
	private Image mainImage;
	
	@Column(name = "reg_date")
	private LocalDateTime regDate;

	@Transient
	private String regFormatDate;
	
	@PostLoad
	public void postLoad() {

		LocalDateTime posted = this.regDate;

		this.regFormatDate = TimeUtils.getTimeFormatDate(posted);
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ClassLg getClassLg() {
		return classLg;
	}

	public void setClassLg(ClassLg classLg) {
		this.classLg = classLg;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getMfCom() {
		return mfCom;
	}

	public void setMfCom(String mfCom) {
		this.mfCom = mfCom;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public Double getKgBox() {
		return kgBox;
	}

	public void setKgBox(Double kgBox) {
		this.kgBox = kgBox;
	}

	public String getMixRatio() {
		return mixRatio;
	}

	public void setMixRatio(String mixRatio) {
		this.mixRatio = mixRatio;
	}

	public String getUsageType() {
		return usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public Double getUsagePerm2() {
		return usagePerm2;
	}

	public void setUsagePerm2(Double usagePerm2) {
		this.usagePerm2 = usagePerm2;
	}

	public Integer getUsageAdd() {
		return usageAdd;
	}

	public void setUsageAdd(Integer usageAdd) {
		this.usageAdd = usageAdd;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
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

	public Image getMainImage() {
		return mainImage;
	}

	public void setMainImage(Image mainImage) {
		this.mainImage = mainImage;
	}
	
	
	
}
