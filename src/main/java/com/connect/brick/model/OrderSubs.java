package com.connect.brick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "_ORDER_SUBS")
public class OrderSubs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "order_no")
	private Order order;
	
	@Transient
	private Long subsNo;
	
	@OneToOne
	@JoinColumn(name = "subs_no")
	@NotFound(action=NotFoundAction.IGNORE)
	private SubMaterial subMaterial;
	
	@Column(name = "sub_box_amt")
	private Integer subBoxAmount;
	
	@Column(name = "sub_box_cost")
	private Integer subBoxCost;
	
	@Column(name = "sub_box_amt_cost")
	private Integer subBoxAmountCost;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getSubsNo() {
		return subsNo;
	}

	public void setSubsNo(Long subsNo) {
		this.subsNo = subsNo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public SubMaterial getSubMaterial() {
		return subMaterial;
	}

	public void setSubMaterial(SubMaterial subMaterial) {
		this.subMaterial = subMaterial;
	}

	public Integer getSubBoxCost() {
		return subBoxCost;
	}

	public void setSubBoxCost(Integer subBoxCost) {
		this.subBoxCost = subBoxCost;
	}

	public Integer getSubBoxAmount() {
		return subBoxAmount;
	}

	public void setSubBoxAmount(Integer subBoxAmount) {
		this.subBoxAmount = subBoxAmount;
	}

	public Integer getSubBoxAmountCost() {
		return subBoxAmountCost;
	}

	public void setSubBoxAmountCost(Integer subBoxAmountCost) {
		this.subBoxAmountCost = subBoxAmountCost;
	}
	

}
