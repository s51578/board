package com.connect.brick.repository.data.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.connect.brick.component.SearchComponent;
import com.connect.brick.model.data.ApartmentSu;
import com.connect.brick.repository.data.ApartmentSuRepository;
import com.connect.brick.repository.data.custom.ApartmentSuRepositoryCustom;

import kr.co.shineware.nlp.komoran.model.Token;

@Transactional
public class ApartmentSuRepositoryImpl implements ApartmentSuRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<ApartmentSu> findBySearch(List<Token> tokenList, List<String> morphMustList, String org) {

		SearchComponent searchComponent = new SearchComponent("TB_APARTMENT_V2_SU", null);
		
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
		
		
		return entityManager.createNativeQuery(query, ApartmentSu.class)
		        .getResultList();
	}

}
