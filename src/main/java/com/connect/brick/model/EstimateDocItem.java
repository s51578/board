package com.connect.brick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_ESTIMATE_DOC_ITEM")
public class EstimateDocItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="estimate_doc_no", updatable=false)
	private EstimateDoc estimateDoc;
	
	/*
	 * 1. tile
	 * 2. sub
	 * 3. cons
	 * 4. trans
	 * 5. destroy
	 * 
	 */
	@Column(name = "item_type")
	private String itemType;

	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_amt")
	private int itemAmt = 1;
	
	@Column(name = "item_cost")
	private int itemCost = 0;
	
	@Column(name = "item_amt_cost")
	private int itemAmtCost = 0;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public EstimateDoc getEstimateDoc() {
		return estimateDoc;
	}

	public void setEstimateDoc(EstimateDoc estimateDoc) {
		this.estimateDoc = estimateDoc;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemAmt() {
		return itemAmt;
	}

	public void setItemAmt(int itemAmt) {
		this.itemAmt = itemAmt;
	}

	public int getItemCost() {
		return itemCost;
	}

	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}

	public int getItemAmtCost() {
		return itemAmtCost;
	}

	public void setItemAmtCost(int itemAmtCost) {
		this.itemAmtCost = itemAmtCost;
	}
	
	
	
}
