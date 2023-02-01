package com.connect.brick.controller.master;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.Material;
import com.connect.brick.model.SubMaterial;
import com.connect.brick.model.code.ClassLg;
import com.connect.brick.model.code.MainColor;
import com.connect.brick.model.code.Country;
import com.connect.brick.model.code.SurfaceTexture;
import com.connect.brick.model.material.MaterialColor;
import com.connect.brick.model.material.MaterialSpace;
import com.connect.brick.service.CodeService;
import com.connect.brick.service.MaterialService;
import com.connect.brick.util.JsonUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ArrayNode;

//@RequestMapping(value="/master/material")
@Controller
public class Master_MaterialController {

	@Autowired
    private MaterialService materialService;
	
	@Autowired
    private CodeService codeService;
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_LIST, method=RequestMethod.GET)
    public String list(Model model, HttpServletRequest request) {
		
		List<Material> materials = materialService.getMaterialAll();
		Map<String, List> codes = codeService.getCodesAll();
		
		model.addAttribute("codes", codes);
		model.addAttribute("materials", materials);
		
		// 페이징
//		  int page = 0; 
//        int size = 10;
//        
//        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
//            page = Integer.parseInt(request.getParameter("page")) - 1;
//        }
//
//        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
//            size = Integer.parseInt(request.getParameter("size"));
//        }
//		
//		model.addAttribute("materials", materialRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDate"))));
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "마감재 관리");
		
		return "views/master/material/material_list";
	}

	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_DETAIL, method=RequestMethod.GET)
    public String detail(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		Material material = materialService.getMaterialByNo(no);
		
		model.addAttribute("material", material);
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "상품 정보");
		
		return "views/master/material/material_detail";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_CREATE, method=RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {
		
		Map<String, List> codes = codeService.getCodesAll();
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "상품 등록");

		//code
		model.addAttribute("codes", codes);
		
		return "views/master/material/material_create";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_REG, method=RequestMethod.POST)
    public String reg(Model model, HttpServletRequest request,
    		@ModelAttribute("materialform") Material material,
    		@RequestParam(name = "isOpen") boolean isOpen) {
		
		if(material.getMtSpace()==null) {
			MaterialSpace ms = new MaterialSpace();
			material.setMtSpace(ms);
		}
		
		Material mt = materialService._reg_Material(material, isOpen);
		
		return "redirect:" + RequestMappingConstants._MASTER_MATERIAL_LIST;
		//return "redirect:"+RequestMappingConstants._MASTER_MATERIAL_DETAIL+"?no=" + mt.getNo();
	}

	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_UPDATE, method=RequestMethod.GET)
    public String update(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		Material material = materialService.getMaterialByNo(no);
		Map<String, List> codes = codeService.getCodesAll();
		
		model.addAttribute("material", material);
		
		//code
		model.addAttribute("codes", codes);
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "마감재 수정");
		
		return "views/master/material/material_update";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_MOD, method=RequestMethod.POST)
    public String mod(Model model, HttpServletRequest request,
    		@ModelAttribute("materialform") Material material,
    		@RequestParam(name = "isOpen") boolean isOpen) {
		
		if(material.getMtSpace()==null) {
			MaterialSpace ms = new MaterialSpace();
			material.setMtSpace(ms);
		}
		
		Material mt = materialService._mod_Material(material, isOpen);

		return "redirect:" + RequestMappingConstants._MASTER_MATERIAL_LIST;
		//return "redirect:"+RequestMappingConstants._MASTER_MATERIAL_DETAIL+"?no=" + mt.getNo();
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_DEL, method=RequestMethod.GET)
    public String delete(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		try {
			
			materialService._del_Material(no);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return "redirect:" + RequestMappingConstants._MASTER_MATERIAL_LIST;
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_SUB_LIST, method=RequestMethod.GET)
    public String sub(Model model, HttpServletRequest request) {
		
		List<SubMaterial> subs = materialService.getSubMaterialAll();
		
		model.addAttribute("subs", subs);
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "부자재 관리");
		
		return "views/master/material/sub/sub_list";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_SUB_DETAIL, method=RequestMethod.GET)
    public String subdetail(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		SubMaterial sub = materialService.getSubMaterialByNo(no);
		
		model.addAttribute("sub", sub);
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "부자재 정보");
		
		return "views/master/material/sub/sub_detail";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_SUB_CREATE, method=RequestMethod.GET)
    public String subcreate(Model model, HttpServletRequest request) {
		
		List<ClassLg> classLgs = codeService.getClassLgAll();
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "부자재 등록");
		
		model.addAttribute("classLgs", classLgs);
		
		return "views/master/material/sub/sub_create";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_SUB_REG, method=RequestMethod.POST)
    public String subreg(Model model, HttpServletRequest request,
    		@ModelAttribute("submaterialform") SubMaterial sub,
    		@RequestParam(name = "filesInfo") String filesInfo) {
		
		try {
			SubMaterial mt = materialService._reg_SubMaterial(sub, filesInfo);
			
		} catch (JsonParseException | JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:" + RequestMappingConstants._MASTER_MATERIAL_SUB_LIST;
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_SUB_UPDATE, method=RequestMethod.GET)
    public String subupdate(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		SubMaterial sub = materialService.getSubMaterialByNo(no);
		List<ClassLg> classLgs = codeService.getClassLgAll();
		
		ArrayNode jss = JsonUtils.makeFileInformation(sub);
		
		model.addAttribute("modifyInfo", jss);
		model.addAttribute("sub", sub);
		model.addAttribute("classLgs", classLgs);
		
		model.addAttribute("menu", "스토어 관리");
		model.addAttribute("submenu", "부자재 수정");
		
		return "views/master/material/sub/sub_update";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_SUB_MOD, method=RequestMethod.POST)
    public String submod(Model model, HttpServletRequest request,
    		@ModelAttribute("submaterialform") SubMaterial material,
    		@RequestParam(name = "filesInfo") String filesInfo) {
		
		SubMaterial sub = null; 
				
		try {

			sub = materialService._mod_SubMaterial(material, filesInfo);

		} catch (JsonParseException | JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:"+RequestMappingConstants._MASTER_MATERIAL_SUB_DETAIL+"?no=" + sub.getNo();
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_MATERIAL_SUB_DEL, method=RequestMethod.GET)
    public String subdelete(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		try {
			
			materialService._del_SubMaterial(no);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return "redirect:" + RequestMappingConstants._MASTER_MATERIAL_SUB_LIST;
	}
	
}