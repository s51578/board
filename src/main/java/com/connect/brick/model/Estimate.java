package com.connect.brick.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.connect.brick.component.EstimateFuncComponent;
import com.connect.brick.config.Constant;
import com.connect.brick.model.dto._EstimateCalcAreaDTO;
import com.connect.brick.model.material.MaterialContents;
import com.connect.brick.model.material.MaterialDtd;
import com.connect.brick.util.TimeUtils;

import jdk.nashorn.internal.runtime.ConsString;

@Entity
@Table(name = "TB_ESTIMATE")
public class Estimate {
	
	@Transient
	final private int __commercial_space_num = 6;
	
	//천장높이 : 평균 2.2 ~ 2.3m
	//현관(entrance) : 전용면적의 2 ~ 3.5%
	//화장실 : 하나당 3 ~ 5m²
	//싱크대 : 1 ~ 1.5m²
	//변수율 : 0.95
	@Transient
	final private double __expect_entrance_per = 0.02;
	@Transient
	final private double __expect_bath_area = 3;
	@Transient
	final private double __expect_sink_area = 1;
	@Transient
	final private double __variable = 0.95;
	@Transient
	final private double __tileLoss = 0.05;
	@Transient
	final private double __vat = 1.1;
	@Transient
	final private double __margin_rate = 0.05;
	
	@Transient
	final private int __default_subs_cost = 300000;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@OneToOne
	@JoinColumn(name="material_contents_no")
	private MaterialContents mtContents;
	
	@Transient
	private String mtName;

	@ManyToOne
	@JoinTable(name = "RL_ORDER_HAS_ESTIMATES", 
	joinColumns = @JoinColumn(name = "estimate_no"), 
	inverseJoinColumns = @JoinColumn(name = "order_no"))
	private Order order;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "estimate", orphanRemoval = true)
	private List<EstimateSub> estimateSubs;
	
	//input
	@Column(name = "ded_area")
	private double dedArea = 0.0; //전용면적
	
	@Column(name = "room_count")
	private int roomCount = 1;
	
	@Column(name = "bath_count")
	private int bathCount = 1; //화장실 개수
	
	@Column(name = "isExpand")
	private boolean isExpand = false;
	
	@Column(name = "expand_room_count")
	private int expandRoomCount = 0;
	
	@Column(name = "isCons")
	private boolean isCons = true;	//시공비 여부
	//
//	private double supArea;	//공급면적
	
	//최종 시공 면적
	@Column(name = "final_area")
	private double finalArea = 0.0;
	
	//평수
	@Column(name = "acreage")
	private int acreage = 0;

	@Column(name = "est_tile_price")
	private int estTilePrice = 0;
	
	//calc
	@Column(name = "tile_qtt")
	private int tileQtt = 0;

	@Column(name = "total_tile_kg")
	private double totalTileKg = 0.0;
	@Column(name = "total_tile_cost")
	private int totalTileCost = 0;
	
	@Column(name = "total_subs_kg")
	private double totalSubsKg = 0.0;
	@Column(name = "total_subs_cost")
	private int totalSubsCost = 0;
	
	@Column(name = "cons_cost")
	private int consCost = 0;
	@Column(name = "total_cons_cost")
	private int totalConsCost = 0;
	
	@Column(name = "manage_cost")
	private int manageCost = 0;
	@Column(name = "total_manage_cost")
	private int totalManageCost = 0;
	
	@Column(name = "total_cons_all_cost")
	private int totalConsAllCost = 0;
	
	@Column(name = "total_destroy_cost")
	private int totalDestroyCost = 0;

	@Column(name = "trans_cost")
	private int transCost = 0;
	@Column(name = "total_trans_cost")
	private int totalTransCost = 0;
	
	@Column(name = "total_final_cost")
	private int totalFinalCost = 0;

	@OneToOne(cascade = CascadeType.ALL, 
			mappedBy = "estimate", orphanRemoval = true)
	private EstimateDoc estimateDoc;
	
	@Column(name = "estimate_date")
	private LocalDateTime estimateDate;

	@Transient
	private String estimateFormatDate;

//	@Column(name = "total_lift_cost")
//	private int totalLiftCost = 0;
//	
//	@Column(name = "total_wrap_cost")
//	private int totalWrapCost = 0;
//
//	@Column(name = "total_waste_cost")
//	private int totalWasteCost = 0;

	
	@PostLoad
	public void postLoad() {

		LocalDateTime posted = this.estimateDate;

		this.estimateFormatDate = TimeUtils.getTimeFormatDate(posted);
	}

	//estimate.min
	public Estimate(int price, double area, double meterToBox, CostSheet costSheet) {
		
		this.estTilePrice = price;
		
		this.finalArea = area;
		this.acreage = EstimateFuncComponent.getAcreage(area);
		this.tileQtt = EstimateFuncComponent.calcTileBoxQuantity(area, meterToBox, costSheet.getRateLoss());
		
		this.totalTileKg = EstimateFuncComponent.calcTotalTileKg(this.tileQtt, 20.0);
		this.totalSubsKg =  0;
		
		this.totalTileCost = EstimateFuncComponent.calcTileCost(this.tileQtt, this.estTilePrice);
		this.totalSubsCost = __default_subs_cost;
		
		this.consCost = costSheet.getPriceCons();
		//this.totalConsCost = calcConsCost(finalArea, consCost);
		this.totalConsCost = EstimateFuncComponent.calcConsCostByTileQtt(this.tileQtt, this.consCost);
		
		this.manageCost = costSheet.getPriceManage();
		//this.totalConsCost = calcConsCost(finalArea, consCost);
		this.totalManageCost = EstimateFuncComponent.calcManageCostByAcreage(this.totalConsCost, acreage, this.manageCost);
		
		this.totalConsAllCost = EstimateFuncComponent.calcConsAllCost(this.totalConsCost, this.totalManageCost, costSheet.getRateMargin() );
		
		//this.totalWasteCost = costSheet.getCostWaste();
		
//		this.transCost = costSheet.getCostTrans();
//		this.totalTransCost = costSheet.getCostTrans();//EstimateFuncComponent.calcTransCost(totalTileKg, totalSubsKg);
		
		//this.totalWrapCost = EstimateFuncComponent.calcWrapCost(acreage, costSheet.getPriceWrap(), costSheet.getRateMargin());
		//this.totalLiftCost = EstimateFuncComponent.calcLiftCost(tileQtt, costSheet.getPriceLift(), costSheet.getRateMargin());
		this.totalDestroyCost = 0; 
		
		this.totalFinalCost = EstimateFuncComponent.calcFinalCostImp(this.totalTileCost, this.totalSubsCost, this.totalConsAllCost,
				this.totalTransCost, this.totalDestroyCost);
	}

	public Estimate(MaterialContents mtContents,
			List<SubMaterial> subs,
			CostSheet costSheet,
			double inDedArea,
			int inRoomCount,
			int inBathCount,
			boolean isRoom) {
		
		this.mtContents = mtContents;
		this.mtName = mtContents.getMaterial().getCbName();
		this.estTilePrice = EstimateFuncComponent.getEstTilePrice(mtContents);
		
		//자료구조 매핑
		this.estimateSubs = EstimateFuncComponent.mappingSubs(subs);
		
//		this.supArea = supArea;
		this.dedArea = inDedArea;
		this.roomCount = inRoomCount;
		this.bathCount = inBathCount;
		this.isExpand = false;
		this.expandRoomCount = 0;
		this.isCons = true;
		
		this.finalArea = EstimateFuncComponent.calcEstimateArea(dedArea, isRoom, roomCount, bathCount, isExpand, expandRoomCount);
		this.acreage = EstimateFuncComponent.getAcreage(finalArea);
		
		this.tileQtt = EstimateFuncComponent.calcTileBoxQuantity(finalArea, mtContents.getMaterial().getMtPkg().getMeter2Box().doubleValue(), costSheet.getRateLoss());
		
		this.totalTileKg = EstimateFuncComponent.calcTotalTileKg(tileQtt, mtContents.getMaterial().getMtPkg().getKgBox().doubleValue());
		this.totalSubsKg = EstimateFuncComponent.calcTotalSubsKg(finalArea, estimateSubs);
		
		this.totalTileCost = EstimateFuncComponent.calcTileCost(tileQtt, estTilePrice);
		this.totalSubsCost = EstimateFuncComponent.calcTotalSubsCost(finalArea, estimateSubs, inRoomCount);

		this.transCost = costSheet.getCostTrans();
		this.totalTransCost = costSheet.getCostTrans();//EstimateFuncComponent.calcTransCost(totalTileKg, totalSubsKg);
		
		if(this.isCons) {
			this.consCost = costSheet.getPriceCons();
			this.totalConsCost = EstimateFuncComponent.calcConsCostByTileQtt(this.tileQtt, this.consCost);
			
			this.manageCost = costSheet.getPriceManage();
			this.totalManageCost = EstimateFuncComponent.calcManageCostByAcreage(this.totalConsCost, this.acreage, this.manageCost);
			
			this.totalConsAllCost = EstimateFuncComponent.calcConsAllCost(this.totalConsCost, this.totalManageCost, costSheet.getRateMargin() );
			//this.totalConsCost = calcConsCost(finalArea, consCost);
			this.totalDestroyCost = EstimateFuncComponent.calcDestroyCost(this.acreage, costSheet.getPriceDestroy()); 
			//this.totalWasteCost = costSheet.getCostWaste();
		}

		this.totalFinalCost = EstimateFuncComponent.calcFinalCostImp(this.totalTileCost, this.totalSubsCost, this.totalConsAllCost,
				this.totalTransCost, this.totalDestroyCost);
		
	}

	public Estimate(MaterialContents mtContents,
			List<SubMaterial> subs,
			CostSheet costSheet,
			_EstimateCalcAreaDTO dto) {
		
		this.mtContents = mtContents;
		this.mtName = mtContents.getMaterial().getCbName();
		this.estTilePrice = EstimateFuncComponent.getEstTilePrice(mtContents);
		
		//자료구조 매핑
		this.estimateSubs = EstimateFuncComponent.mappingSubs(subs);
		
//		this.supArea = supArea;
		this.dedArea = dto.getInDedArea();
		this.roomCount = dto.getRoomCount();
		this.bathCount = dto.getBathCount();
		this.isCons = true;
		
		if(dto.getTypeHouse()==__commercial_space_num)
			this.finalArea = EstimateFuncComponent.calcUpEstimateAreaForCommercial(dto);
		else
			this.finalArea = EstimateFuncComponent.calcUpEstimateAreaForHome(dto);
		
		this.acreage = EstimateFuncComponent.getAcreage(finalArea);
		
		this.tileQtt = EstimateFuncComponent.calcTileBoxQuantity(finalArea, mtContents.getMaterial().getMtPkg().getMeter2Box().doubleValue(), costSheet.getRateLoss());
		
		this.totalTileKg = EstimateFuncComponent.calcTotalTileKg(tileQtt, mtContents.getMaterial().getMtPkg().getKgBox().doubleValue());
		this.totalSubsKg = EstimateFuncComponent.calcTotalSubsKg(finalArea, estimateSubs);
		
		this.totalTileCost = EstimateFuncComponent.calcTileCost(tileQtt, estTilePrice);
		this.totalSubsCost = EstimateFuncComponent.calcTotalSubsCost(finalArea, estimateSubs, this.roomCount);

		this.transCost = costSheet.getCostTrans();
		this.totalTransCost = costSheet.getCostTrans();//EstimateFuncComponent.calcTransCost(totalTileKg, totalSubsKg);
		
		if(this.isCons) {
			this.consCost = costSheet.getPriceCons();
			this.totalConsCost = EstimateFuncComponent.calcConsCostByTileQtt(this.tileQtt, this.consCost);
			
			this.manageCost = costSheet.getPriceManage();
			this.totalManageCost = EstimateFuncComponent.calcManageCostByAcreage(this.totalConsCost, this.acreage, this.manageCost);
			
			this.totalConsAllCost = EstimateFuncComponent.calcConsAllCost(this.totalConsCost, this.totalManageCost, costSheet.getRateMargin() );
			//this.totalConsCost = calcConsCost(finalArea, consCost);
			this.totalDestroyCost = EstimateFuncComponent.calcDestroyCost(this.acreage, costSheet.getPriceDestroy()); 
			//this.totalWasteCost = costSheet.getCostWaste();
		}

		this.totalFinalCost = EstimateFuncComponent.calcFinalCostImp(this.totalTileCost, this.totalSubsCost, this.totalConsAllCost,
				this.totalTransCost, this.totalDestroyCost);
		
	}
	
	public Estimate(MaterialContents mtContents,
			List<SubMaterial> subs,
			CostSheet costSheet,
			double consArea) {
		
		this.mtContents = mtContents;
		this.mtName = mtContents.getMaterial().getCbName();
		this.estTilePrice = EstimateFuncComponent.getEstTilePrice(mtContents);
		
		//자료구조 매핑
		this.estimateSubs = EstimateFuncComponent.mappingSubs(subs);
		
		this.isExpand = false;
		this.expandRoomCount = 0;
		this.isCons = true;
		
		this.finalArea = consArea;//EstimateFuncComponent.calcEstimateArea(dedArea, isRoom, roomCount, bathCount, isExpand, expandRoomCount);
		this.acreage = EstimateFuncComponent.getAcreage(this.finalArea);
		
		this.tileQtt = EstimateFuncComponent.calcTileBoxQuantity(this.finalArea, mtContents.getMaterial().getMtPkg().getMeter2Box().doubleValue(), costSheet.getRateLoss());
		
		this.totalTileKg = EstimateFuncComponent.calcTotalTileKg(this.tileQtt, mtContents.getMaterial().getMtPkg().getKgBox().doubleValue());
		this.totalSubsKg = EstimateFuncComponent.calcTotalSubsKg(this.finalArea, this.estimateSubs);
		
		this.totalTileCost = EstimateFuncComponent.calcTileCost(this.tileQtt, this.estTilePrice);
		this.totalSubsCost = EstimateFuncComponent.calcTotalSubsCost(this.finalArea, this.estimateSubs, 3); //3: 방개수 3개로 고정

		this.transCost = costSheet.getCostTrans();
		this.totalTransCost = costSheet.getCostTrans();//EstimateFuncComponent.calcTransCost(totalTileKg, totalSubsKg);
		
		if(this.isCons) {
			this.consCost = costSheet.getPriceCons();
			this.totalConsCost = EstimateFuncComponent.calcConsCostByTileQtt(this.tileQtt, this.consCost);
			
			this.manageCost = costSheet.getPriceManage();
			this.totalManageCost = EstimateFuncComponent.calcManageCostByAcreage(this.totalConsCost, this.acreage, this.manageCost);
			
			this.totalConsAllCost = EstimateFuncComponent.calcConsAllCost(this.totalConsCost, this.totalManageCost, costSheet.getRateMargin() );
			//this.totalConsCost = calcConsCost(finalArea, consCost);
			this.totalDestroyCost = EstimateFuncComponent.calcDestroyCost(this.acreage, costSheet.getPriceDestroy()); 
		}

		this.totalFinalCost = EstimateFuncComponent.calcFinalCostImp(this.totalTileCost, this.totalSubsCost, this.totalConsAllCost,
				this.totalTransCost, this.totalDestroyCost);
		
	}
	
	public Estimate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public MaterialContents getMtContents() {
		return mtContents;
	}

	public void setMtContents(MaterialContents mtContents) {
		this.mtContents = mtContents;
	}

	public String getMtName() {
		return mtName;
	}

	public void setMtName(String mtName) {
		this.mtName = mtName;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<EstimateSub> getEstimateSubs() {
		return estimateSubs;
	}

	public void setEstimateSubs(List<EstimateSub> estimateSubs) {
		this.estimateSubs = estimateSubs;
	}

	public double getDedArea() {
		return dedArea;
	}

	public void setDedArea(double dedArea) {
		this.dedArea = dedArea;
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

	public boolean isExpand() {
		return isExpand;
	}

	public void setExpand(boolean isExpand) {
		this.isExpand = isExpand;
	}

	public int getExpandRoomCount() {
		return expandRoomCount;
	}

	public void setExpandRoomCount(int expandRoomCount) {
		this.expandRoomCount = expandRoomCount;
	}

	public boolean isCons() {
		return isCons;
	}

	public void setCons(boolean isCons) {
		this.isCons = isCons;
	}

	public double getFinalArea() {
		return finalArea;
	}

	public void setFinalArea(double finalArea) {
		this.finalArea = finalArea;
	}

	public int getAcreage() {
		return acreage;
	}

	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}

	public int getEstTilePrice() {
		return estTilePrice;
	}

	public void setEstTilePrice(int estTilePrice) {
		this.estTilePrice = estTilePrice;
	}

	public int getTileQtt() {
		return tileQtt;
	}

	public void setTileQtt(int tileQtt) {
		this.tileQtt = tileQtt;
	}

	public double getTotalTileKg() {
		return totalTileKg;
	}

	public void setTotalTileKg(double totalTileKg) {
		this.totalTileKg = totalTileKg;
	}

	public int getTotalTileCost() {
		return totalTileCost;
	}

	public void setTotalTileCost(int totalTileCost) {
		this.totalTileCost = totalTileCost;
	}

	public double getTotalSubsKg() {
		return totalSubsKg;
	}

	public void setTotalSubsKg(double totalSubsKg) {
		this.totalSubsKg = totalSubsKg;
	}

	public int getTotalSubsCost() {
		return totalSubsCost;
	}

	public void setTotalSubsCost(int totalSubsCost) {
		this.totalSubsCost = totalSubsCost;
	}

	public int getConsCost() {
		return consCost;
	}

	public void setConsCost(int consCost) {
		this.consCost = consCost;
	}

	public int getTotalConsCost() {
		return totalConsCost;
	}

	public void setTotalConsCost(int totalConsCost) {
		this.totalConsCost = totalConsCost;
	}

	public int getTotalDestroyCost() {
		return totalDestroyCost;
	}

	public void setTotalDestroyCost(int totalDestroyCost) {
		this.totalDestroyCost = totalDestroyCost;
	}

	public int getManageCost() {
		return manageCost;
	}

	public void setManageCost(int manageCost) {
		this.manageCost = manageCost;
	}

	public int getTotalManageCost() {
		return totalManageCost;
	}

	public void setTotalManageCost(int totalManageCost) {
		this.totalManageCost = totalManageCost;
	}

	public int getTotalConsAllCost() {
		return totalConsAllCost;
	}

	public void setTotalConsAllCost(int totalConsAllCost) {
		this.totalConsAllCost = totalConsAllCost;
	}

	public int getTransCost() {
		return transCost;
	}

	public void setTransCost(int transCost) {
		this.transCost = transCost;
	}

	public int getTotalTransCost() {
		return totalTransCost;
	}

	public void setTotalTransCost(int totalTransCost) {
		this.totalTransCost = totalTransCost;
	}

	public int getTotalFinalCost() {
		return totalFinalCost;
	}

	public void setTotalFinalCost(int totalFinalCost) {
		this.totalFinalCost = totalFinalCost;
	}

	public EstimateDoc getEstimateDoc() {
		return estimateDoc;
	}

	public void setEstimateDoc(EstimateDoc estimateDoc) {
		this.estimateDoc = estimateDoc;
	}

	public LocalDateTime getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(LocalDateTime estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getEstimateFormatDate() {
		return estimateFormatDate;
	}

	public void setEstimateFormatDate(String estimateFormatDate) {
		this.estimateFormatDate = estimateFormatDate;
	}

	
	
}
