package com.connect.brick.repository.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.connect.brick.component.SearchComponent;
import com.connect.brick.model.data.ApartmentKkd;
import com.connect.brick.repository.data.custom.ApartmentKkdRepositoryCustom;

import kr.co.shineware.nlp.komoran.model.Token;

public class ApartmentKkdRepositoryImpl implements ApartmentKkdRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;

//	@Autowired
//    private SearchComponent searchComponent;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ApartmentKkd> findBySearch(List<Token> tokenList, List<String> morphMustList, String org) {

		SearchComponent searchComponent = new SearchComponent("TB_APARTMENT_V2_KKD", null);
		
		List<String> allStringList = new ArrayList<String>();
		
		for (Token token : tokenList) {
			allStringList.add(token.getMorph());
		}
		
		String query =  
				" SELECT * FROM ( "+ searchComponent.getSelectFromOrg(org) + 
				searchComponent.getSelectFromAll(allStringList) +
				searchComponent.getSelectFromAll(morphMustList) + 
				searchComponent.getSelectFromRegexp(morphMustList) + 
				" ) result JOIN TB_REGION_SD region WHERE result.addr_sd=region.addr_sd_search_name " +
				" GROUP BY result.addr_emd, result.addr_jb, result.name_apart LIMIT 400";
		
		
		return entityManager.createNativeQuery(query, ApartmentKkd.class)
		        .getResultList();
	}
	
}
