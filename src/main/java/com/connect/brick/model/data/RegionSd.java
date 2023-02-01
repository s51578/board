package com.connect.brick.model.data;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name = "TB_REGION_SD")
public class RegionSd {

	@Id
	@Column(name = "rcode")
	private String rcode;

	@Column(name = "sort_num")
	private Integer sortNum;
	
	@Column(name = "addr_sd_view")
	private String addrSdView;
	
	@Column(name = "addr_sd_result")
	private String addrSdResult;
	
	@Column(name = "addr_sd_search_name")
	private String addrSdSearchName;

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getAddrSdView() {
		return addrSdView;
	}

	public void setAddrSdView(String addrSdView) {
		this.addrSdView = addrSdView;
	}

	public String getAddrSdResult() {
		return addrSdResult;
	}

	public void setAddrSdResult(String addrSdResult) {
		this.addrSdResult = addrSdResult;
	}

	public String getAddrSdSearchName() {
		return addrSdSearchName;
	}

	public void setAddrSdSearchName(String addrSdSearchName) {
		this.addrSdSearchName = addrSdSearchName;
	}
	
	
}
