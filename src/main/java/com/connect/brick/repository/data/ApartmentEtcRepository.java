package com.connect.brick.repository.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect.brick.model.data.ApartmentEtc;
import com.connect.brick.repository.data.custom.ApartmentEtcRepositoryCustom;

import kr.co.shineware.nlp.komoran.model.Token;

@Transactional
public interface ApartmentEtcRepository extends JpaRepository<ApartmentEtc, Long>, ApartmentEtcRepositoryCustom {
	
//	INSERT INTO tb_apartment_v1_etc
//	SELECT * from tb_apartment_v1 av WHERE av.addr_sd!="인천광역시" AND av.addr_sd!="경기도" and av.addr_sd!="서울특별시"
	
	
	@Query(value = "(SELECT * FROM tb_apartment_v1_etc asu \r\n" + 
			"WHERE asu.name_apart \r\n" + 
			"LIKE :searchForName \r\n" +
			"AND asu.addr_sd=:regionName \r\n" +
			"UNION\r\n" + 
			"SELECT * FROM tb_apartment_v1_etc asu \r\n" + 
			"WHERE CONCAT(LEFT(asu.addr_sg, CHAR_LENGTH(asu.addr_sg)-1), \" \", LEFT(asu.addr_gu, CHAR_LENGTH(asu.addr_gu)-1), \" \", LEFT(asu.addr_emd, CHAR_LENGTH(asu.addr_emd)-1), \" \", asu.name_apart) \r\n" + 
			"LIKE :searchForName \r\n" + 
			"AND asu.addr_sd=:regionName \r\n" +
			"UNION\r\n" + 
			"SELECT * FROM tb_apartment_v1_etc asu \r\n" + 
			"WHERE CONCAT(LEFT(asu.addr_sg, CHAR_LENGTH(asu.addr_sg)-1), \" \", LEFT(asu.addr_gu, CHAR_LENGTH(asu.addr_gu)-1), \" \", LEFT(asu.addr_emd, CHAR_LENGTH(asu.addr_emd)-1), \" \", asu.name_apart) \r\n" + 
			"LIKE :searchForNatural \r\n" +
			"AND asu.addr_sd=:regionName) LIMIT 400", nativeQuery = true)
	public List<ApartmentEtc> findApartmentBySearch(
			@Param(value = "regionName") String regionName, 
			@Param(value = "searchForName") String searchForName, 
			@Param(value = "searchForNatural") String searchForNatural);
	
	@Query(value = "SELECT * FROM TB_APARTMENT_V2_ETC asu JOIN TB_REGION_SD region "
			+ "WHERE asu.addr_sd=region.addr_sd_search_name AND asu.no=:no"
			, nativeQuery = true)
	public ApartmentEtc findByNo(@Param(value = "no") Long no);
	
	@Query(value = "SELECT * FROM TB_APARTMENT_V2_ETC asu JOIN TB_REGION_SD region "
			+ "WHERE asu.addr_sd=region.addr_sd_search_name AND asu.name_apart=:aptName AND asu.addr_emd=:aptEmd AND asu.addr_jb=:aptJb"
			, nativeQuery = true)
	public List<ApartmentEtc> findDtoByNameApartAndAddrEmdAndAddrJb(@Param(value = "aptName")String aptName, 
			@Param(value = "aptEmd")String aptEmd, 
			@Param(value = "aptJb")String aptJb);
	
	public List<ApartmentEtc> findBySearch(List<Token> tokenList, List<String> morphMustList, String org, String regionName);
	
}
