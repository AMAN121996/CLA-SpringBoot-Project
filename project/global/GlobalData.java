package com.shopping.project.global;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.shopping.project.model.Product;
import com.shopping.project.model.User;


public class GlobalData {

	
	public static List<Product>cart;
	
	static {
		
		cart=new ArrayList<Product>();
	}
	
	
	static String userEmail;
	static Integer  userId;
	public static String  getcurrentUserEmail()
	{
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	userEmail = authentication.getName();
	return userEmail;
		
		
	}

	public static Integer getUserId() {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user=(User) authentication.getPrincipal();
		  userId=user.getId();
		return userId;
	}
	
	
	
	
	
	
}
