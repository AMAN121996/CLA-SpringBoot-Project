package com.shopping.project.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.project.model.CartItem;
import com.shopping.project.model.Product;
import com.shopping.project.model.ShoppingCart;



import com.shopping.project.repository.ShoppingCartRepository;



@Service
public class ShoppingCartService {

	
	//@Autowired
	//private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	@Autowired
	private ProductService productService;

	
	public ShoppingCart addShoppingCartFirstTime(Integer id, String sessionToken, int quantity,String username) {
		
		
		//System.out.println(id+" "+sessionToken+" "+" "+quantity+" "+username);
		
		ShoppingCart shoppingCart = new ShoppingCart();
		
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(quantity);
		cartItem.setDate(new Date());
		cartItem.setProduct(productService.getProductById(id).get());
		shoppingCart.getItems().add(cartItem);
		shoppingCart.setSessionToken(sessionToken);
		shoppingCart.setDate(new Date());
		shoppingCart.setUsername(username);
		
		return shoppingCartRepository.saveAndFlush(shoppingCart);

	}

	public ShoppingCart addToExistingShoppingCart(Integer id, String sessionToken, int quantity,String username) {

		ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
		Product p = productService.getProductById(id).get();
		Boolean productDoesExistInTheCart = false;
		if (shoppingCart != null) {
		    Set<CartItem> items = shoppingCart.getItems();
			for (CartItem item : items) {
				if (item.getProduct().equals(p)) {
					productDoesExistInTheCart = true;
					item.setQuantity(item.getQuantity() + quantity);
					shoppingCart.setItems(items);
					shoppingCart.setUsername(username);
					return shoppingCartRepository.saveAndFlush(shoppingCart);  
				}
				
			}
		}
		if(!productDoesExistInTheCart && (shoppingCart != null))
		{
			CartItem cartItem1 = new CartItem();
			cartItem1.setDate(new Date());
			cartItem1.setQuantity(quantity);
			cartItem1.setProduct(p);
			shoppingCart.getItems().add(cartItem1);
			return shoppingCartRepository.saveAndFlush(shoppingCart);
		}
		
		return this.addShoppingCartFirstTime(id, sessionToken, quantity,username);

	}

	//public ShoppingCart getShoppingCartBySessionTokent(String username) {
		
		//return  shoppingCartRepository.findBySessionToken(username);
	//}
/*
	public CartItem updateShoppingCartItem(Integer id, int quantity) {
		CartItem cartItem = cartItemRepository.findById(id).get();
		cartItem.setQuantity(quantity);
		return cartItemRepository.saveAndFlush(cartItem);
		
	}

	public ShoppingCart removeCartIemFromShoppingCart(Integer id, String sessionToken) {
		ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
		Set<CartItem> items = shoppingCart.getItems();
		CartItem cartItem = null;
		for(CartItem item : items) {
			if(item.getId()==id) {
				cartItem = item;
			}
		}
		items.remove(cartItem);
		cartItemRepository.delete(cartItem);
	    shoppingCart.setItems(items);
	    return shoppingCartRepository.save(shoppingCart);
	}
*/
	public void clearShoppingCart(String sessionToken) {
		ShoppingCart sh = shoppingCartRepository.findBySessionToken(sessionToken);
		shoppingCartRepository.delete(sh);
		
	}
	
	public Optional<ShoppingCart>getSessionbyUsername(String username)
	{
		
	
	return shoppingCartRepository.findSessionByUsername(username);
	

	
	}

	public ShoppingCart getShoppingCartBySessionToken(String sessionToken) {
		// TODO Auto-generated method stub
		
		
		return shoppingCartRepository.findBySessionToken(sessionToken);
	}


	
	
	

	
	


	
		  
	
		
		 

	
	
	/*
	public Integer addProduct(Integer productId,Integer quantity,User user)
	{
		
		Integer addedQuantity=quantity;
		
		

		Product product =productRepository.findById(productId).get();
		CartItem cartItem=cartRepo.findByUserAndProduct(user, product);
		
		
		if(cartItem!=null)
		{
			addedQuantity=cartItem.getQuantity()+quantity;
			cartItem.setQuantity(addedQuantity);
		}
		else {
			
			cartItem=new CartItem();
			cartItem.setQuantity(quantity);
			cartItem.setUser(user);
			cartItem.setProduct(product);
		}
	 cartRepo.save(cartItem);
		return addedQuantity;
	}

	public Integer addProduct(int id, int quantity, String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	/*

	public ShoppingCart addShoppingCartFirstTime(int id, String sessionToken) {

		ShoppingCart shoppingCart = new ShoppingCart();
		CartItem cartItem = new CartItem();

		cartItem.setQuantity(1);
		cartItem.setDate(new Date());
		cartItem.setProduct(productService.getProductById(id).get());

		shoppingCart.getItems().add(cartItem);
		shoppingCart.setSessionToken(sessionToken);
		shoppingCart.setDate(new Date());
		shoppingCart.setUser_id(GlobalData.getcurrentUserEmail());
		return shoppingCartRepository.save(shoppingCart);
	}

	 public ShoppingCart addExistingShoppingCart(int id, String sessionToken) {
	 
	  
	  ShoppingCart shoppingCart=shoppingCartRepository.findBySessionToken(sessionToken); 
	  
	  Product p = productService.getProductById(id).get(); for (CartItem item :shoppingCart.getItems()) {
	  
	  if(p.getId().equals(item.getProduct().getId())) 
	  {
	  item.setQuantity(item.getQuantity()+1); return
	  shoppingCartRepository.save(shoppingCart);
	  }
	  
	  }
	  
	  CartItem cartItem=new CartItem(); 
	  cartItem.setDate(new Date());
	  cartItem.setQuantity(1); 
	  cartItem.setProduct(p);
	  shoppingCart.getItems().add(cartItem); 
	  return shoppingCartRepository.save(shoppingCart);
	  
	  }

	

	
	

	*/
	
	 

}
