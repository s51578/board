package com.connect.brick.model.data;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name = "TB_REGION_SG")
public class RegionSg {

	@Id
	@Column(name = "rcode")
	private String rcode;

	@Column(name = "addr_sg")
	private String addrSg;
	
	
	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getAddrSg() {
		return addrSg;
	}

	public void setAddrSg(String addrSg) {
		this.addrSg = addrSg;
	}

	
}
