package com.connect.brick.repository.data.custom;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect.brick.model.data.ApartmentIch;
import com.connect.brick.model.data.ApartmentKkd;
import com.connect.brick.model.data.ApartmentSu;

import kr.co.shineware.nlp.komoran.model.Token;

@Transactional
public interface ApartmentIchRepositoryCustom {

//	INSERT INTO tb_apartment_v1_Ich
//	SELECT * from tb_apartment_v1 av WHERE av.addr_sd="인천광역시"

	public List<ApartmentIch> findBySearch(List<Token> tokenList, List<String> morphMustList, String org);
}
