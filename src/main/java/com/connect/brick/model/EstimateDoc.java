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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.connect.brick.util.TimeUtils;

@Entity
@Table(name = "TB_ESTIMATE_DOC")
public class EstimateDoc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
//	@Column(name = "access_url")
//	private String accessUrl;
	
	@OneToOne
	@JoinColumn(name="estimate_no", updatable=false)
	private Estimate estimate;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "estimateDoc", orphanRemoval = true)
	private List<EstimateDocItem> items;
	
	@Column(name = "total_estimate_cost")
	private Integer totalEstimateCost;
	
	@Column(name = "memo")
	private String memo;
	
	@Column(name = "state")
	private Integer state = 0;
	
	@Column(name = "reg_date", updatable=false)
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

	public Estimate getEstimate() {
		return estimate;
	}

	public void setEstimate(Estimate estimate) {
		this.estimate = estimate;
	}

	public List<EstimateDocItem> getItems() {
		return items;
	}

	public void setItems(List<EstimateDocItem> items) {
		this.items = items;
	}

	public Integer getTotalEstimateCost() {
		return totalEstimateCost;
	}

	public void setTotalEstimateCost(Integer totalEstimateCost) {
		this.totalEstimateCost = totalEstimateCost;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
