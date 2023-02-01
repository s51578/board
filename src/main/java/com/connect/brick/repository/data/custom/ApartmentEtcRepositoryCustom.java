package com.connect.brick.repository.data.custom;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect.brick.model.data.ApartmentEtc;
import com.connect.brick.model.data.ApartmentKkd;
import com.connect.brick.model.data.ApartmentSu;

import kr.co.shineware.nlp.komoran.model.Token;

@Transactional
public interface ApartmentEtcRepositoryCustom {
	
//	INSERT INTO tb_apartment_v1_etc
//	SELECT * from tb_apartment_v1 av WHERE av.addr_sd!="인천광역시" AND av.addr_sd!="경기도" and av.addr_sd!="서울특별시"
	
	public List<ApartmentEtc> findBySearch(List<Token> tokenList, List<String> morphMustList, String org, String regionName);
	
}
