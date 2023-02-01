package com.connect.brick.controller.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.connect.brick.model.dto._ApartmentDTO;
import com.connect.brick.service.ApartService;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

@RequestMapping(value="/service/ajax")
@Controller
public class ResponseSearchController {

	@Autowired
	private ApartService apartService;
	
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity search(Model model, Authentication user,
			MultipartHttpServletRequest request, 
			@RequestParam(name = "region") String region,
			@RequestParam(name = "search") String search) {
		
		//List<_ApartmentDTO> result = apartService.getApartBySearch(region, search);

		if(search==null || search.equals("") || search.isEmpty())
			return new ResponseEntity<>("검색어가 없습니다",HttpStatus.BAD_REQUEST);
		
		long beforeTime = System.currentTimeMillis();
		//////////////////////////////////////////////////////////
		
		Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
		
		KomoranResult analyzeResultList = komoran.analyze(search);
		
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("############################################################################");
		System.out.println("코모란 분석 시간 : " + secDiffTime);
		System.out.println("############################################################################");
		

		List<Token> tokenList = analyzeResultList.getTokenList();
		List<String> tokenStringList = new ArrayList<String>();
		List<String> morphMustList = analyzeResultList.getMorphesByTags("NNP", "NNG", "SN", "SL");
		
		for (Token token : tokenList) {
				System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(),
						token.getPos());
				tokenStringList.add(token.getMorph());
		}
		
		List<_ApartmentDTO> sus = apartService.getResultBySearch(region, search, tokenList, morphMustList);
		
//		for (_ApartmentDTO ad : sus) {
//			System.out.println(ad.getNameApart());
//		}
		
		System.out.println(sus.size());
		
		Map<String, Object> result = new HashMap<>();
		result.put("apts", sus);
		result.put("words", tokenStringList);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}