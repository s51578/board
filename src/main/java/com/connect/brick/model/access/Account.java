package com.connect.brick.model.access;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

/*import lombok.Data;

@Data*/
@Entity
@Table(name = "TB_ACCOUNT")
public class Account {
	
	@Id
	@Column(name = "account_no")
	private String no;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
    @Length(min = 3, message = "*Your password must have at least 3 characters")
    @NotEmpty(message = "*Please provide your password")
	private String password;
	
	@Transient
	private String confirmPassword;

	@Column(name = "isAccountNonExpired")
	private Boolean isAccountNonExpired; 	//만료 계정
	
	@Column(name = "isAccountNonLocked")
	private Boolean isAccountNonLocked;		//잠겨있는 계정
	
	@Column(name = "isCredentialsNonExpired")
	private Boolean isCredentialsNonExpired;  //패스워드 만료
	
	@Column(name = "isEnabled")
	private Boolean isEnabled;					//사용가능한 계정인지
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RL_ACCOUNT_HAS_ROLE", 
		joinColumns = @JoinColumn(name = "account_no"), 
		inverseJoinColumns = @JoinColumn(name = "role_no"))
	private List<Role> roles;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Boolean getIsAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public Boolean getIsAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public Boolean getIsCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	} 
	
	
}
