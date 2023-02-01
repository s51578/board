package com.connect.brick.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.brick.model.Order;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findAllByOrderByOrderDateDesc();
	
}
