package com.connect.brick.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.connect.brick.model.EstimateSub;
import com.connect.brick.model.SubMaterial;
import com.connect.brick.model.dto._EstimateCalcAreaDTO;
import com.connect.brick.model.material.MaterialContents;

@Component
public class EstimateFuncComponent {
	
	final private Integer[] pyungbang = {
			3, 6, 9, 13, 16, 19, 23, 26, 29, 33, 36, 39, 42, 46, 49, 
			52, 56, 59, 62, 66, 69, 72, 75, 79, 82, 85, 98, 92, 95, 
			99, 102, 105, 108, 112, 115, 118, 122, 125, 128, 132, 135, 
			138, 141, 145, 148, 151, 155, 158, 161, 165, 168, 171, 174, 
			178, 181, 184, 188, 191, 194, 198, 201, 204, 207, 211, 214, 
			217, 221, 224, 227, 231, 234, 237, 240, 244, 247, 250, 254, 
			257, 260, 264, 267, 270, 273, 277, 280, 283, 287, 290, 239, 
			297, 300, 303, 306, 310, 313, 316, 320, 323, 326, 330
	};
	
	final public static double __default_min_area = 42.0;
	
	final public static double __calSquareMeter = 3.305785;
	//공급 대비 전용 비율 : 70~80%
	final public static double __expect_dArea_per = 0.80;
	//천장높이 : 평균 2.2 ~ 2.3m
	//현관(entrance) : 전용면적의 2 ~ 3.5%
	//주방 : 전용면적의 12~15%
	//화장실 : 하나당 3 ~ 5m²
	//싱크대 : 1 ~ 1.5m²
	//변수율 : 0.95
	final public static double __expect_kitchen_per = 0.11;
	final public static double __expect_entrance_per = 0.02;
	final public static double __expect_bath_area = 3;
	final public static double __expect_sink_area = 1;
	final public static double __variable = 0.95;
	
	final public static double __expect_three_over_room_area_per = 0.5;
	final public static double __expect_three_room_area_per = 0.42;
	final public static double __expect_two_room_area_per = 0.35;
	final public static double __expect_one_room_area_per = 0.3;
	
	final public static int __default_subs_cost = 300000;
	final public static int __default_lift_cost = 4000;
	final public static int __default_destroy_cost = 27000;
	final public static int __default_waste_cost = 300000;
	
	public static int getAcreage(double supArea) {
		
		double result = (supArea/__calSquareMeter);
		
		return (int) Math.round(result);
	}
	
	public static int getEstTilePrice(MaterialContents mtContents) {

		Integer tilePrice = mtContents.getMaterial().getMtSales().getSalesPrice();
		
		if(tilePrice==0 || tilePrice==null)
			tilePrice = mtContents.getMaterial().getMtSales().getConsumerPrice();
			
		return tilePrice;
	}
	
	//시공 면적
	// @param
	// dArea : 전용면적
	// roomCount : 방 개수
	// bathCount : 욕실 개수
	// isExpand : 확장 여부
	// expandRoomCount : 확장 룸 갯수
	public static double calcEstimateArea(double dArea, boolean isRoom, int roomCount, int bathCount, boolean isExpand, int expandRoomCount) {
				
		double entrance = dArea * __expect_entrance_per;
		double all_bath_area = __expect_bath_area * bathCount;
		
		double expectArea = dArea-entrance-all_bath_area-__expect_sink_area;
		
		if(!isRoom) {
			
			if(roomCount > 3)
				expectArea = expectArea - (dArea*__expect_three_room_area_per);
			else
				expectArea = expectArea - (dArea*__expect_two_room_area_per);
			
		}

		double finalArea = expectArea*__variable;
		
		if(finalArea<0)
			finalArea = 0.0;
		
		return finalArea;
	}

	//시공 면적
	// @param
	//boolean isLivingRoom,
	//boolean isKitchen,
	//boolean isRoom,
	//boolean isEntrance, //현관
	//boolean isVeranda,
	//boolean isExpandLiving,
	//boolean isExpandKitchen,
	//boolean isExpandRoom,
	//double inSupArea,
	//double inDedArea,
	//double inConsArea,
	// roomCount : 방 개수
	// bathCount : 욕실 개수
	
	// 1. 거실 ⇒ 공급면적의 30%
	// 2. 거실 + 확장&베란다시공 ⇒ 공급면적의 35%
	
	// 1. 거실, 주방 => 공급면적의 절반값
	// 2. 거실, 주방 + 확장&베란다시공 => 공급면적의 절반값
	
	// 1. 거실, 주방, 방 ⇒ 전용면적
	// 2. 거실, 주방, 방 + 모두확장 ⇒ 공급면적
	// 3. 거실, 주방, 방  + 일부확장 ⇒ 공급면적
	
	// (n)+ 현관 ⇒ (n)+ 전용면적의2%
	public static double calcUpEstimateAreaForHome(_EstimateCalcAreaDTO dto) {

		double inConsArea = dto.getInConsArea();
		double inDedArea = dto.getInDedArea();
		double inSupArea = dto.getInSupArea();
		
		//시공 면적이 있을 경우 => 시공면적 리턴
		if(inConsArea > 0)
			return inConsArea;
		
		//전용 + 공급 둘다
		//전용만
		if(inDedArea > 0 && inSupArea < 1) {
			inSupArea = inDedArea / __expect_dArea_per;
		//공급만
		} else if(inDedArea < 1 && inSupArea > 0) {
			inDedArea = inSupArea * __expect_dArea_per;
		//둘다 없을때
		} else
			return __default_min_area;
		
		double entrance_area = inDedArea * __expect_entrance_per;
		
		boolean isLivingRoom = dto.getIsLivingRoom();
		boolean isKitchen = dto.getIsKitchen();
		boolean isRoom = dto.getIsRoom();
		boolean isEntrance = dto.getIsEntrance();
		boolean isVeranda = dto.getIsVeranda();
		boolean isExpandLiving = dto.getIsExpandLiving();
		boolean isExpandKitchen = dto.getIsExpandKitchen();
		boolean isExpandRoom = dto.getIsExpandRoom();
		
		double final_area = 0;
		
		// 1. 거실 ⇒ 공급면적의 30%
		if(isLivingRoom && !isKitchen && !isRoom && !isVeranda && !isExpandLiving)
			final_area = inSupArea*0.3;
		// 2. 거실 + 확장&베란다시공 ⇒ 공급면적의 35%
		else if(isLivingRoom && !isKitchen && !isRoom && (isVeranda || isExpandLiving) )
			final_area = inSupArea*0.35;
		
		// 1. 거실, 방 => 전용면적
		else if(isLivingRoom && !isKitchen && isRoom && !isVeranda && !isExpandLiving)
			final_area = inDedArea*0.9;
		// 2. 거실, 방 + 확장
		else if(isLivingRoom && !isKitchen && isRoom && (isVeranda || isExpandLiving))
			final_area = inDedArea;
		
		// 1. 거실, 주방 => 공급면적의 절반값
		else if(isLivingRoom && isKitchen && !isRoom && !isVeranda && !isExpandLiving && !isExpandKitchen)
			final_area = inSupArea*0.45;
		// 2. 거실, 주방 + 확장&베란다시공 => 공급면적의 절반값
		else if(isLivingRoom && isKitchen && !isRoom && (isVeranda || isExpandLiving || isExpandKitchen))
			final_area = inSupArea*0.5;
	
		// 1. 거실, 주방, 방 ⇒ 전용면적
		else if(isLivingRoom && isKitchen && isRoom && !isVeranda && !isExpandLiving && !isExpandKitchen && !isExpandRoom)
			final_area = inDedArea;
		// 2. 거실, 주방, 방 + 모두확장 ⇒ 공급면적
		else if(isLivingRoom && isKitchen && isRoom && isVeranda && isExpandLiving && isExpandKitchen && isExpandRoom)
			final_area = inSupArea;
		// 3. 거실, 주방, 방  + 일부확장 ⇒ 공급면적
		else if(isLivingRoom && isKitchen && isRoom && (isVeranda || isExpandLiving || isExpandKitchen || isExpandRoom) )
			final_area = inSupArea;
		//에러 : 전용면적
		else
			final_area = inDedArea;
			
			
		if(isEntrance)
			final_area = final_area + entrance_area;
	
		return final_area;
	}
	

	public static double calcUpEstimateAreaForCommercial(_EstimateCalcAreaDTO dto) {
		
		double inConsArea = dto.getInConsArea();
		
		//시공 면적이 있을 경우 => 시공면적 리턴
		if(inConsArea > 0) {
			return inConsArea;
		//전용 면적이 있을 경우 => 전용면적으로 기준점
		} else if(dto.getInDedArea() > 0) {
			return dto.getInDedArea();
		} else
			return __default_min_area;
		
	}
	
	//타일박스 수
	public static int calcTileBoxQuantity(double finalArea, double meterPerBox, double __tileLoss) {
		double tileQuantity = finalArea / meterPerBox * (1+__tileLoss);
		return (int) Math.ceil(tileQuantity);
	}
	
	// 타일 가격 계산기
	public static int calcTileCost(int tileboxQuantity, int tilePrice) {
		
		return tileboxQuantity * tilePrice;
	}

	//개별 부자재 매핑
	public static List<EstimateSub> mappingSubs(List<SubMaterial> subs) {
		
		List<EstimateSub> ess = new ArrayList<EstimateSub>();
		
		for (SubMaterial sm : subs) {
			
			EstimateSub es = new EstimateSub();
			//es.setEstimate(this);
			es.setSubMaterial(sm);
			
			ess.add(es);
		}
		
		return ess;
	}
	
	//개별 부자재 수량
	public static int calcSubQuantity(double finalArea, double subPerM2) {
		double subQuantity = finalArea * subPerM2;
		return (int) Math.ceil(subQuantity);
	}

	//개별 부자재 특수사항
	public static int calcSubQuantityForEtc(int constant, int add) {
		return (int) Math.ceil(constant + add);
	}

	//개별 부자재 가격
	public static int calcSubPrice(int eachQuantity, int subPrice) {
		return eachQuantity * subPrice;
	}

	
	public static double calcTotalTileKg(int tileboxQuantity, double kgPerBox) {
		return tileboxQuantity * kgPerBox;
	}
	
	//총 부자재 무게
	public static double calcTotalSubsKg(double finalArea, List<EstimateSub> list) {
		
		double totalSubsKg = 0;
		
		for (EstimateSub esub : list) {
			
			int eachSubQuantity = calcSubQuantity(finalArea, esub.getSubMaterial().getUsagePerm2().doubleValue());
			totalSubsKg += eachSubQuantity * esub.getSubMaterial().getKgBox();
		}

		return totalSubsKg;
		
	}

	//총 부자재 가격
	public static int calcTotalSubsCost(double finalArea, List<EstimateSub> list, int roomCount) {
		
		int totalSubsCost = 0;
		
		if(list==null)
			return __default_subs_cost;
		
		for (EstimateSub esub : list) {
			
			int eachSubQuantity = 0;
			
			if(esub.getSubMaterial().getUsageType().equals("m2"))
				eachSubQuantity = calcSubQuantity(finalArea, esub.getSubMaterial().getUsagePerm2());
			else if(esub.getSubMaterial().getUsageType().equals("room"))
				eachSubQuantity = calcSubQuantityForEtc(roomCount, esub.getSubMaterial().getUsageAdd());
			else
				eachSubQuantity = calcSubQuantityForEtc(0, esub.getSubMaterial().getUsageAdd());
			
			int eachSubCost = calcSubPrice(eachSubQuantity, esub.getSubMaterial().getCost());
			
			esub.setSubBoxAmount(eachSubQuantity);
			esub.setSubBoxCost(esub.getSubMaterial().getCost());
			esub.setSubBoxAmountCost(eachSubCost); 
			
			System.out.println("##########################################");
			System.out.println("##########################################");
			System.out.println("##########################################");
			System.out.println(esub.getSubMaterial().getUsagePerm2().doubleValue());
			System.out.println(eachSubQuantity);
			System.out.println(eachSubCost);
			System.out.println("##########################################");
			System.out.println("##########################################");
			System.out.println("##########################################");
			
			totalSubsCost += eachSubCost;
		}
		
		return totalSubsCost;
	}

	//시공비 계산 (1헤베)
//	private int calcConsCost(double finalArea, int pay) {
//		
//		//35,000 x 1박스(1.44m2)당 x 1.44
//		
//		//let dailyPaidPerWorker = 300000;
//		//let minDayToWork = 2;
//		//constructionCost =  minDayToWork * (2 * dailyPaidPerWorker);
//		
//		return (int) Math.floor(Math.ceil(finalArea) * pay);
//	}
	
	//시공비 계산 (1헤베) ver.2
//	public static int calcConsCostByTileQtt(int tileQtt, double meterPerBox, int pay, double rateMargin) {
//		
//		//35,000 x 1박스(1.44m2)당 x 1.44
//		
//		//let dailyPaidPerWorker = 300000;
//		//let minDayToWork = 2;
//		//constructionCost =  minDayToWork * (2 * dailyPaidPerWorker);
//		
//		return (int)((pay * tileQtt * meterPerBox) * (1+rateMargin));
//	}

	//시공비 계산 (1헤베) ver.3
//	public static int calcConsCostByTileQtt(int tileQtt, int pay, double rateMargin) {
//		
//		//35,000 x 1박스(1.44m2)당 x 1.44
//		
//		//let dailyPaidPerWorker = 300000;
//		//let minDayToWork = 2;
//		//constructionCost =  minDayToWork * (2 * dailyPaidPerWorker);
//		
//		return (int)( (tileQtt/2.0) * pay * (1+rateMargin) );
//	}
	
	//시공비 계산 (1헤베) ver.4
	public static int calcConsCostByTileQtt(int tileQtt, int pay) {
		
		//35,000 x 1박스(1.44m2)당 x 1.44
		
		//let dailyPaidPerWorker = 300000;
		//let minDayToWork = 2;
		//constructionCost =  minDayToWork * (2 * dailyPaidPerWorker);
		
		return (int)( (tileQtt/2.0) * pay );
	}
		
	//매니저비용 계산 ver.1
//	public static int calcManageCostByAcreage(int consCost, int acreage, int pay, double rateMargin) {
//		
//		//총 시공비 = * 양중비, 보양비, 타일시공비, 줄눈시공비, 폐기물처리비, 현장관리비
//		//총 시공비 = 평 * 매니저 단가 - 실제시공비
//		return (int)( (acreage*pay - consCost/(1+rateMargin)) * (1+rateMargin) );
//	}
	
	//매니저비용 계산 ver.2
	public static int calcManageCostByAcreage(int consCost, int acreage, int pay) {
		
		//총 시공비 = * 양중비, 보양비, 타일시공비, 줄눈시공비, 폐기물처리비, 현장관리비
		//총 시공비 = 평 * 매니저 단가 - 실제시공비
		return acreage*pay - consCost;
	}
	
	//총 시공비 계산 ver.1
	public static int calcConsAllCost(int totalConsCost, int totalManageCost, double rateMargin) {
		
		return (int)((totalConsCost + totalManageCost)*(1+rateMargin));
	}
	
	//운송비 계산
	public static int calcTransCost(double totalTileKg, double totalSubsKg) {
		
		//double totalTilePkg = tileboxQuantity * tileKgPerBox;
		
		int transportationPrice = 0;
		int transportationUpload = 0;
		int totalTransportationCost = 0;
		
		double totalWeight = totalTileKg + totalSubsKg;
		
		//1톤일 경우,
		if(totalWeight <= 1000){
			transportationPrice = 100000;
			transportationUpload = 100000;
			totalTransportationCost = transportationPrice + transportationUpload;
		  //console.log("1톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1톤 혹은 이하일 경우) : "+thousands_separators(totalTransportationCost)+"원");
		
		} else if(totalWeight > 1000 && totalWeight < 1500){
			transportationPrice = 110000;
			transportationUpload = 100000;	
			totalTransportationCost = transportationPrice + transportationUpload;	
		  //console.log("1.5톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1톤 이상 혹은 1.5톤일 경우) : "+thousands_separators(totalTransportationCost)+"원");
		
		} else if(totalWeight > 1500 && totalWeight < 2500){
			transportationPrice = 130000;
			transportationUpload = 100000;	
			totalTransportationCost = transportationPrice + transportationUpload;	
		  //console.log("1.5톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1톤 이상 혹은 1.5톤일 경우) : "+thousands_separators(totalTransportationCost)+"원");
		
		} else if(totalWeight > 2500 && totalWeight < 3500){
			transportationPrice = 140000;
			transportationUpload = 200000;	
			totalTransportationCost = transportationPrice + transportationUpload;	
		  //console.log("1.5톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1톤 이상 혹은 1.5톤일 경우) : "+thousands_separators(totalTransportationCost)+"원");
		
		} else {
			transportationPrice = 150000;
			transportationUpload = 200000;	
			totalTransportationCost = transportationPrice + transportationUpload;
		  //console.log("5톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1.5톤보다 더 많을 경우) : "+thousands_separators(totalTransportationCost)+"원");
		}
		
		return totalTransportationCost;
	}
	
	public static int calcWrapCost(int acreage, int wrapCost, double rateMargin) {
		return (int) (acreage * wrapCost *(1+rateMargin));
	}
	
	public static int calcLiftCost(int tileQtt, int liftCost, double rateMargin) {
		return (int) (tileQtt * liftCost *(1+rateMargin));
	}
	
	public static int calcDestroyCost(int acreage, int destroyCost) {
		return acreage * destroyCost;
	}
	
	//총 가격
	public static int calcFinalCost(int allTileCost, int allSubsCost, int consCost, int transCost, 
			int totalLiftCost, int totalDestroyCost, int totalWasteCost, int totalWrapCost) {
		//, boolean isConst
		
		if(allTileCost<1)
			return 0;
		
		//int allTileCost = calcTileCost(tileboxQuantity, tilePrice); // 최종 타일가격
		
//		if(isConst)
//			return (int) Math.floor((allTileCost + allSubsCost + consCost + transCost)*__vat);
//		else
//			return (int) Math.floor((allTileCost + allSubsCost + transCost)*__vat);
		
		//return (int) Math.floor((allTileCost + allSubsCost + consCost + transCost + totalLiftCost + totalDestroyCost + totalWasteCost)*(1+__margin_rate));
		return (int) Math.floor(allTileCost + allSubsCost + consCost + transCost + totalLiftCost + totalDestroyCost + totalWasteCost + totalWrapCost);
	}
	
	//총 가격 ver.2
	public static int calcFinalCostImp(int allTileCost, int allSubsCost, int consAllCost, 
			int transCost, int totalDestroyCost) {
		
		if(allTileCost<1)
			return 0;
		
		return (int) Math.floor(allTileCost + allSubsCost + consAllCost + transCost + totalDestroyCost);
	}
	
}
