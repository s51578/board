package com.connect.brick.controller.master;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.Contact;
import com.connect.brick.model.DpMaterial;
import com.connect.brick.model.Material;
import com.connect.brick.model.SubMaterial;
import com.connect.brick.service.DisplayService;
import com.connect.brick.service.EstimateService;
import com.connect.brick.service.MaterialService;
import com.connect.brick.service.QuestionService;

//@RequestMapping(value="/master")
@Controller
public class Master_MainController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	EstimateService estimateService;
	
	@Autowired
	DisplayService displayService;
	
	@Autowired
    private MaterialService materialService;
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MAIN, method=RequestMethod.GET)
    public String main(Model model, HttpServletRequest request) {
		
		List<Material> materials = materialService.getMaterialAll();
		List<SubMaterial> subMaterials = materialService.getSubMaterialAll();
//		List<Contents> contentsList = displayService.getContentsAll();
		List<DpMaterial> dmList = displayService.getDpMaterialAll();
		List<Contact> contactList = estimateService.getContactAll();
		
		int materialCount = materials.size();
		int subMaterialCount = subMaterials.size();
		int dmCount = dmList.size();
		int contactCount = contactList.size();
		
		model.addAttribute("materials", materials);
		model.addAttribute("materialCount", materialCount);
		model.addAttribute("subMaterialCount", subMaterialCount);
		model.addAttribute("contentsCount", dmCount);
		model.addAttribute("contactCount", contactCount);
		
		//return "views/master/master_main";
		return "redirect:" + RequestMappingConstants._MASTER_MATERIAL_LIST;
	}
	
	@RequestMapping(value="/master/main/new", method=RequestMethod.GET)
    public String mainew(Model model, HttpServletRequest request) {
		
		List<Material> materials = materialService.getMaterialAll();
		List<SubMaterial> subMaterials = materialService.getSubMaterialAll();
//		List<Contents> contentsList = displayService.getContentsAll();
		List<DpMaterial> dmList = displayService.getDpMaterialAll();
		List<Contact> contactList = estimateService.getContactAll();
		
		int materialCount = materials.size();
		int subMaterialCount = subMaterials.size();
		int dmCount = dmList.size();
		int contactCount = contactList.size();
		
		model.addAttribute("materials", materials);
		model.addAttribute("materialCount", materialCount);
		model.addAttribute("subMaterialCount", subMaterialCount);
		model.addAttribute("contentsCount", dmCount);
		model.addAttribute("contactCount", contactCount);
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "상품 관리");
		
		return "views/master/master_main_new";
	}
	
}