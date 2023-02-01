package com.connect.brick.model.material;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.connect.brick.model.Material;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable
//@DiscriminatorValue("mtSpace")
@Entity
@Table(name = "_MATERIAL_SPACE")
public class MaterialSpace {

	public MaterialSpace() {
		this.inPossible = false;
		this.outPossible = false;
		this.roomFloor = false;
		this.roomWall = false;
		this.kichFloor = false;
		this.kichWall = false;
		this.toilFloor = false;
		this.toilWall = false;
		this.balcFloor = false;
		this.balcWall = false;
	}

	@Id
	@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;

	@Column(name = "in_possible")
	private Boolean inPossible;

	@Column(name = "out_possible")
	private Boolean outPossible;

	@Column(name="room_floor")
	private Boolean roomFloor;

	@Column(name = "room_wall")
	private Boolean roomWall;

	@Column(name = "kich_floor")
	private Boolean kichFloor;
	
	@Column(name = "kich_wall")
	private Boolean kichWall;

	@Column(name = "toil_floor")
	private Boolean toilFloor;

	@Column(name = "toil_wall")
	private Boolean toilWall;

	@Column(name = "balc_floor")
	private Boolean balcFloor;

	@Column(name = "balc_wall")
	private Boolean balcWall;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Boolean getInPossible() {
		return inPossible;
	}

	public void setInPossible(Boolean inPossible) {
		this.inPossible = inPossible;
	}

	public Boolean getOutPossible() {
		return outPossible;
	}

	public void setOutPossible(Boolean outPossible) {
		this.outPossible = outPossible;
	}

	public Boolean getRoomFloor() {
		return roomFloor;
	}

	public void setRoomFloor(Boolean roomFloor) {
		this.roomFloor = roomFloor;
	}

	public Boolean getRoomWall() {
		return roomWall;
	}

	public void setRoomWall(Boolean roomWall) {
		this.roomWall = roomWall;
	}

	public Boolean getKichFloor() {
		return kichFloor;
	}

	public void setKichFloor(Boolean kichFloor) {
		this.kichFloor = kichFloor;
	}
	
	public Boolean getKichWall() {
		return kichWall;
	}

	public void setKichWall(Boolean kichWall) {
		this.kichWall = kichWall;
	}

	public Boolean getToilFloor() {
		return toilFloor;
	}

	public void setToilFloor(Boolean toilFloor) {
		this.toilFloor = toilFloor;
	}

	public Boolean getToilWall() {
		return toilWall;
	}

	public void setToilWall(Boolean toilWall) {
		this.toilWall = toilWall;
	}

	public Boolean getBalcFloor() {
		return balcFloor;
	}

	public void setBalcFloor(Boolean balcFloor) {
		this.balcFloor = balcFloor;
	}

	public Boolean getBalcWall() {
		return balcWall;
	}

	public void setBalcWall(Boolean balcWall) {
		this.balcWall = balcWall;
	}

}
