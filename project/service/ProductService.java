package com.shopping.project.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.project.model.Product;
import com.shopping.project.repository.ProductRepository;





@Service
public class ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	public void addProduct(Product product)
	{
		
		productRepository.save(product);
	}
	
	
	public  void removeProductById(int id)
	{
		productRepository.deleteById(id);
	}
	
	public Optional<Product>getProductById(int id)
	{
	 return productRepository.findById(id);
	}
	
	
	public List<Product>getAllProductsByCategoryId(int id)
	{
		return productRepository.findAllByCategoryId(id);
	}
	
	public List<Product>getAllProduct()
	{
		return (List<Product>)productRepository.findAll();
	
	}

	public void deleteproductbyId(int id) {
		
		productRepository.deleteById(id);
	}
	

	
}


