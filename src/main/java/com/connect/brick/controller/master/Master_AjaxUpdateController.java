package com.connect.brick.controller.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.DpPopular;
import com.connect.brick.service.DisplayService;

//@RequestMapping(value="/ajax/update")
@Controller
@ComponentScan("com.connect.brick.component")
public class Master_AjaxUpdateController {
	
	@Value("${upload.image.locations.path}")
	private String uploadImageLocationPath;

	@Autowired
	private DisplayService displayService;
	
//	@RequestMapping(value = "/recommand", method = RequestMethod.POST)
//	public ResponseEntity imageUploadProcess(Model model, Authentication user,
//			MultipartHttpServletRequest request, 
//			@RequestParam(name = "rno") Long rno,
//			@RequestParam(name = "targetrank") int targetrank) {
//		
//		List<Recommand> recommands = displayService.getRecommandAll();
//
//		int maxRank = recommands.size();
//		
//		if(maxRank<0)
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		
//		Recommand recommand = displayService.getRecommandByNo(rno);
//
//		if(targetrank < 1 || targetrank > maxRank)
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		
//		displayService._mod_Recommand(recommand, targetrank);
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	@RequestMapping(value = RequestMappingConstants._AJAX_UPDATE_POPULAR, method = RequestMethod.POST)
	public ResponseEntity updatePopularRank(Model model, Authentication user,
			MultipartHttpServletRequest request, 
			@RequestParam(name = "pno") Long pno,
			@RequestParam(name = "targetrank") int targetrank) {
		
		List<DpPopular> populars= displayService.getPopularAll();

		int maxRank = populars.size();
		
		if(maxRank<0)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		DpPopular popular = displayService.getPopularByNo(pno);

		if(targetrank < 1 || targetrank > maxRank)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		displayService._mod_Popular(popular, targetrank);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/monthSales", method = RequestMethod.POST)
//	public ResponseEntity updateMonthSalesRank(Model model, Authentication user,
//			MultipartHttpServletRequest request, 
//			@RequestParam(name = "monthNo") Long monthNo,
//			@RequestParam(name = "targetrank") int targetrank) {
//		
//		List<MonthSales> MonthSalesList= displayService.getMonthSalesAll();
//
//		int maxRank = MonthSalesList.size();
//		
//		if(maxRank<0)
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		
//		MonthSales monthSales = displayService.getMonthSalesByNo(monthNo);
//
//		if(targetrank < 1 || targetrank > maxRank)
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		
//		displayService._mod_MonthSales(monthSales, targetrank);
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	
}