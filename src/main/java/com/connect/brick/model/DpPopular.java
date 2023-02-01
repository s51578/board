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

import com.connect.brick.util.TimeUtils;

@Entity
@Table(name = "DP_POPULAR")
public class DpPopular {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@OneToOne
	@JoinColumn(name="dp_material_no")
	private DpMaterial dpMaterial;
	
	@Column(name = "popular_rank")
	private Integer popularRank;
	
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

	public DpMaterial getDpMaterial() {
		return dpMaterial;
	}

	public void setDpMaterial(DpMaterial dpMaterial) {
		this.dpMaterial = dpMaterial;
	}

	public Integer getPopularRank() {
		return popularRank;
	}

	public void setPopularRank(Integer popularRank) {
		this.popularRank = popularRank;
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
