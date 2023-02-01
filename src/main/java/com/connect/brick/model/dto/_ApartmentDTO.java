package com.connect.brick.model.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.PostLoad;

import com.connect.brick.util.TimeUtils;

public class _ApartmentDTO {

	private List<String> morphMustList;
	private Long no;
	private String type;
	private String addrSd;
	private String addrSdResult;
	private String addrSg;
	private String addrGu;
	private String addrEmd;
	private String addrRi;
	private String addrJb;
	private String nameApart;
	private Long areaSale;
	private String typeAcreage;
	private Long areaDed;
	private Integer numTotalHouseholds;
	private Integer numHouseholds;
	private String typePorch;
	private Integer numRoom;
	private Integer numBath;
	private Integer numCarPark;
	private String typeHeatFuel;
	private String typeHeat;
	private String yearSale;
	private String yearSaleDate;
	private String yearMoveIn;
	private String yearMoveInDate;
	private Integer priceSale;
	private String nameConst;
	private String nameSubway;
	private String typeBusiness;
	private LocalDate regDate;
	private String regFormatDate;
	
	@PostLoad
	public void postLoad() {

		LocalDate posted = this.regDate;

		this.regFormatDate = TimeUtils.getFormatDateForDate(posted);
	}

	
	public List<String> getMorphMustList() {
		return morphMustList;
	}


	public void setMorphMustList(List<String> morphMustList) {
		this.morphMustList = morphMustList;
	}


	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddrSd() {
		return addrSd;
	}

	public void setAddrSd(String addrSd) {
		this.addrSd = addrSd;
	}
	
	public String getAddrSdResult() {
		return addrSdResult;
	}

	public void setAddrSdResult(String addrSdResult) {
		this.addrSdResult = addrSdResult;
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

	public String getAddrRi() {
		return addrRi;
	}

	public void setAddrRi(String addrRi) {
		this.addrRi = addrRi;
	}

	public String getAddrJb() {
		return addrJb;
	}

	public void setAddrJb(String addrJb) {
		this.addrJb = addrJb;
	}

	public String getNameApart() {
		return nameApart;
	}

	public void setNameApart(String nameApart) {
		this.nameApart = nameApart;
	}

	public Long getAreaSale() {
		return areaSale;
	}

	public void setAreaSale(Long areaSale) {
		this.areaSale = areaSale;
	}

	public String getTypeAcreage() {
		return typeAcreage;
	}

	public void setTypeAcreage(String typeAcreage) {
		this.typeAcreage = typeAcreage;
	}

	public Long getAreaDed() {
		return areaDed;
	}

	public void setAreaDed(Long areaDed) {
		this.areaDed = areaDed;
	}

	public Integer getNumTotalHouseholds() {
		return numTotalHouseholds;
	}

	public void setNumTotalHouseholds(Integer numTotalHouseholds) {
		this.numTotalHouseholds = numTotalHouseholds;
	}

	public Integer getNumHouseholds() {
		return numHouseholds;
	}

	public void setNumHouseholds(Integer numHouseholds) {
		this.numHouseholds = numHouseholds;
	}

	public String getTypePorch() {
		return typePorch;
	}

	public void setTypePorch(String typePorch) {
		this.typePorch = typePorch;
	}

	public Integer getNumRoom() {
		return numRoom;
	}

	public void setNumRoom(Integer numRoom) {
		this.numRoom = numRoom;
	}

	public Integer getNumBath() {
		return numBath;
	}

	public void setNumBath(Integer numBath) {
		this.numBath = numBath;
	}

	public Integer getNumCarPark() {
		return numCarPark;
	}

	public void setNumCarPark(Integer numCarPark) {
		this.numCarPark = numCarPark;
	}

	public String getTypeHeatFuel() {
		return typeHeatFuel;
	}

	public void setTypeHeatFuel(String typeHeatFuel) {
		this.typeHeatFuel = typeHeatFuel;
	}

	public String getTypeHeat() {
		return typeHeat;
	}

	public void setTypeHeat(String typeHeat) {
		this.typeHeat = typeHeat;
	}

	public String getYearSale() {
		return yearSale;
	}

	public void setYearSale(String yearSale) {
		this.yearSale = yearSale;
	}

	public String getYearSaleDate() {
		return yearSaleDate;
	}

	public void setYearSaleDate(String yearSaleDate) {
		this.yearSaleDate = yearSaleDate;
	}

	public String getYearMoveIn() {
		return yearMoveIn;
	}

	public void setYearMoveIn(String yearMoveIn) {
		this.yearMoveIn = yearMoveIn;
	}

	public String getYearMoveInDate() {
		return yearMoveInDate;
	}

	public void setYearMoveInDate(String yearMoveInDate) {
		this.yearMoveInDate = yearMoveInDate;
	}

	public Integer getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(Integer priceSale) {
		this.priceSale = priceSale;
	}

	public String getNameConst() {
		return nameConst;
	}

	public void setNameConst(String nameConst) {
		this.nameConst = nameConst;
	}

	public String getNameSubway() {
		return nameSubway;
	}

	public void setNameSubway(String nameSubway) {
		this.nameSubway = nameSubway;
	}

	public String getTypeBusiness() {
		return typeBusiness;
	}

	public void setTypeBusiness(String typeBusiness) {
		this.typeBusiness = typeBusiness;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public String getRegFormatDate() {
		return regFormatDate;
	}

	public void setRegFormatDate(String regFormatDate) {
		this.regFormatDate = regFormatDate;
	}
	
	
}
