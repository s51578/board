package com.connect.brick.repository.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect.brick.model.Contact;
import com.connect.brick.model.data.ApartmentAll;
import com.connect.brick.model.data.ApartmentSu;

@Transactional
public interface ApartmentAllRepository extends JpaRepository<ApartmentAll, Long>{

	@Query(value = "SELECT * FROM TB_APARTMENT_V1 apt WHERE MATCH(apt.name_apart) AGAINST (:nameApart IN NATURAL LANGUAGE MODE)", nativeQuery = true)
	public List<ApartmentAll> findFullTextSearchByNameApart(@Param("nameApart") String nameApart);

	List<ApartmentAll> findByNameApartContaining(String nameApart);
	
}
