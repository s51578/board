package com.connect.brick.controller.master;

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

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.CostSheet;
import com.connect.brick.model.Estimate;
import com.connect.brick.model.EstimateDoc;
import com.connect.brick.model.EstimateDocItem;
import com.connect.brick.model.Material;
import com.connect.brick.model.Order;
import com.connect.brick.model.SubMaterial;
import com.connect.brick.model.code.ClassLg;
import com.connect.brick.model.dto._EstimateDTO;
import com.connect.brick.model.material.MaterialContents;
import com.connect.brick.service.CodeService;
import com.connect.brick.service.EstimateService;
import com.connect.brick.service.MaterialService;
import com.connect.brick.service.OrderService;

//@RequestMapping(value="/master/contents")
@Controller
public class Master_EstimateController {

	@Autowired
    private MaterialService materialService;
	
	@Autowired
    private EstimateService estimateService;
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
    private CodeService codeService;
	
	//견적 테스트
	@RequestMapping(value="/master/estimate", method=RequestMethod.GET)
    public String estimateMain(Model model, HttpServletRequest request,
    		@RequestParam(name="costSheetNo", required=false) Long costSheetNo) {
		
		if(costSheetNo==null)
			return "redirect:/master/estimate?costSheetNo=1";
		
		CostSheet costSheet = estimateService.getCostSheetByNo(costSheetNo);
		
		ClassLg classLg = codeService.getClassLgByNo(costSheet.getClassLg().getNo());
		
		List<SubMaterial> subs = materialService.getSubMaterialByClassLg(classLg);
		List<Material> mList = materialService.getMaterialAll();
//		
		model.addAttribute("mList", mList);
		
		model.addAttribute("costSheet", costSheet);
		model.addAttribute("subs", subs);
		
		return "views/master/estimate/estimate_main";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ESTIMATE_LIST, method=RequestMethod.GET)
    public String list(Model model, HttpServletRequest request) {
		
	
		model.addAttribute("menu", "주문 관리");
		model.addAttribute("submenu", "주문 내역");
		
		return "views/master/estimate/estimate_list";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ESTIMATE_CREATE, method=RequestMethod.GET)
    public String create(Model model, HttpServletRequest request,
    		@RequestParam(name="orderNo") Long orderNo) {
		
		Order order = orderService.getOrderByNo(orderNo);
		
		List<CostSheet> costSheets = estimateService.getCostSheetAll();
		List<Material> mList = materialService.getMaterialAll();

		model.addAttribute("mList", mList);
		model.addAttribute("costSheets", costSheets);

		model.addAttribute("order", order);
		
		model.addAttribute("menu", "주문 관리");
		model.addAttribute("submenu", "견적 생성");
		
		return "views/master/estimate/estimate_create";
	}
	
	/*
	 * @RequestMapping(value=RequestMappingConstants._MASTER_ESTIMATE_REG,
	 * method=RequestMethod.POST) public String reg(Model model, HttpServletRequest
	 * request,
	 * 
	 * @RequestParam(name="orderNo") Long orderNo,
	 * 
	 * @RequestParam(name="costSheetNo") Long costSheetNo,
	 * 
	 * @RequestParam(name="mtContentsNo") Long mtContentsNo,
	 * 
	 * @RequestParam(name="areaCons") double areaCons) {
	 * 
	 * Order order = orderService.getOrderByNo(orderNo); CostSheet cs =
	 * estimateService.getCostSheetByNo(costSheetNo); MaterialContents mc =
	 * materialService.getMaterialContentsByNo(mtContentsNo);
	 * 
	 * List<SubMaterial> subs =
	 * materialService.getSubMaterialByClassLg(mc.getMaterial().getMtClass().
	 * getClassLg());
	 * 
	 * Estimate estimate = new Estimate(mc, subs, cs, areaCons);
	 * 
	 * //estimate.setContents(contents); Order result =
	 * orderService._reg_order_estimate(order, estimate);
	 * 
	 * return "redirect:" + RequestMappingConstants._MASTER_ORDER_DETAIL + "?no=" +
	 * result.getNo(); }
	 */
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ESTIMATE_REG, method=RequestMethod.POST)
    public String reg(Model model, HttpServletRequest request,
    		@ModelAttribute("estimateForm") _EstimateDTO dto) {
		
		Long orderNo = dto.getOrderNo();
		Long mtContentsNo = dto.getMtContentsNo();
		Long costSheetNo = dto.getCostSheetNo();
		Double areaCons = dto.getAreaCons();
		EstimateDoc ed = dto.getEstimateDoc();
		Order order = orderService.getOrderByNo(orderNo);
		CostSheet cs = estimateService.getCostSheetByNo(costSheetNo);
		MaterialContents mc = materialService.getMaterialContentsByNo(mtContentsNo);
		
		List<SubMaterial> subs = materialService.getSubMaterialByClassLg(mc.getMaterial().getMtClass().getClassLg());
		
		Estimate estimate = new Estimate(mc, subs, cs, areaCons);
		estimate.setOrder(order);
		estimate.setEstimateDoc(ed);
		ed.setEstimate(estimate);
		
		//estimate.setContents(contents);
		Order result = orderService._reg_order_estimate(order, estimate);
		
		return "redirect:" + RequestMappingConstants._MASTER_ORDER_DETAIL + "?no=" + result.getNo();
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ESTIMATE_UPDATE, method=RequestMethod.GET)
    public String update(Model model, HttpServletRequest request,
    		@RequestParam(name="orderNo") Long orderNo,
    		@RequestParam(name="docNo") Long docNo) {
		
		Order order = orderService.getOrderByNo(orderNo);
		EstimateDoc estimateDoc = estimateService.getEstimateDocByNo(docNo);
		List<CostSheet> costSheets = estimateService.getCostSheetAll();
		List<Material> mList = materialService.getMaterialAll();

		model.addAttribute("mList", mList);
		model.addAttribute("costSheets", costSheets);

		model.addAttribute("order", order);
		model.addAttribute("estimate", estimateDoc.getEstimate());
		
		model.addAttribute("menu", "주문 관리");
		model.addAttribute("submenu", "견적 상세");
		
		return "views/master/estimate/estimate_update";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ESTIMATE_MOD, method=RequestMethod.POST)
    public String mod(Model model, HttpServletRequest request,
    		@RequestParam(name="estimateNo") Long estimateNo,
    		@ModelAttribute("estimateForm") _EstimateDTO dto) {
		
		Long orderNo = dto.getOrderNo();
		Long mtContentsNo = dto.getMtContentsNo();
		Long costSheetNo = dto.getCostSheetNo();
		Double areaCons = dto.getAreaCons();
		EstimateDoc ed = dto.getEstimateDoc();
		
		Order order = orderService.getOrderByNo(orderNo);
		CostSheet cs = estimateService.getCostSheetByNo(costSheetNo);
		MaterialContents mc = materialService.getMaterialContentsByNo(mtContentsNo);
		
		List<SubMaterial> subs = materialService.getSubMaterialByClassLg(mc.getMaterial().getMtClass().getClassLg());
		
		Estimate estimate = new Estimate(mc, subs, cs, areaCons);
		estimate.setNo(estimateNo);
		estimate.setOrder(order);
		estimate.setEstimateDoc(ed);
		ed.setEstimate(estimate);
		
		//estimate.setContents(contents);
		Estimate result = orderService._mod_order_estimate(estimate);
		
		return "redirect:" + RequestMappingConstants._MASTER_ESTIMATE_DETAIL + "?orderNo=" + order.getNo() + "&docNo=" + result.getEstimateDoc().getNo();
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ESTIMATE_DETAIL, method=RequestMethod.GET)
    public String detail(Model model, HttpServletRequest request,
    		@RequestParam(name="orderNo") Long orderNo,
    		@RequestParam(name="docNo") Long docNo) {
		
		Order order = orderService.getOrderByNo(orderNo);
		EstimateDoc estimateDoc = estimateService.getEstimateDocByNo(docNo);
		
		for (EstimateDocItem item : estimateDoc.getItems()) {
			System.out.println(item.getItemName());
		}
		
		try {
			
			String phoneNum = estimateDoc.getEstimate().getOrder().getCustomer().getPhoneNum();
			String pwd = phoneNum.substring(phoneNum.length()-4, phoneNum.length());
			
			model.addAttribute("pwd", pwd);
			
		} catch(Exception e) {
			model.addAttribute("msg", "고객 핸드폰 번호 형식이 잘못되었습니다.");
			return "redirect:"+ RequestMappingConstants._ERROR_LOAD + HttpStatus.INTERNAL_SERVER_ERROR.value();
		}
	
		model.addAttribute("order", order);
		model.addAttribute("estimate", estimateDoc.getEstimate());
		
		model.addAttribute("menu", "주문 관리");
		model.addAttribute("submenu", "견적 상세");
		
		return "views/master/estimate/estimate_detail";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ESTIMATE_DEL, method=RequestMethod.POST)
    public String del(Model model, HttpServletRequest request,
    		@RequestParam(name="docNo") Long docNo) {
		
		String referer = request.getHeader("Referer");
		
		EstimateDoc estimateDoc = estimateService.getEstimateDocByNo(docNo);
		
		try {
			estimateService._del_order_estimate(estimateDoc.getEstimate());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		model.addAttribute("estimate", estimateDoc.getEstimate());
		
		return "redirect:" + referer;
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ESTIMATE_STATE, method=RequestMethod.GET)
    public String state(Model model, HttpServletRequest request,
    		@RequestParam(name="docNo") Long docNo,
    		@RequestParam(name="toState") Integer toState) {
		
		String referer = request.getHeader("Referer");
		
		EstimateDoc estimateDoc = estimateService.getEstimateDocByNo(docNo);
		
		EstimateDoc result = estimateService._reg_EstimateDoc_State(estimateDoc, toState);
		
		model.addAttribute("menu", "주문 관리");
		model.addAttribute("submenu", "견적 상세");

		return "redirect:" + referer;
	}
	
}