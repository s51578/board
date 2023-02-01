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

import com.connect.brick.component.ValidComponent;
import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.Material;
import com.connect.brick.model.Order;
import com.connect.brick.service.OrderService;

//@RequestMapping(value="/master/order")
@Controller
public class Master_OrderController {

	@Autowired
    private OrderService orderService;
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ORDER_LIST, method=RequestMethod.GET)
    public String order(Model model, HttpServletRequest request) {
		
		List<Order> orders = orderService.getOrderAll();
		
		model.addAttribute("orders", orders);
		
		model.addAttribute("menu", "주문 관리");
		model.addAttribute("submenu", "신청 리스트");
		
		return "views/master/order/order_list";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ORDER_CREATE, method=RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {
		
		model.addAttribute("menu", "주문 관리");
		model.addAttribute("submenu", "신청 접수");
		
		return "views/master/order/order_create";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ORDER_REG, method=RequestMethod.POST)
    public String reg(Model model, HttpServletRequest request,
    		@ModelAttribute("orderForm") Order order) {
		
		boolean validCheck = false;
		
		try {
			
			validCheck = ValidComponent.isOrderListCheck(order);
			if(!validCheck) 
				throw new Exception();
			
		} catch (Exception e) {
			
			model.addAttribute("msg", "지정된 양식을 확인해 주세요.");
			return "redirect:"+ RequestMappingConstants._ERROR_LOAD + HttpStatus.INTERNAL_SERVER_ERROR.value();
			
		}
		
		if(validCheck) 
			orderService._reg_Order(order);
		
		return "redirect:" + RequestMappingConstants._MASTER_ORDER_LIST;
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ORDER_UPDATE, method=RequestMethod.GET)
    public String update(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		Order order = orderService.getOrderByNo(no);
		
		model.addAttribute("order", order);
		
		model.addAttribute("menu", "주문 관리");
		model.addAttribute("submenu", "신청 수정");
		
		return "views/master/order/order_update";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ORDER_MOD, method=RequestMethod.POST)
    public String mod(Model model, HttpServletRequest request,
    		@ModelAttribute("orderForm") Order order) {
		boolean validCheck = false;
		
		try {
			
			validCheck = ValidComponent.isOrderListCheck(order);
			if(!validCheck) 
				throw new Exception();
			
		} catch (Exception e) {
			
			model.addAttribute("msg", "지정된 양식을 확인해 주세요.");
			return "redirect:"+ RequestMappingConstants._ERROR_LOAD + HttpStatus.INTERNAL_SERVER_ERROR.value();
			
		}
		
		if(validCheck) 
			orderService._mod_Order(order);
		
		
		return "redirect:" + RequestMappingConstants._MASTER_ORDER_DETAIL + "?no=" + order.getNo();
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_ORDER_DETAIL, method=RequestMethod.GET)
    public String detail(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		Order order = orderService.getOrderByNo(no);
		
		model.addAttribute("order", order);
		
		model.addAttribute("menu", "주문 관리");
		model.addAttribute("submenu", "신청 상세");
		
		return "views/master/order/order_detail";
	}
	
	
}