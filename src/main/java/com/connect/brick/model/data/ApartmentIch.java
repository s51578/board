package com.connect.brick.model.data;

import java.time.LocalDate;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.connect.brick.util.TimeUtils;

@Cacheable
@Entity
@Table(name = "TB_APARTMENT_V2_ICH")
//@Table(name = "TB_APARTMENT_V1", indexes = @Index(columnList="name_apart"))
public class ApartmentIch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@Column(name = "type")
	private String type;
	
	@Column(name = "addr_sd")
	private String addrSd;
	
	@Column(name = "addr_sd_result")
	private String addrSdResult;
	
	@Column(name = "addr_sg")
	private String addrSg;

	@Column(name = "addr_gu")
	private String addrGu;
	
	@Column(name = "addr_emd")
	private String addrEmd;
	
	@Column(name = "addr_ri")
	private String addrRi;
	
	@Column(name = "addr_jb")
	private String addrJb;

	@Column(name = "name_apart")
	private String nameApart;
	
	@Column(name = "area_sale")
	private Long areaSale;
	
	@Column(name = "type_acreage")
	private String typeAcreage;
	
	@Column(name = "area_ded")
	private Long areaDed;
	
	@Column(name = "num_total_households")
	private Integer numTotalHouseholds;
	
	@Column(name = "num_households")
	private Integer numHouseholds;
	
	@Column(name = "type_porch")
	private String typePorch;

	@Column(name = "num_room")
	private Integer numRoom;

	@Column(name = "num_bath")
	private Integer numBath;

	@Column(name = "num_car_park")
	private Integer numCarPark;

	@Column(name = "type_heat_fuel")
	private String typeHeatFuel;

	@Column(name = "type_heat")
	private String typeHeat;

	@Column(name = "year_sale")
	private String yearSale;

	@Transient
	private String yearSaleDate;
	
	@Column(name = "year_move_in")
	private String yearMoveIn;

	@Transient
	private String yearMoveInDate;
	
	@Column(name = "price_sale")
	private Integer priceSale;

	@Column(name = "name_const")
	private String nameConst;

	@Column(name = "name_subway")
	private String nameSubway;

	@Column(name = "type_business")
	private String typeBusiness;

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

	public String getAddrSg() {
		return addrSg;
	}

	public String getAddrSdResult() {
		return addrSdResult;
	}

	public void setAddrSdResult(String addrSdResult) {
		this.addrSdResult = addrSdResult;
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

	public String getYearMoveIn() {
		return yearMoveIn;
	}

	public void setYearMoveIn(String yearMoveIn) {
		this.yearMoveIn = yearMoveIn;
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

}
