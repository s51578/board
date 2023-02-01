package com.connect.brick.repository.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect.brick.model.data.ApartmentKkd;

import kr.co.shineware.nlp.komoran.model.Token;

@Transactional
public interface ApartmentKkdRepository extends JpaRepository<ApartmentKkd, Long>{

//	INSERT INTO tb_apartment_v1_Kkd
//	SELECT * from tb_apartment_v1 av WHERE av.addr_sd="경기도"
	
	@Query(value = "(SELECT * FROM tb_apartment_v1_kkd asu \r\n" + 
			"WHERE asu.name_apart \r\n" + 
			"LIKE :searchForName \r\n" + 
			"UNION\r\n" + 
			"SELECT * FROM tb_apartment_v1_kkd asu \r\n" + 
			"WHERE CONCAT(LEFT(asu.addr_sg, CHAR_LENGTH(asu.addr_sg)-1), \" \", LEFT(asu.addr_gu, CHAR_LENGTH(asu.addr_gu)-1), \" \", LEFT(asu.addr_emd, CHAR_LENGTH(asu.addr_emd)-1), \" \", asu.name_apart) \r\n" + 
			"LIKE :searchForName \r\n" + 
			"UNION\r\n" + 
			"SELECT * FROM tb_apartment_v1_kkd asu \r\n" + 
			"WHERE CONCAT(LEFT(asu.addr_sg, CHAR_LENGTH(asu.addr_sg)-1), \" \", LEFT(asu.addr_gu, CHAR_LENGTH(asu.addr_gu)-1), \" \", LEFT(asu.addr_emd, CHAR_LENGTH(asu.addr_emd)-1), \" \", asu.name_apart) \r\n" + 
			"LIKE :searchForNatural) LIMIT 400", nativeQuery = true)
	public List<ApartmentKkd> findApartmentBySearch(@Param(value = "searchForName") String searchForName, @Param(value = "searchForNatural") String searchForNatural);
	
	@Query(value = "SELECT * FROM TB_APARTMENT_V2_KKD asu JOIN TB_REGION_SD region "
			+ "WHERE asu.addr_sd=region.addr_sd_search_name AND asu.no=:no"
			, nativeQuery = true)
	public ApartmentKkd findByNo(@Param(value = "no") Long no);
	
	@Query(value = "SELECT * FROM TB_APARTMENT_V2_KKD asu JOIN TB_REGION_SD region "
			+ "WHERE asu.addr_sd=region.addr_sd_search_name AND asu.name_apart=:aptName AND asu.addr_emd=:aptEmd AND asu.addr_jb=:aptJb"
			, nativeQuery = true)
	public List<ApartmentKkd> findDtoByNameApartAndAddrEmdAndAddrJb(@Param(value = "aptName")String aptName, 
			@Param(value = "aptEmd")String aptEmd, 
			@Param(value = "aptJb")String aptJb);
	
	public List<ApartmentKkd> findBySearch(List<Token> tokenList, List<String> morphMustList, String org);
}
