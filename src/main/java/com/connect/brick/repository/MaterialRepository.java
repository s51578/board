package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.connect.brick.model.Material;

@Transactional
public interface MaterialRepository extends JpaRepository<Material, Long>{

	List<Material> findAllByOrderByRegDateDesc();
	Material findMaterialByNo(Long no);
	
//	@Query(value = "SELECT mt FROM Material mt WHERE mt.cbCode LIKE %:keyword% OR mt.cbName LIKE %:keyword% ORDER BY mt.regDate DESC")
//	List<Material> findSearchMaterial(String keyword);
	
	@Query(value = "SELECT mt FROM Material mt WHERE mt.cbCode LIKE %:keyword% OR mt.cbName LIKE %:keyword% ORDER BY mt.regDate DESC")
	Page<Material> findSearchPageMaterial(String keyword, Pageable pageable);
	
	@Query(value = "SELECT m FROM Material m "
			+ "LEFT OUTER JOIN DpMaterial dm ON m.no=dm.material "
			+ "LEFT OUTER JOIN DpPopular p ON p.dpMaterial = dm.dpNo "
			+ "ORDER BY p.popularRank NULLS LAST")
	List<Material> findMaterialSortByPopularRank();
	
	List<Material> findMaterialByOrderByDpMaterialDpPopularPopularRankAsc();
	List<Material> findMaterialByOrderByDpMaterialRegDate();
}
