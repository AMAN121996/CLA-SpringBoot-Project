package com.shopping.project.repository;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.project.model.CartItem;
import com.shopping.project.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer>{

	
	     ShoppingCart findBySessionToken(String sessionToken);

         Optional<ShoppingCart> findSessionByUsername(String username);

		//Set<CartItem> findItemsByUsername(String username);


		

		
	

	



	
}
