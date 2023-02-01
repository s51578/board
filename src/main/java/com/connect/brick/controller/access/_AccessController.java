package com.connect.brick.controller.access;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.connect.brick.config.RequestMappingConstants;

@Controller
public class _AccessController {

	@RequestMapping(value=RequestMappingConstants._ACCESS_LOGIN_ERROR, method=RequestMethod.POST)
    public String loginError(Model model, HttpServletRequest request) {
		
		String msg = null;
		
		Object r_msg = request.getAttribute("msg");
		
		if(r_msg!=null)
			msg = r_msg.toString();

		model.addAttribute("msg", msg);
		model.addAttribute("title", "로그인 에러");
		
		return "views/access/loginError";
	}
	
	
	@RequestMapping(value=RequestMappingConstants._ACCESS_LOGIN, method=RequestMethod.GET)
    public String login(Model model) {
		
		model.addAttribute("title", "로그인");
		return "views/access/login";
	}
	
	@RequestMapping(value=RequestMappingConstants._ACCESS_VALID, method=RequestMethod.POST)
    public String valid(Model model) {
		
		return "redirect:"+RequestMappingConstants._MAIN_URL;
	}
	
}