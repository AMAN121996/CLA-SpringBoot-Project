package com.shopping.project.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.shopping.project.model.CartItem;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {



	
	//public List<CartItem>findCartItemsByProductId(Integer Id);
	
	//public CartItem findByUserAndProduct(String username,Integer Id);

	//public List<CartItem> findCartItemsByProductId(Integer id);

	
}