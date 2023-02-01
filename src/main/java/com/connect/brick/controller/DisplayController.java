package com.connect.brick.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.brick.component.SearchComponent;
import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.ConsReview;
import com.connect.brick.model.DpMaterial;
import com.connect.brick.model.Material;
import com.connect.brick.model.code.MainColor;
import com.connect.brick.model.dto._ViewedContentsInfo;
import com.connect.brick.service.CodeService;
import com.connect.brick.service.ConsService;
import com.connect.brick.service.DisplayService;
import com.connect.brick.service.ImageService;
import com.connect.brick.service.MaterialService;
import com.connect.brick.util.TimeUtils;

@Controller
public class DisplayController {

	@Autowired
    private MaterialService materialService;
	
	@Autowired
    private ImageService imageService;
	
	@Autowired
    private DisplayService displayService;
	
	@Autowired
    private CodeService codeService;
	
	@Autowired 
	private ConsService consService;
	
	@RequestMapping(value=RequestMappingConstants._DP_DETAIL_PRODUCT, method=RequestMethod.GET)
    public String detailByMaterial(Model model, HttpServletRequest request,
    		@RequestParam(name="pno") Long pno) {
		
		Material mt = materialService.getMaterialByNo(pno);
		Map<String, List> codes = codeService.getCodesAll();
		
		if(mt==null) {
			//request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST);
			model.addAttribute("msg", "등록되지 않는 상품입니다.");
			return "redirect:"+RequestMappingConstants._ERROR_LOAD + HttpStatus.BAD_REQUEST.value();
		}
		
		DpMaterial dpMaterial = mt.getDpMaterial();
		
		if(dpMaterial==null) {
			//request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST);
			model.addAttribute("msg", "존재하지않거나 판매 중지된 상품입니다.");
			return "redirect:"+RequestMappingConstants._ERROR_LOAD + HttpStatus.BAD_REQUEST.value();
		}
		
		List<DpMaterial> mdPickList = displayService.getMdPickAll();
		//List<Recommand> recommandlist = displayService.getRecommandAll();
		
		//조회수 +1
		materialService.updateHit(dpMaterial.getMaterial().getMtContents());
		
		String title = dpMaterial.getMaterial().getMtClass().getClassLg().getClassName() + " | " + dpMaterial.getMaterial().getCbName();
		
		HttpSession session = request.getSession();

		_ViewedContentsInfo info = new _ViewedContentsInfo();
		
		info.setDpNo(dpMaterial.getDpNo());
		
		LocalDateTime now = LocalDateTime.now();
		String nowString = TimeUtils.getTimeFormatDate(now);
		info.setViewedDate(nowString);
		
		@SuppressWarnings("unchecked")
		List<_ViewedContentsInfo> watchProductList = (List<_ViewedContentsInfo>)session.getAttribute("product");
		
		//Queue<ViewedContentsInfo> watched = (Queue<ViewedContentsInfo>)session.getAttribute("product");
		
		if(watchProductList == null || watchProductList.isEmpty()) {
			
			watchProductList = new ArrayList<>();
			session.setMaxInactiveInterval(30*60); // 30분
			session.setAttribute("product", watchProductList);
			watchProductList.add(info);
			
		} else {
			
			boolean isContain = false;
			
			for(int i=0; i<watchProductList.size(); i++) {
				if(watchProductList.get(i).getDpNo().equals(info.getDpNo())) {
					watchProductList.remove(i);
					watchProductList.add(info);
					isContain = true;
					break;
				}
			}
			
			if(!isContain && watchProductList.size() < 10) {
				watchProductList.add(info);
			}
			
		}
		
		Collections.reverse(watchProductList);
		
		String ogImage = dpMaterial.getMaterial().getMainImage().getThumbnailPath() + "/" + dpMaterial.getMaterial().getMainImage().getThumbnailName(); 
		
		model.addAttribute("mdPickList", mdPickList);
		//컨텐츠 내용 - 관리자단 확인
		model.addAttribute("contents", dpMaterial);
		model.addAttribute("codes", codes);
		model.addAttribute("title", title);
		
		model.addAttribute("ogImage", "/image_storage/" + ogImage);
		
		return "views/service/details/contents/contents_detail_v3";
	}
	
	@RequestMapping(value=RequestMappingConstants._DP_DETAIL, method=RequestMethod.GET)
    public String detail(Model model, HttpServletRequest request,
    		@RequestParam(name="no") Long no) {
		
		DpMaterial dm = displayService.getDpMaterialByNo(no);
		
		if(dm==null) {
			model.addAttribute("msg", "존재하지않거나 판매 중지된 상품입니다.");
			return RequestMappingConstants._ERROR_LOAD + HttpStatus.BAD_REQUEST.value();
		} else
			return "redirect:" + RequestMappingConstants._DP_DETAIL_PRODUCT + "?pno=" + dm.getMaterial().getNo();
		
	}
	

	@RequestMapping(value = RequestMappingConstants._DETAILS_STORE_MAIN, method = RequestMethod.GET)
	public String storeMain(Model model, HttpServletRequest request, 
			@RequestParam(name="sort", required = false) String sort, 
			@RequestParam(name="type", required = false) String type,
			@RequestParam(name="color", required = false) List<Long> color,
			@RequestParam(name="surface", required = false) Long surfaceNo,
			@RequestParam(name="search", required = false) String search) {

		if(sort==null || type==null)
			return "redirect:"+RequestMappingConstants._DETAILS_STORE_MAIN+"?sort=popular&type=asc";
		
		String surface = null;
		
		if(surfaceNo!=null) {
			if(surfaceNo==2)
				surface = "유광";
			else
				surface = "무광";
		}
		
		long beforeTime = System.currentTimeMillis();
		List<DpMaterial> mdPickList = displayService.getMdPickAll();
		//List<DpMaterial> dpMaterials = displayService.getDpMaterialSortAll(sort, type);
		List<MainColor> colors = codeService.getColorToneAll();
		List<String> searchColorName = SearchComponent.getColorTonesByNo(colors, color);
		
		List<DpMaterial> dpMaterials = displayService.getDpMaterialAllBySortTag(sort, type, color, surface);
		//List<Material> materials = materialService.getMaterialAll();
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTime);
		
		dpMaterials = SearchComponent.filter(dpMaterials, searchColorName, search);
		
		String title = "온라인샵";
		
		model.addAttribute("mdPickList", mdPickList);
		model.addAttribute("dpMaterials", dpMaterials);
		model.addAttribute("colors", colors);
		model.addAttribute("sort", sort);
		model.addAttribute("type", type);
		model.addAttribute("color", color);
		model.addAttribute("surface", surface);
		model.addAttribute("search", search);
		model.addAttribute("title", title);
		
		System.out.println("############################################################################");
		System.out.println("DB 로딩 시간 : " + secDiffTime);
		System.out.println("############################################################################");
		
		return "views/service/details/store/store_main";
	}
	
	@RequestMapping(value = RequestMappingConstants._DETAILS_VIEWED_PRODUCT, method = RequestMethod.GET)
	public String product(Model model, HttpServletRequest request) {
		String title = "최근 상품";
		Object ob = request.getSession().getAttribute("product");
		//List<Contents> list = new ArrayList<>();
				
		if(ob!=null) {
			
			@SuppressWarnings("unchecked")
			List<_ViewedContentsInfo> watchProductList = (List<_ViewedContentsInfo>) ob;
		
			for (_ViewedContentsInfo viewedContentsInfo : watchProductList) {
				DpMaterial dm = displayService.getDpMaterialByNo(viewedContentsInfo.getDpNo());
				
				if(dm!=null)
					viewedContentsInfo.setDpMaterial(dm);
				
			}

			//Collections.reverse(watchProductList);
			
			model.addAttribute("watchProductList", watchProductList);
		}
		
		model.addAttribute("title", title);
		
		return "views/service/details/viewedProduct";
	}
	
	@RequestMapping(value=RequestMappingConstants._CONSREVIEW_DETAIL, method = RequestMethod.GET)
	public String consReviewDetail(Model model, HttpServletRequest request,
			@RequestParam(name = "no") Long no) {
	
	
	ConsReview consReview = consService.getConsReviewByNo(no);
	model.addAttribute("consReview", consReview);
	
	
	return "views/service/cons_review/cons_review_detail";
	
}
	
	
	
}