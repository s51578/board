package com.connect.brick.controller.master;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.DpMaterial;
import com.connect.brick.model.DpPopular;
import com.connect.brick.service.DisplayService;

//@RequestMapping(value="/master/contents")
@Controller
public class Master_DisplayController {

	@Autowired
    private DisplayService displayService;
	
	@RequestMapping(value=RequestMappingConstants._MASTER_POPULAR_LIST, method=RequestMethod.GET)
    public String popularList(Model model, HttpServletRequest request) {
		
		List<DpPopular> populars = displayService.getPopularAll();
		List<DpMaterial> dps = displayService.getDpMaterialAll();

		List<DpMaterial> notlists = new ArrayList<>();
		
		for (DpMaterial dp : dps) {
			
			boolean check = true;
			for (DpPopular popular : populars) {
				if(dp.getDpNo().equals(popular.getDpMaterial().getDpNo()))
					check = false;
			}
			
			if(check)
				notlists.add(dp);
		}
		
		model.addAttribute("notlists", notlists);
		model.addAttribute("populars", populars);
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "인기순 관리");
		
		return "views/master/contents/popular_list";
	}
	
	@RequestMapping(value = RequestMappingConstants._MASTER_POPULAR_ADD, method = RequestMethod.GET)
	public String popularAdd(Model model, HttpServletRequest request, @RequestParam(name = "dpNo") Long dpNo) {
		
		DpMaterial dp = displayService.getDpMaterialByNo(dpNo);
		
		DpPopular already = displayService.getPopularByDpMaterial(dp);
		
		if(already!=null) {
			model.addAttribute("msg", "이미 등록되었습니다.");
			return "redirect:"+RequestMappingConstants._ERROR_LOAD + HttpStatus.BAD_REQUEST.value();
		}
			
		DpPopular popular = new DpPopular();
		
		popular.setDpMaterial(dp);
		
		displayService._reg_Popular(popular);
		
		return "redirect:"+ RequestMappingConstants._MASTER_POPULAR_LIST;
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_POPULAR_DEL, method=RequestMethod.GET)
    public String popularDel(Model model, HttpServletRequest request,
    		@RequestParam(name = "dpNo") Long dpNo) {
		
		DpMaterial dp = displayService.getDpMaterialByNo(dpNo);
		
		if(dp==null) {
			model.addAttribute("msg", "삭제할 노출 제품이 없습니다.");
			return RequestMappingConstants._ERROR_LOAD + HttpStatus.BAD_REQUEST.value();
		}
		
		displayService._del_Popular(dp);
		
		return "redirect:"+ RequestMappingConstants._MASTER_POPULAR_LIST;
	}
	
}