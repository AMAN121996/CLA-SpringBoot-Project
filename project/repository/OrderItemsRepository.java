package com.shopping.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.project.model.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {
	
}