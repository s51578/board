package com.connect.brick.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.service.DisplayService;

@Controller
public class _ViewEncodedController {

	@Autowired
	private DisplayService displayService;
	
	//ajax
	@RequestMapping(value = RequestMappingConstants._GET_ENCODE, method = RequestMethod.POST)
	public ResponseEntity getEncode(Model model, HttpServletRequest request,
			@RequestParam(name = "urlstring") String urlstring) {
		
		String encodeVal = null;
		System.out.println(urlstring+"URL테스트");
		System.out.println(urlstring);
		
		try {
			encodeVal = Base64.getUrlEncoder().encodeToString(urlstring.getBytes());
		} catch (Exception e) {
			model.addAttribute("msg", "잘못된 URL입니다");
			return new ResponseEntity<>(encodeVal, HttpStatus.BAD_REQUEST);
		}
		
		if(encodeVal == null)
			return new ResponseEntity<>(encodeVal, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(encodeVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = RequestMappingConstants._GO_ENCODE + "{encodePath}", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest request,
			@PathVariable("encodePath") String encodePath) {
		
		byte[] decode = null;
		
		try {
			decode = Base64.getUrlDecoder().decode(encodePath);
		} catch (Exception e) {
			model.addAttribute("msg", "잘못된 URL입니다");
			return "redirect:"+ RequestMappingConstants._ERROR_LOAD + HttpStatus.BAD_REQUEST.value();
		}
		
		String decodeVal = new String(decode);
		System.out.println(decodeVal);
		
		return "redirect:" + decodeVal;
	}
	
}