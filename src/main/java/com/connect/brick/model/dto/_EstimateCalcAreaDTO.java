package com.connect.brick.model.dto;

public class _EstimateCalcAreaDTO {

	
	public _EstimateCalcAreaDTO(double inSupArea, double inDedArea, double inConsArea, boolean isLivingRoom,
			boolean isKitchen, boolean isRoom, boolean isEntrance, boolean isVeranda, boolean isExpandLiving,
			boolean isExpandKitchen, boolean isExpandRoom, int roomCount, int bathCount, int typeHouse) {
		
		super();
		this.inSupArea = inSupArea;
		this.inDedArea = inDedArea;
		this.inConsArea = inConsArea;
		this.isLivingRoom = isLivingRoom;
		this.isKitchen = isKitchen;
		this.isRoom = isRoom;
		this.isEntrance = isEntrance;
		this.isVeranda = isVeranda;
		this.isExpandLiving = isExpandLiving;
		this.isExpandKitchen = isExpandKitchen;
		this.isExpandRoom = isExpandRoom;
		this.roomCount = roomCount;
		this.bathCount = bathCount;
		this.typeHouse = typeHouse;
	}
	
	private double inSupArea;
	private double inDedArea;
	private double inConsArea;
	private boolean isLivingRoom;
	private boolean isKitchen;
	private boolean isRoom;
	private boolean isEntrance;
	private boolean isVeranda;
	private boolean isExpandLiving;
	private boolean isExpandKitchen;
	private boolean isExpandRoom;
	private int roomCount;
	private int bathCount;
	private int typeHouse;
	
	public double getInSupArea() {
		return inSupArea;
	}
	public void setInSupArea(double inSupArea) {
		this.inSupArea = inSupArea;
	}
	public double getInDedArea() {
		return inDedArea;
	}
	public void setInDedArea(double inDedArea) {
		this.inDedArea = inDedArea;
	}
	public double getInConsArea() {
		return inConsArea;
	}
	public void setInConsArea(double inConsArea) {
		this.inConsArea = inConsArea;
	}
	public boolean getIsLivingRoom() {
		return isLivingRoom;
	}
	public void setIsLivingRoom(boolean isLivingRoom) {
		this.isLivingRoom = isLivingRoom;
	}
	public boolean getIsKitchen() {
		return isKitchen;
	}
	public void setIsKitchen(boolean isKitchen) {
		this.isKitchen = isKitchen;
	}
	public boolean getIsRoom() {
		return isRoom;
	}
	public void setIsRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}
	public boolean getIsEntrance() {
		return isEntrance;
	}
	public void setIsEntrance(boolean isEntrance) {
		this.isEntrance = isEntrance;
	}
	public boolean getIsVeranda() {
		return isVeranda;
	}
	public void setIsVeranda(boolean isVeranda) {
		this.isVeranda = isVeranda;
	}
	public boolean getIsExpandLiving() {
		return isExpandLiving;
	}
	public void setIsExpandLiving(boolean isExpandLiving) {
		this.isExpandLiving = isExpandLiving;
	}
	public boolean getIsExpandKitchen() {
		return isExpandKitchen;
	}
	public void setIsExpandKitchen(boolean isExpandKitchen) {
		this.isExpandKitchen = isExpandKitchen;
	}
	public boolean getIsExpandRoom() {
		return isExpandRoom;
	}
	public void setIsExpandRoom(boolean isExpandRoom) {
		this.isExpandRoom = isExpandRoom;
	}
	public int getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
	public int getBathCount() {
		return bathCount;
	}
	public void setBathCount(int bathCount) {
		this.bathCount = bathCount;
	}
	public int getTypeHouse() {
		return typeHouse;
	}
	public void setTypeHouse(int typeHouse) {
		this.typeHouse = typeHouse;
	}
	
}
