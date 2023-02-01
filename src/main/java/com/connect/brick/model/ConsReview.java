package com.connect.brick.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.connect.brick.model.material.MaterialContents;
import com.connect.brick.util.TimeUtils;

@Entity
@Table(name = "TB_CONS_REVIEW")
public class ConsReview {
	
	@Id
	@Column(name = "cons_review_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consReviewNo;
	
	@Column(name = "cons_review_writer")
	private String consReviewWriter;
	
	@Column(name = "cons_review_title")
	private String consReviewTitle;
	
	@Column(name = "cons_review_contents")
	private String consReviewContents;
	
	@Column(name = "cons_review_date", updatable = false)
	private LocalDateTime consReviewDate;
	
	@OneToOne
	@JoinColumn(name = "main_image_no")
	private Image mainImage;
	
	@OneToOne
	@JoinColumn(name="material_contents_no")
	private MaterialContents mtContents;
	
	@Column(name = "type_house")
	private String typeHouse;
	
	@Column(name = "isLivingFloor")
	private Integer isLivingFloor;
	
	@Column(name = "isKitchenFloor")
	private Integer isKitchenFloor;
	
	@Column(name = "isRoomFloor")
	private Integer isRoomFloor;
	
	@Column(name = "acreage")
	private Integer acreage;
	
	@Column(name = "cost")
	private Integer cost;
	
	@Column(name = "period")
	private Integer period;
	
	@Column(name = "tags")
	private String tags;
	
	@Column(name = "address")
	private String address;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Image getMainImage() {
		return mainImage;
	}

	public void setMainImage(Image mainImage) {
		this.mainImage = mainImage;
	}

	public MaterialContents getMtContents() {
		return mtContents;
	}

	public void setMtContents(MaterialContents mtContents) {
		this.mtContents = mtContents;
	}

	public String getTypeHouse() {
		return typeHouse;
	}

	public void setTypeHouse(String typeHouse) {
		this.typeHouse = typeHouse;
	}

	public Integer getIsLivingFloor() {
		return isLivingFloor;
	}

	public void setIsLivingFloor(Integer isLivingFloor) {
		this.isLivingFloor = isLivingFloor;
	}

	public Integer getIsKitchenFloor() {
		return isKitchenFloor;
	}

	public void setIsKitchenFloor(Integer isKitchenFloor) {
		this.isKitchenFloor = isKitchenFloor;
	}

	public Integer getIsRoomFloor() {
		return isRoomFloor;
	}

	public void setIsRoomFloor(Integer isRoomFloor) {
		this.isRoomFloor = isRoomFloor;
	}

	public Integer getAcreage() {
		return acreage;
	}

	public void setAcreage(Integer acreage) {
		this.acreage = acreage;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	

	@Transient
	private String cosReviewFormatDate;
	
	public String getCosReviewFormatDate() {
		return cosReviewFormatDate;
	}

	public void setCosReviewFormatDate(String cosReviewFormatDate) {
		this.cosReviewFormatDate = cosReviewFormatDate;
	}

	@PostLoad
	public void postLoad() {
		LocalDateTime posted = this.consReviewDate;
		this.cosReviewFormatDate = TimeUtils.getTimeFormatDate(posted);
	}

	public Long getConsReviewNo() {
		return consReviewNo;
	}

	public void setConsReviewNo(Long consReviewNo) {
		this.consReviewNo = consReviewNo;
	}

	public String getConsReviewWriter() {
		return consReviewWriter;
	}

	public void setConsReviewWriter(String consReviewWriter) {
		this.consReviewWriter = consReviewWriter;
	}

	public String getConsReviewTitle() {
		return consReviewTitle;
	}

	public void setConsReviewTitle(String consReviewTitle) {
		this.consReviewTitle = consReviewTitle;
	}

	public String getConsReviewContents() {
		return consReviewContents;
	}

	public void setConsReviewContents(String consReviewContents) {
		this.consReviewContents = consReviewContents;
	}

	public LocalDateTime getConsReviewDate() {
		return consReviewDate;
	}

	public void setConsReviewDate(LocalDateTime consReviewDate) {
		this.consReviewDate = consReviewDate;
	}
	
	
	
}
