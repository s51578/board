package com.connect.brick.controller.async;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.brick.model.CostSheet;
import com.connect.brick.model.Estimate;
import com.connect.brick.model.EstimateDoc;
import com.connect.brick.model.Order;
import com.connect.brick.model.SubMaterial;
import com.connect.brick.model.dto._EstimateCalcAreaDTO;
import com.connect.brick.model.dto._EstimateResultCardDTO;
import com.connect.brick.model.material.MaterialContents;
import com.connect.brick.service.EstimateService;
import com.connect.brick.service.MaterialService;
import com.connect.brick.service.OrderService;

@RequestMapping(value="/service/ajax")
@Controller
public class ResponseEstimateController {

	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private EstimateService estimateService;

	@Autowired
    private OrderService orderService;
	
	@RequestMapping(value = "/estimate/min", method = RequestMethod.POST)
	public ResponseEntity getEstimateMin(Model model, HttpServletRequest request,
			@RequestParam(name="price") int price,
			@RequestParam(name="area") double area,
			@RequestParam(name="meter2Box") double meter2Box) {
		
		CostSheet cs = estimateService.getCostSheetByActive(true);
		
		Estimate estimate = new Estimate(price, area, meter2Box, cs);

		return new ResponseEntity<>(estimate, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/estimate", method = RequestMethod.POST)
	public ResponseEntity getEstimate(Model model, HttpServletRequest request,
			@RequestParam(name="costSheetNo") Long costSheetNo,
			@RequestParam(name="mtContentsNo") Long mtContentsNo,
			@RequestParam(name="dedArea") double dedArea,
			@RequestParam(name="roomCount") int roomCount,
			@RequestParam(name="bathCount") int bathCount,
			@RequestParam(name="isRoom") boolean isRoom,
			@RequestParam(name="isCons") boolean isCons) {
		
		CostSheet cs = estimateService.getCostSheetByNo(costSheetNo);
		MaterialContents mc = materialService.getMaterialContentsByNo(mtContentsNo);
		
		List<SubMaterial> subs = materialService.getSubMaterialByClassLg(mc.getMaterial().getMtClass().getClassLg());
		
		Estimate estimate = new Estimate(mc, subs, cs, dedArea, roomCount, bathCount, isRoom);
		
		//estimate.setContents(contents);
		//Estimate result = estimateService._reg_estimate(estimate);
		
		return new ResponseEntity<>(estimate, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/estimate/form", method = RequestMethod.POST)
	public ResponseEntity getEstimate(Model model, HttpServletRequest request,
			@RequestParam(name="costSheetNo") Long costSheetNo,
			@RequestParam(name="mtContentsNo") Long mtContentsNo,
			@RequestParam(name="isLivingRoom") boolean isLivingRoom,
			@RequestParam(name="isKitchen") boolean isKitchen,
			@RequestParam(name="isRoom") boolean isRoom,
			@RequestParam(name="isEntrance") boolean isEntrance, //현관
			@RequestParam(name="isVeranda") boolean isVeranda,
			@RequestParam(name="isExpandLiving") boolean isExpandLiving,
			@RequestParam(name="isExpandKitchen") boolean isExpandKitchen,
			@RequestParam(name="isExpandRoom") boolean isExpandRoom,
			@RequestParam(name="inSupArea") double inSupArea,
			@RequestParam(name="inDedArea") double inDedArea,
			@RequestParam(name="inConsArea") double inConsArea,
			@RequestParam(name="roomCount") int roomCount,
			@RequestParam(name="bathCount") int bathCount,
			@RequestParam(name="typeHouse") int typeHouse) {
		
		CostSheet cs = estimateService.getCostSheetByNo(costSheetNo);
		MaterialContents mc = materialService.getMaterialContentsByNo(mtContentsNo);
		
		List<SubMaterial> subs = materialService.getSubMaterialByClassLg(mc.getMaterial().getMtClass().getClassLg());
		
		_EstimateCalcAreaDTO dto = new _EstimateCalcAreaDTO(inSupArea, inDedArea, inConsArea, isLivingRoom, isKitchen, 
				isRoom, isEntrance, isVeranda, isExpandLiving, isExpandKitchen, isExpandRoom, roomCount, bathCount, typeHouse);
		
		Estimate estimate = new Estimate(mc, subs, cs, dto);
		
		Estimate result = estimateService._reg_estimate(estimate);
		
		_EstimateResultCardDTO resultDto = new _EstimateResultCardDTO();
		
		resultDto.setEstimate(result);
		resultDto.setMaterial(result.getMtContents().getMaterial());
		
		
		return new ResponseEntity<>(resultDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/estimate/form/del", method = RequestMethod.POST)
	public ResponseEntity delEstimate(Model model, HttpServletRequest request,
			@RequestParam(name="estimateNo") Long estimateNo) {
		Estimate estimate = estimateService.getEstimateByNo(estimateNo);
		
		try {
			estimateService._del_order_estimate(estimate);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@RequestMapping(value = "/estimate/order/calc", method = RequestMethod.POST)
	public ResponseEntity getEstimateCalc(Model model, HttpServletRequest request,
			@RequestParam(name="orderNo") Long orderNo,
    		@RequestParam(name="costSheetNo") Long costSheetNo,
    		@RequestParam(name="mtContentsNo") Long mtContentsNo,
    		@RequestParam(name="areaCons") double areaCons) {
		
		Order order = orderService.getOrderByNo(orderNo);
		CostSheet cs = estimateService.getCostSheetByNo(costSheetNo);
		MaterialContents mc = materialService.getMaterialContentsByNo(mtContentsNo);
		
		List<SubMaterial> subs = materialService.getSubMaterialByClassLg(mc.getMaterial().getMtClass().getClassLg());
		
		Estimate estimate = new Estimate(mc, subs, cs, areaCons);
		
		return new ResponseEntity<>(estimate, HttpStatus.OK);
	}
}