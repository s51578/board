package com.connect.brick.model.data;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name = "TB_REGION_SGU")
public class RegionSgu {

	@Id
	@Column(name = "rcode")
	private String rcode;

	@Column(name = "addr_sgu")
	private String addrSgu;
	
	
	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getAddrSgu() {
		return addrSgu;
	}

	public void setAddrSgu(String addrSgu) {
		this.addrSgu = addrSgu;
	}

	
	
}
