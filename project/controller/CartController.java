package com.shopping.project.controller;



import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.shopping.project.global.GlobalData;
import com.shopping.project.model.CartItem;
import com.shopping.project.model.Product;
import com.shopping.project.model.ShoppingCart;

import com.shopping.project.service.ProductService;
import com.shopping.project.service.ShoppingCartService;


@Controller
public class CartController {


	@Autowired
	private ProductService productService;
	
	//@Autowired
	//private ShoppingCartService cartService;
	
	/*@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id)
	{
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	
	*/
	
	///////////////////
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping("/addToCart/{id}")
	public String addToCart(Model model,@PathVariable Integer id) {

		
		 //findUserEmail
		String username=GlobalData.getcurrentUserEmail();
         System.out.println(username);
         //sessionToken
         String sessionToken="";  
         Optional<ShoppingCart>shoppingcart=(shoppingCartService.getSessionbyUsername(username));
		
         
         
         if(shoppingcart.isPresent())
         {
        	  System.out.println("Value is present");
        	  sessionToken=shoppingcart.get().getSessionToken();
        	  System.out.println("sessiontoken1="+sessionToken+username);
        	  shoppingCartService.addToExistingShoppingCart(id, sessionToken, 1, username);
        	  
         }
         
         else {
        	 
        	 sessionToken = UUID.randomUUID().toString();
		     System.out.println("sessiontoken2="+sessionToken+username);  
 			shoppingCartService.addShoppingCartFirstTime(id, sessionToken, 1,username);
        	 
        	 
    
        	 System.out.println("Value is not present");
         }
         
		  
		return "redirect:/cart";
	}
	
	//show cart items 
	
	@GetMapping("/cart")
	public String showShoopingCartView(Model model) {
		

		 //findUserEmail
		String username=GlobalData.getcurrentUserEmail();
        System.out.println(username);
        //sessionToken
        String sessionToken="";  
        Optional<ShoppingCart>shoppingcart=(shoppingCartService.getSessionbyUsername(username));
		
        if(shoppingcart.isPresent())
        {
       	  System.out.println("Value is present");
       	  sessionToken=shoppingcart.get().getSessionToken();
       	  System.out.println("sessiontoken1="+sessionToken+username);
       	  
       	 ShoppingCart shoppingCart=shoppingCartService.getShoppingCartBySessionToken(sessionToken);
       	 
       	 //cartitems count
          model.addAttribute("cartCount",shoppingCart.getItems().size());
       	 System.out.println("Cart size="+shoppingCart.getItems().size());
       	 
       	 
       //	Set<CartItem> items=shoppingCart.getItems();
       	for(CartItem item :shoppingCart.getItems()) {
			System.out.println(item.getProduct().getName()+" "+item.getProduct().getPrice()+" "+item.getQuantity()+item.getProduct().getImageName());
		}
       	 
       	 
       	 
       	  model.addAttribute("shoppingCart",shoppingCart);
       	  
        }
        
        else
        {
       	       model.addAttribute("shoppingCart",new ShoppingCart());
       	        System.out.println("cart is empty");
        }
		
		return "cart";
	}
	
	
	
	@GetMapping("/checkout")
	public String checkout(Model model)
	{
		  //model.addAttribute("cartCount",GlobalData.cart.size());
		 //model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		 // model.addAttribute("cart",GlobalData.cart);
		  
		  
		  model.addAttribute("msg","Your Cart Is Empty");
			// if(GlobalData.cart.size()<1)
			//  {
			//	 return "cart";
			 // }
		
		  String username=GlobalData.getcurrentUserEmail();
	        System.out.println(username);
	        //sessionToken
	        String sessionToken="";  
	        Optional<ShoppingCart>shoppingcart=(shoppingCartService.getSessionbyUsername(username));
			
	        if(shoppingcart.isPresent())
	        {
	       	  System.out.println("Value is present");
	       	  sessionToken=shoppingcart.get().getSessionToken();
	       	  System.out.println("sessiontoken1="+sessionToken+username);
	       	  
	       	 ShoppingCart shoppingCart=shoppingCartService.getShoppingCartBySessionToken(sessionToken);
	       	 
	       	 //cartitems count
	          model.addAttribute("cartCount",shoppingCart.getItems().size());
	       	 System.out.println("Cart size="+shoppingCart.getItems().size());
	       	 
	       	 
	       //	Set<CartItem> items=shoppingCart.getItems();
	       	for(CartItem item :shoppingCart.getItems()) {
				System.out.println(item.getProduct().getName()+" "+item.getProduct().getPrice()+" "+item.getQuantity()+item.getProduct().getImageName());
			}
	       	 
	      
	       	 model.addAttribute("TotalPrice",shoppingCart.getTotalPrice());
	       	  model.addAttribute("shoppingCart",shoppingCart);
	       	  
	        }
	        
	        else
	        {
	       	       model.addAttribute("shoppingCart",new ShoppingCart());
	       	        System.out.println("cart is empty");
	       	         model.addAttribute("msg","Your Cart Is Empty");
	       	         return "cart";
	        }
			
	
		  
		  return "checkout";
		
		
		
		
	}
	
	
	/*
	@PostMapping("/updateShoppingCart")
	public String updateCartItem(@RequestParam("item_id") Integer id,
			@RequestParam("quantity") int quantity) {
		
		shoppingCartService.updateShoppingCartItem(id,quantity);
		return "redirect:shoppingCart";
	}
	@GetMapping("/removeCartItem/{id}")
	public String removeItem(@PathVariable("id") Integer id, HttpServletRequest request) {
		String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
		System.out.println("got here ");
		shoppingCartService.removeCartIemFromShoppingCart(id,sessionToken);
		return "redirect:/shoppingCart";
	}
	
	@GetMapping("/clearShoppingCart")
	public String clearShoopiString(HttpServletRequest request) {
		String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
		request.getSession(false).removeAttribute("sessiontToken");
		shoppingCartService.clearShoppingCart(sessionToken);
		return "redirect:/shoppingCart";
	}
	


	//----------------------------------------------------------------------------------
	*/
	//@GetMapping("/cart")
	public String cartGet(Model model)
	{
		String username=GlobalData.getcurrentUserEmail();
	    
		
    
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
	    model.addAttribute("cart",GlobalData.cart);
	    
	    return "cart";
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index)
	{
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	
	
	
	@GetMapping("/order")
	public String order(Model model)
	{
		 
		GlobalData.cart.clear();
		return "order";
	} 
	
}
