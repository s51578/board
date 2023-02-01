package com.connect.brick.model.data;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name = "TB_REGION")
public class Region {

	@Id
	@Column(name = "rcode")
	private String rcode;

	@Column(name = "addr_sd")
	private String addrSd;
	
	@Column(name = "addr_sg")
	private String addrSg;

	@Column(name = "addr_gu")
	private String addrGu;
	
	@Column(name = "addr_emd")
	private String addrEmd;

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getAddrSd() {
		return addrSd;
	}

	public void setAddrSd(String addrSd) {
		this.addrSd = addrSd;
	}

	public String getAddrSg() {
		return addrSg;
	}

	public void setAddrSg(String addrSg) {
		this.addrSg = addrSg;
	}

	public String getAddrGu() {
		return addrGu;
	}

	public void setAddrGu(String addrGu) {
		this.addrGu = addrGu;
	}

	public String getAddrEmd() {
		return addrEmd;
	}

	public void setAddrEmd(String addrEmd) {
		this.addrEmd = addrEmd;
	}
	
	
}
