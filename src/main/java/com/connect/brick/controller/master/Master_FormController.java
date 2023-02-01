package com.connect.brick.controller.master;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.code.EmailForm;
import com.connect.brick.service.FormService;

//@RequestMapping(value="/master")
@Controller
public class Master_FormController {

	@Autowired
	FormService formService;
	
	@RequestMapping(value=RequestMappingConstants._MASTER_FORM_EMAIL_LIST, method=RequestMethod.GET)
    public String emaillist(Model model, HttpServletRequest request) {
		
		
		List<EmailForm> forms = formService.getEmailFormAll();
		
		model.addAttribute("forms", forms);
		
		return "views/master/form/email_form_list";
	}
	
	@RequestMapping(value=RequestMappingConstants._MASTER_FORM_EMAIL_DETAIL, method=RequestMethod.GET)
    public String emaildetail(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		EmailForm form = formService.getEmailFormByNo(no);
		
		model.addAttribute("form", form);
		
		return "views/master/form/email_form_detail";
	}
	
}