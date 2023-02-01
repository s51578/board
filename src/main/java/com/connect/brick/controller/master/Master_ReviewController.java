package com.connect.brick.controller.master;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.ConsReview;
import com.connect.brick.model.Material;

import com.connect.brick.service.ConsService;

//@RequestMapping(value="/master/review")
@Controller
public class Master_ReviewController {
	
	@Autowired
    private ConsService consService;
	
	
	
	//시공 후기 리스트
	@RequestMapping(value=RequestMappingConstants._MASTER_CONS_REVIEW_LIST, method=RequestMethod.GET)
    public String reviewList(Model model, HttpServletRequest request) {
		
		List<ConsReview> consReviewLists = consService.getConsReviewAll();
		
		//model.addAttribute("reviewlists", reviewlists);
		model.addAttribute("consReviewLists", consReviewLists);
		model.addAttribute("menu", "컨텐츠 관리");
		model.addAttribute("submenu", "시공사례 관리");
		
		return "views/master/cons_review/cons_review_list";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_CONS_REVIEW_CREATE, method=RequestMethod.GET)
    public String reviewCreate(Model model, HttpServletRequest request, Authentication user) {
		
		String id = user.getName();
		List<Material> materials = consService.getMaterialAll();
		model.addAttribute("menu", "컨텐츠 관리");
		model.addAttribute("submenu", "시공후기 등록");
		
		model.addAttribute("materials", materials);
		model.addAttribute("id", id);
		
		return "views/master/cons_review/cons_review_create";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_CONS_REVIEW_REG, method=RequestMethod.POST)
    public String reg(Model model, HttpServletRequest request,
    		@ModelAttribute("consReviewForm") ConsReview consReview) {
		
		ConsReview cr = consService._reg_ConsReview(consReview);
		
		return "redirect:" + RequestMappingConstants._MASTER_CONS_REVIEW_LIST;
		//return "redirect:"+RequestMappingConstants._MASTER_MATERIAL_DETAIL+"?no=" + mt.getNo();
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_CONS_REVIEW_DEL, method=RequestMethod.GET)
    public String subdelete(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		try {
			
			consService._del_ConsReview(no);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return "redirect:" + RequestMappingConstants._MASTER_CONS_REVIEW_LIST;
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_CONS_REVIEW_DETAIL, method=RequestMethod.GET)
    		public String subdetail(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		ConsReview consReview = consService.getConsReviewByNo(no);
		
		model.addAttribute("consReview", consReview);
		
		model.addAttribute("menu", "컨텐츠 관리");
		model.addAttribute("submenu", "시공후기 정보");
		
		return "views/master/cons_review/cons_review_detail";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_CONS_REVIEW_UPDATE, method=RequestMethod.GET)
    public String update(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		ConsReview consReview = consService.getConsReviewByNo(no);
		List<Material> materials = consService.getMaterialAll();
		model.addAttribute("materials", materials);
		model.addAttribute("consReview", consReview);
		model.addAttribute("menu", "컨텐츠 관리");
		model.addAttribute("submenu", "시공후기 수정");
		
		return "views/master/cons_review/cons_review_update";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_CONS_REVIEW_MOD, method=RequestMethod.POST)
    public String mod(Model model, HttpServletRequest request,
    		@ModelAttribute("consReviewUpdateform") ConsReview consReview
    		) {
		
		ConsReview cr = null;
		
		cr = consService._mod_ConsReview(consReview);
		
		return "redirect:" + RequestMappingConstants._MASTER_CONS_REVIEW_DETAIL+"?no=" + cr.getConsReviewNo();
		
	}
	
	
	
	
}