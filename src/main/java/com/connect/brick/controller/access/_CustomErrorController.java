package com.connect.brick.controller.access;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.connect.brick.config.RequestMappingConstants;

@Controller
public class _CustomErrorController implements ErrorController {
	
	public String PATH = "/error";
	
	@RequestMapping(value = RequestMappingConstants._ERROR_PATH)
    public String error(Model model, 
    		HttpServletRequest request) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		
		Object ms_object = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		Exception ex = (Exception)ms_object;
		
		String message = "";
		
		if(ex!=null && ex.getCause()!=null && ex.getCause().getMessage()!=null)
				message = String.valueOf(ex.getCause().getMessage());
		
		//HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));

		model.addAttribute("msg", message);
		
		return "error/error";
	}
	
	@RequestMapping(value = RequestMappingConstants._ERROR_LOAD + "{statusCode}")
    public String errorPage(Model model, 
    		HttpServletRequest request,
    		@PathVariable("statusCode") Integer statusCode,
    		@ModelAttribute("msg") String msg) {
		
		model.addAttribute("statusCode", statusCode);
		model.addAttribute("msg", msg);
		
		return "error/error";
	}

	@RequestMapping(value = RequestMappingConstants._ERROR_PATH_CODE + "{statusCode}")
    public ResponseEntity errorStatusCode(Model model, 
    		HttpServletRequest request,
    		@PathVariable("statusCode") Integer statusCode,
    		@ModelAttribute("msg") String msg) {
		
		String referer = request.getHeader("Referer");
		
		HttpStatus hs = HttpStatus.valueOf(statusCode);
		
		return new ResponseEntity<>(msg, hs);
	}
	
	@Override
	public String getErrorPath() {
		 return PATH;
	}
	
}