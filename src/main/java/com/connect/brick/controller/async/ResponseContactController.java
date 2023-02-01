package com.connect.brick.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.connect.brick.model.Contact;
import com.connect.brick.model.material.MaterialContents;
import com.connect.brick.service.EstimateService;
import com.connect.brick.service.MaterialService;

@RequestMapping(value="/service/ajax")
@Controller
public class ResponseContactController {

	@Autowired
	private EstimateService estimateService;

	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public ResponseEntity regContact(Model model, Authentication user,
			MultipartHttpServletRequest request, 
			@RequestParam(name = "cno") Long cno,
			@ModelAttribute Contact contact) {
		
		MaterialContents mtContents = materialService.getMaterialContentsByNo(cno);
		
		contact.setMtContents(mtContents);
		
		estimateService._reg_Contact(contact);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}