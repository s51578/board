package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect.brick.model.DpMaterial;

@Transactional
public interface DpMaterialRepository extends JpaRepository<DpMaterial, Long>{
	
	// 생성일, 업데이트일 정렬
	List<DpMaterial> findAllByOrderByRegDateDesc();
	List<DpMaterial> findAllByMaterialMtSpecMainColorNoAndMaterialMtSpecSurfaceOrderByRegDateDesc(Long colorNo, String surface);
	List<DpMaterial> findTop4ByOrderByRegDateDesc();
	
	//인기순
	List<DpMaterial> findByOrderByDpPopularPopularRankAsc();
	List<DpMaterial> findAllByMaterialMtSpecMainColorNoAndMaterialMtSpecSurfaceOrderByDpPopularPopularRankAsc(Long colorNo, String surface);
	
	List<DpMaterial> findByOrderByDpPopularPopularRankDesc();
	
	//이름 리스트 정렬
	List<DpMaterial> findByOrderByMaterialCbName(Sort sort);
	//List<DpMaterial> findByOrderByMaterialCbName();
	List<DpMaterial> findByOrderByMaterialCbNameDesc();
	
	// 페이징기능을 추가한 이름 리스트 정렬
	//Page<Contents> findByOrderByMaterialCbName(Pageable pageable);
	//Page<Contents> findByOrderByMaterialCbNameDesc(Pageable pageable);
	
	// 가격 리스트 정렬
	List<DpMaterial> findByOrderByMaterialMtSalesSalesPriceAscMaterialMtSalesConsumerPriceAsc();
	
	List<DpMaterial> findByOrderByMaterialMtSalesSalesPriceDescMaterialMtSalesConsumerPriceDesc();
	
//	@Query(value = "SELECT c FROM Contents c WHERE c.salesPrice > 0 ORDER BY c.salesPrice")
//	List<Contents> findSalesPriceSort();
	List<DpMaterial> findByMaterialMtSalesSalesPriceGreaterThanOrderByMaterialMtSalesSalesPriceDesc(int min);
	
//	@Query(value = "SELECT * FROM TB_CONTENTS c ORDER BY c.salesPrice=0 ASC, c.salesPrice ASC LIMIT 4", nativeQuery=true)
//	List<Contents> findSalesPriceSortLimit4();
	List<DpMaterial> findTop4ByOrderByMaterialMtSalesSalesPrice();
	
	List<DpMaterial> findAllByMaterialMtContentsMdpickLabelViewTrueOrderByRegDateDesc();
	
	@Query(value = "SELECT * FROM DP_MATERIAL dm, TB_MATERIAL m, _MATERIAL_SPEC ms "
			+ "WHERE dm.material_no=m.material_no AND m.material_no=ms.material_no "
			+ "AND (:surface IS NULL OR ms.surface = :surface) "
			+ "AND (COALESCE(:colorNos) IS NULL OR ms.main_color_no IN (:colorNos))", nativeQuery=true)
	List<DpMaterial> findBySurfaceAndColorsAndSearch(@Param("surface")String surface, @Param("colorNos")List<Long> colorNo);
	
	@Query(value = "SELECT dm FROM DpMaterial dm JOIN dm.material m JOIN m.mtSpec ms JOIN ms.mainColor mc JOIN m.mtSales mts LEFT JOIN dm.dpPopular dp "
			+ "WHERE (:surface IS NULL OR ms.surface = :surface) "
			+ "AND (COALESCE(:colorNos) IS NULL OR mc.no IN (:colorNos))")
	List<DpMaterial> findJpqlBySurfaceAndColorsAndSearch(@Param("surface")String surface, @Param("colorNos")List<Long> colorNo, Sort sort);
	
	@Query(value = "SELECT dm FROM DpMaterial dm JOIN dm.material m JOIN m.mtSpec ms JOIN ms.mainColor mc JOIN m.mtSales mts LEFT JOIN dm.dpPopular dp "
			+ "WHERE 1=1 AND (:surface IS NULL OR ms.surface = :surface) ")
	List<DpMaterial> findJpqlBySurface(@Param("surface")String surface, Sort sort);
	
	
	List<DpMaterial> findAll(Sort sort);
}