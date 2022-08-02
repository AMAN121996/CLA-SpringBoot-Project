package com.shopping.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.project.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

	

	List<Product>findAllByCategoryId(int id);

	
	

}
