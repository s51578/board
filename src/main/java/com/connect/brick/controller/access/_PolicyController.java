package com.connect.brick.controller.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.connect.brick.config.RequestMappingConstants;

@Controller
public class _PolicyController {

	private static final String URL_PREFIX = "views/policy";
	
	@Autowired
	ResourceLoader resourceLoader;
	

	@RequestMapping(value = RequestMappingConstants._POLICY_PRIVACY, method = RequestMethod.GET)
	public String privacyPolicy(Model model) {
		
		return URL_PREFIX + "/privacy_policy";
	}
	
	@RequestMapping(value = RequestMappingConstants._POLICY_CONSENT, method = RequestMethod.GET)
	public String privacy(Model model) {
		
		return URL_PREFIX + "/privacy_policy_consent_landing";
	}
	
	@RequestMapping(value = RequestMappingConstants._POLICY_MARKETING, method = RequestMethod.GET)
	public String marketing(Model model) {
		
		return URL_PREFIX + "/marketing_consent";
	}
	
	@RequestMapping(value = RequestMappingConstants._POLICY_PROVIDING, method = RequestMethod.GET)
	public String providing(Model model) {
		
		return URL_PREFIX + "/provide_to_third_parties";
	}

	@RequestMapping(value = RequestMappingConstants._POLICY_PARTNER, method = RequestMethod.GET)
	public String partner(Model model) {
		
		return URL_PREFIX + "/partner_privacy_policy";
	}
	
	@RequestMapping(value = RequestMappingConstants._POLICY_TERMS_OF_USE, method = RequestMethod.GET)
	public String termsofuse(Model model) {
		
		model.addAttribute("title", "이용약관");
		return URL_PREFIX + "/service_policy";
	}
	
}