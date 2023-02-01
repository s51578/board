package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect.brick.model.Image;

@Transactional
public interface ImageRepository extends JpaRepository<Image, Long>{
	
	@Query(value="SELECT i.* FROM IMAGE i inner join IMAGE_TAG it ON i.no = it.image_no inner join TAG t ON it.tag_no = t.no where t.name=:tagName", nativeQuery = true) 
	List<Image> findImageByTagName(@Param("tagName") String tagName);
	
	@Query(value="SELECT i.* FROM IMAGE i "
			+ "inner join IMAGE_TAG it ON i.no = it.image_no inner join TAG t ON it.tag_no = t.no "
			+ "where t.name IN :tagNames", nativeQuery = true) 
	List<Image> findImageByTagNames(@Param("tagNames") List<String> tagNames);
	
	@Query(value="SELECT i.* FROM IMAGE i "
			+ "inner join COLLECTION c ON i.collection_id = c.id "
			+ "inner join COLLECTION_TAG ct ON c.id = ct.collection_id "
			+ "inner join TAG t ON ct.tag_no = t.no "
			+ "where t.name IN :tagNames", nativeQuery = true) 
	List<Image> findImageByCollectionTagNames(@Param("tagNames") List<String> tagNames);
	
	Image findByUuid(String uuid);
	
}
