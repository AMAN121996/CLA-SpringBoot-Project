package com.shopping.project.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.project.global.GlobalData;
import com.shopping.project.model.Order;
import com.shopping.project.model.ShoppingCart;
import com.shopping.project.repository.ShoppingCartRepository;
import com.shopping.project.service.OrderService;
import com.shopping.project.service.ProductService;
import com.shopping.project.service.ShoppingCartService;

@Controller
public class OrderController {

	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	
	@Autowired 
	private OrderService orderService;
	/*@PostMapping("/addOrder")
    public String placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws ProductNotExistException, AuthenticationFailException {
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        orderService.placeOrder(userId, sessionId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }
	
	@GetMapping("/getAllOrder")
    public List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        List<Order> orderDtoList = orderService.listOrders(userId);
        return new ResponseEntity<List<Order>>(orderDtoList,HttpStatus.OK);
    }
	*/
	
	
	@GetMapping("/order1")
	public String placeOrder(Model model)
            {
		 System.out.println("order placed");
		
		
		String username=GlobalData.getcurrentUserEmail();
		//Integer userId=GlobalData.getUserId();
	     System.out.println(username);
	     //sessionToken
	     String sessionToken="";  
	     Optional<ShoppingCart>shoppingcart=(shoppingCartService.getSessionbyUsername(username));
		
	     if(shoppingcart.isPresent())
	     {
	    	  System.out.println("Value is present");
	    	  sessionToken=shoppingcart.get().getSessionToken();
	    	  System.out.println("sessiontoken1="+sessionToken+username);
	          orderService.placeOrder(username, sessionToken);
	     }
		
	     return "redirect:/order";
    }
	
	/*
	@GetMapping("/getAllOrder")
    public String getAllOrders(Model model){
  
       
		Integer userId=GlobalData.getUserId();
		
		
        List<Order>orderDtoList = orderService.listOrders(userId);
        
        model.addAttribute("orderlist",orderDtoList);
        
        return "order";
    }
	
	*/
	
}





