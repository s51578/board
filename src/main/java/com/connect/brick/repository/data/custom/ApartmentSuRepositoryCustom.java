package com.connect.brick.repository.data.custom;

import java.util.List;

import javax.transaction.Transactional;

import com.connect.brick.model.data.ApartmentSu;

import kr.co.shineware.nlp.komoran.model.Token;

@Transactional
public interface ApartmentSuRepositoryCustom {

	public List<ApartmentSu> findBySearch(List<Token> tokenList, List<String> morphMustList, String org);
	
}
