package com.shopping.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.project.model.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, String> {
    List<Order> findAllByUserIdOrderByCreatedDateDesc(String userId);
}