package com.connect.brick.controller.master;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.connect.brick.model.code.AptExc;
import com.connect.brick.model.data.RegionSd;
import com.connect.brick.model.data.RegionSg;
import com.connect.brick.service.ApartService;

//@RequestMapping(value="/master/contents")
@Controller
public class Master_DataController {

	@Autowired
    private ApartService apartService;
	
	@RequestMapping(value="/master/data", method=RequestMethod.GET)
    public String dataConfig(Model model, HttpServletRequest request) {
		
		List<RegionSd> rsds = apartService.getRegionSdAll();
		/* List<RegionSg> rsgs = apartService.getRegionSgAll(); */
		List<AptExc> aptExcs = apartService.getAptExcAll();
		
		model.addAttribute("aptExcs", aptExcs);
		model.addAttribute("rsds", rsds);
		/* model.addAttribute("rsgs", rsgs); */
		
		model.addAttribute("menu", "시스템 관리");
		model.addAttribute("submenu", "데이터");
		
		return "views/master/data/data_config";
	}
	
}