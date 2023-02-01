package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect.brick.model.access.Role;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query(value="SELECT * FROM ROLE where role=:role", nativeQuery = true)
    Role findRoleByRoleName(@Param("role") String role);
	
	@Query(value="SELECT * FROM ROLE where no<:level", nativeQuery = true)
    List<Role> findBelowRoleByLevel(@Param("level") long level);
	
}
