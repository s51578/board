package com.connect.brick.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.brick.component.ValidComponent;
import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.CostSheet;
import com.connect.brick.model.DpMaterial;
import com.connect.brick.model.DpPopular;
import com.connect.brick.model.Estimate;
import com.connect.brick.model.Material;
import com.connect.brick.model.Order;
import com.connect.brick.model.SubMaterial;
import com.connect.brick.model.data.RegionSd;
import com.connect.brick.model.dto._ApartmentDTO;
import com.connect.brick.model.material.MaterialContents;
import com.connect.brick.service.ApartService;
import com.connect.brick.service.DisplayService;
import com.connect.brick.service.EstimateService;
import com.connect.brick.service.MapService;
import com.connect.brick.service.MaterialService;
import com.connect.brick.service.OrderService;

@Controller
public class FormController {

	@Autowired
	MapService mapService;

	@Autowired
    private MaterialService materialService;
	
	@Autowired
	private EstimateService estimateService;
	
	@Autowired
	private DisplayService displayService;
	
	@Autowired
	private OrderService orderService;
	
	// googleForm
	@RequestMapping(value = RequestMappingConstants._GOOGLE_FORM, method = RequestMethod.GET)
	public String google_form(Model model, HttpServletRequest request) {		
		String title = "상담 신청 양식";	
		model.addAttribute("title", title);
		List<MaterialContents> popularFourlist = materialService.mtRank4();
		List<DpMaterial> allList = displayService.getDpMaterialAll();
		model.addAttribute("storeRankingPopularList", popularFourlist);
		model.addAttribute("allList", allList);
		return "views/service/form/google_form";
	}
	
	@RequestMapping(value=RequestMappingConstants._GOOGLE_FORM_REG, method=RequestMethod.POST)
    public String reg(Model model, HttpServletRequest request,
    		@ModelAttribute("orderForm") Order order,
    		@RequestParam(name="estimateNo1") Long estimateNo1,
    		@RequestParam(name="estimateNo2", required=false) Long estimateNo2,
    		@RequestParam(name="estimateNo3", required=false) Long estimateNo3,
    		@RequestParam(name="isElevator") int isElevator,
    		@RequestParam(name="isDestroy") String isDestroy
    		) {
		boolean validCheck = false;
		try {
			validCheck = ValidComponent.isOrderListCheck(order);
			if(!validCheck) 
				throw new Exception();
		} catch (Exception e) {
			model.addAttribute("msg", "지정된 양식을 확인해 주세요.");
			return "redirect:"+ RequestMappingConstants._ERROR_LOAD + HttpStatus.INTERNAL_SERVER_ERROR.value();
		}
		if(validCheck) { 
			List<Estimate> ests = new ArrayList<>();
			Estimate est1 = estimateService.getEstimateByNo(estimateNo1);
			if(estimateNo2 == null) {
				ests.add(est1);
				orderService._reg_order_estimate_form(order,ests);
				est1.setOrder(order);
				orderService._mod_estimate(est1,isElevator,isDestroy);
			}else if(estimateNo3 == null) {
				Estimate est2 = estimateService.getEstimateByNo(estimateNo2);
				ests.add(est1);
				ests.add(est2);
				orderService._reg_order_estimate_form(order,ests);
				est1.setOrder(order);
				est2.setOrder(order);
				orderService._mod_estimate(est1,isElevator,isDestroy);
				orderService._mod_estimate(est2,isElevator,isDestroy);
			} else {
				Estimate est2 = estimateService.getEstimateByNo(estimateNo2);
				Estimate est3 = estimateService.getEstimateByNo(estimateNo3);
				ests.add(est1);
				ests.add(est2);
				ests.add(est3);
				orderService._reg_order_estimate_form(order,ests);
				est1.setOrder(order);
				est2.setOrder(order);
				est3.setOrder(order);
				orderService._mod_estimate(est1,isElevator,isDestroy);
				orderService._mod_estimate(est2,isElevator,isDestroy);
				orderService._mod_estimate(est3,isElevator,isDestroy);
			}			
		}
		return "redirect:" + RequestMappingConstants._GOOGLE_FORM_COMPLETE;
	}
	
	// googleForm 완료
	@RequestMapping(value = RequestMappingConstants._GOOGLE_FORM_COMPLETE, method = RequestMethod.GET)
	public String googleFormComplete(Model model, HttpServletRequest request) {		
		
		return "views/service/form/google_form_complete";
	}
}