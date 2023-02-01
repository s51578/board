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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.connect.brick.util.TimeUtils;

@Entity
@Table(name = "TB_ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_no", updatable=false)
	private Customer customer;
	
	@Column(name = "order_memo")
	private String memo;
	
	@Column(name = "type_house")
	private Integer typeHouse = 0;
	
	@Column(name = "isLivingFloor")
	private Boolean isLivingFloor = false;
	
	@Column(name = "isKitchenFloor")
	private Boolean isKitchenFloor = false;
	
	@Column(name = "isRoomFloor")
	private Boolean isRoomFloor = false;
	
	@Column(name = "isEntranceFloor")
	private Boolean isEntranceFloor = false;
	
	@Column(name = "isVerandaFloor")
	private Boolean isVerandaFloor = false;
	
	@Column(name = "space_use")
	private Integer spaceUse = 0;
	
	@Column(name = "sup_area")
	private Double supArea = 0.0;
	
	@Column(name = "ded_area")
	private Double dedArea = 0.0;
	
	@Column(name = "area_cons")
	private Double areaCons = 0.0;
	
	@Column(name = "isDestroy")
	private String isDestroy;
	
	@Column(name = "cntRoom")
	private Integer cntRoom =0;
	
	@Column(name = "cntBath")
	private Integer cntBath =0;
	
	@Column(name = "living_floor_material")
	private Integer livingFloorMaterial =0;
	
	@Column(name = "kitchen_floor_material")
	private Integer kitchenFloorMaterial =0;
	
	@Column(name = "room_floor_material")
	private Integer roomFloorMaterial =0;
	
	@Column(name = "commercial_floor_material")
	private Integer commercialFloorMaterial =0;
	
	@Column(name = "isExpandLiving")
	private Boolean isExpandLiving= false;
	
	@Column(name = "isExpandKitchen")
	private Boolean isExpandKitchen= false;
	
	@Column(name = "isExpandRoom")
	private Boolean isExpandRoom= false;
	
	@Column(name = "isMolding")
	private Integer isMolding = 0;
	
	@Column(name = "state_space")
	private Integer stateSpace = 0;
	
	@Column(name = "state_space_commerce")
	private Integer stateSpaceCommerce = 0;
	
	@Column(name = "isElevator")
	private Boolean isElevator = false;
	
	@Column(name = "desire_date")
	private String desireDate;

	@Transient
	private String desireFormatDate;
	
//	@OneToOne
//	@JoinColumn(name="estimate_no")
//	private Estimate estimate;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "order")
	private List<Estimate> estimates;
	
	@Column(name = "order_date", updatable=false)
	private LocalDateTime orderDate;

	@Transient
	private String orderFormatDate;

	@PostLoad
	public void postLoad() {
		LocalDateTime posted2 = this.orderDate;
		this.orderFormatDate = TimeUtils.getTimeFormatDate(posted2);
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getTypeHouse() {
		return typeHouse;
	}

	public void setTypeHouse(Integer typeHouse) {
		this.typeHouse = typeHouse;
	}

	public Boolean getIsLivingFloor() {
		return isLivingFloor;
	}

	public void setIsLivingFloor(Boolean isLivingFloor) {
		this.isLivingFloor = isLivingFloor;
	}

	public Boolean getIsKitchenFloor() {
		return isKitchenFloor;
	}

	public void setIsKitchenFloor(Boolean isKitchenFloor) {
		this.isKitchenFloor = isKitchenFloor;
	}

	public Boolean getIsRoomFloor() {
		return isRoomFloor;
	}

	public void setIsRoomFloor(Boolean isRoomFloor) {
		this.isRoomFloor = isRoomFloor;
	}

	public Boolean getIsEntranceFloor() {
		return isEntranceFloor;
	}

	public void setIsEntranceFloor(Boolean isEntranceFloor) {
		this.isEntranceFloor = isEntranceFloor;
	}

	public Boolean getIsVerandaFloor() {
		return isVerandaFloor;
	}

	public void setIsVerandaFloor(Boolean isVerandaFloor) {
		this.isVerandaFloor = isVerandaFloor;
	}

	public Double getSupArea() {
		return supArea;
	}

	public void setSupArea(Double supArea) {
		this.supArea = supArea;
	}

	public Double getDedArea() {
		return dedArea;
	}

	public void setDedArea(Double dedArea) {
		this.dedArea = dedArea;
	}

	public Integer getSpaceUse() {
		return spaceUse;
	}

	public void setSpaceUse(Integer spaceUse) {
		this.spaceUse = spaceUse;
	}

	public Double getAreaCons() {
		return areaCons;
	}

	public void setAreaCons(Double areaCons) {
		this.areaCons = areaCons;
	}

	public String getIsDestroy() {
		return isDestroy;
	}

	public void setIsDestroy(String isDestroy) {
		this.isDestroy = isDestroy;
	}

	public Integer getCntRoom() {
		return cntRoom;
	}

	public void setCntRoom(Integer cntRoom) {
		this.cntRoom = cntRoom;
	}

	public Integer getCntBath() {
		return cntBath;
	}

	public void setCntBath(Integer cntBath) {
		this.cntBath = cntBath;
	}

	public Integer getLivingFloorMaterial() {
		return livingFloorMaterial;
	}

	public void setLivingFloorMaterial(Integer livingFloorMaterial) {
		this.livingFloorMaterial = livingFloorMaterial;
	}

	public Integer getKitchenFloorMaterial() {
		return kitchenFloorMaterial;
	}

	public void setKitchenFloorMaterial(Integer kitchenFloorMaterial) {
		this.kitchenFloorMaterial = kitchenFloorMaterial;
	}

	public Integer getRoomFloorMaterial() {
		return roomFloorMaterial;
	}

	public void setRoomFloorMaterial(Integer roomFloorMaterial) {
		this.roomFloorMaterial = roomFloorMaterial;
	}

	public Integer getCommercialFloorMaterial() {
		return commercialFloorMaterial;
	}

	public void setCommercialFloorMaterial(Integer commercialFloorMaterial) {
		this.commercialFloorMaterial = commercialFloorMaterial;
	}

	public boolean getIsExpandLiving() {
		return isExpandLiving;
	}

	public void setIsExpandLiving(Boolean isExpandLiving) {
		this.isExpandLiving = isExpandLiving;
	}

	public boolean getIsExpandKitchen() {
		return isExpandKitchen;
	}

	public void setIsExpandKitchen(Boolean isExpandKitchen) {
		this.isExpandKitchen = isExpandKitchen;
	}

	public boolean getIsExpandRoom() {
		return isExpandRoom;
	}

	public void setIsExpandRoom(Boolean isExpandRoom) {
		this.isExpandRoom = isExpandRoom;
	}

	public Integer getIsMolding() {
		return isMolding;
	}

	public void setIsMolding(Integer isMolding) {
		this.isMolding = isMolding;
	}

	public Integer getStateSpace() {
		return stateSpace;
	}

	public void setStateSpace(Integer stateSpace) {
		this.stateSpace = stateSpace;
	}

	public Integer getStateSpaceCommerce() {
		return stateSpaceCommerce;
	}

	public void setStateSpaceCommerce(Integer stateSpaceCommerce) {
		this.stateSpaceCommerce = stateSpaceCommerce;
	}

	public boolean getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(Boolean isElevator) {
		this.isElevator = isElevator;
	}

	
	public String getDesireDate() {
		return desireDate;
	}

	public void setDesireDate(String desireDate) {
		this.desireDate = desireDate;
	}

	public String getDesireFormatDate() {
		return desireFormatDate;
	}

	public void setDesireFormatDate(String desireFormatDate) {
		this.desireFormatDate = desireFormatDate;
	}

	public List<Estimate> getEstimates() {
		return estimates;
	}

	public void setEstimates(List<Estimate> estimates) {
		this.estimates = estimates;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderFormatDate() {
		return orderFormatDate;
	}

	public void setOrderFormatDate(String orderFormatDate) {
		this.orderFormatDate = orderFormatDate;
	}

	
	
}
