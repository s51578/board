package com.connect.brick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.connect.brick.model.code.ClassLg;

@Entity
@Table(name = "TB_COST_SHEET")
public class CostSheet {
	
	@Id
	@Column(name="no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "class_lg_no")
	private ClassLg classLg;
	
	@Column(name = "price_cons")
	private Integer priceCons;
	
	@Column(name = "price_manage")
	private Integer priceManage;

	@Column(name = "price_destroy")
	private Integer priceDestroy;
	
	@Column(name = "cost_trans")
	private Integer costTrans;
	
	@Column(name = "rate_loss")
	private Double rateLoss;

	@Column(name = "rate_margin")
	private Double rateMargin;
	
	@Column(name = "is_active")
	private Boolean isActive;

/**********************************************************/
//	@Column(name = "price_wrap")
//	private Integer priceWrap;
//	
//	@Column(name = "price_lift")
//	private Integer priceLift;
//	
//	@Column(name = "cost_waste")
//	private Integer costWaste;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public ClassLg getClassLg() {
		return classLg;
	}

	public void setClassLg(ClassLg classLg) {
		this.classLg = classLg;
	}

	public Integer getPriceCons() {
		return priceCons;
	}

	public void setPriceCons(Integer priceCons) {
		this.priceCons = priceCons;
	}

	public Integer getPriceManage() {
		return priceManage;
	}

	public void setPriceManage(Integer priceManage) {
		this.priceManage = priceManage;
	}

	public Integer getPriceDestroy() {
		return priceDestroy;
	}

	public void setPriceDestroy(Integer priceDestroy) {
		this.priceDestroy = priceDestroy;
	}

	public Integer getCostTrans() {
		return costTrans;
	}

	public void setCostTrans(Integer costTrans) {
		this.costTrans = costTrans;
	}

	public Double getRateLoss() {
		return rateLoss;
	}

	public void setRateLoss(Double rateLoss) {
		this.rateLoss = rateLoss;
	}

	public Double getRateMargin() {
		return rateMargin;
	}

	public void setRateMargin(Double rateMargin) {
		this.rateMargin = rateMargin;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

//	public Integer getPriceWrap() {
//		return priceWrap;
//	}
//
//	public void setPriceWrap(Integer priceWrap) {
//		this.priceWrap = priceWrap;
//	}
//
//	public Integer getPriceLift() {
//		return priceLift;
//	}
//
//	public void setPriceLift(Integer priceLift) {
//		this.priceLift = priceLift;
//	}
//
//	public Integer getCostWaste() {
//		return costWaste;
//	}
//
//	public void setCostWaste(Integer costWaste) {
//		this.costWaste = costWaste;
//	}
	
	
}
