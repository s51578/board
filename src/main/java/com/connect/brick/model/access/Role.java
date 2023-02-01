package com.connect.brick.model.access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_ROLE")
public class Role {
	
	public Role() {
		super();
	}

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

	@Id
	@Column(name="role_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "role_name")
	private String roleName;

	@Transient
	private boolean have;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isHave() {
		return have;
	}

	public void setHave(boolean have) {
		this.have = have;
	}
	
}
