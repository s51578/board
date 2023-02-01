package com.connect.brick.model.material;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.connect.brick.model.Material;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable
//@DiscriminatorValue("mtSales")
@Entity
@Table(name = "_MATERIAL_SALES")
public class MaterialSales {
	
	public MaterialSales() {
		this.costPrice = 0;
		this.dtdPrice = 0;
		this.consumerPrice = 0;
		this.salesPrice = 0;
		this.stock = 0;
	}
	
	@Id
	@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;
	
	@Column(name = "cost_price")
	private Integer costPrice;
	
	@Column(name = "dtd_price")
	private Integer dtdPrice;
	
	@Column(name = "consumer_price")
	private Integer consumerPrice;
	
	@Column(name = "sales_price")
	private Integer salesPrice;
	
	@Column(name = "stock")
	private Integer stock;
	
//	@Column(name = "stock_flag", columnDefinition = "TINYINT", length = 3)
//	private Boolean stockFlag;

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

	public Integer getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Integer costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getDtdPrice() {
		return dtdPrice;
	}

	public void setDtdPrice(Integer dtdPrice) {
		this.dtdPrice = dtdPrice;
	}

	public Integer getConsumerPrice() {
		return consumerPrice;
	}

	public void setConsumerPrice(Integer consumerPrice) {
		this.consumerPrice = consumerPrice;
	}

	public Integer getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Integer salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

//	public Boolean getStockFlag() {
//		return stockFlag;
//	}
//
//	public void setStockFlag(Boolean stockFlag) {
//		this.stockFlag = stockFlag;
//	}

		
}
