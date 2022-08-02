package com.shopping.project.controller;

import javax.servlet.ServletException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.project.model.User;
import com.shopping.project.repository.UserRepository;



@Controller
public class ForgotPasswordController {

	String forgotEmail="";
	 String otpSting="";

	
	@Autowired
	UserRepository userRepository;
	

	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/forgot")
	public String forgot(){
		return "forgot";
		
	}
	
	
	@GetMapping("/update")
	public String updatePassword()
	{
		return "update";
	}
	
	//otp generator method
	 public static String generateOTP() 
	    { 
	        int randomPin   =(int) (Math.random()*9000)+1000;
	        String otp  = String.valueOf(randomPin);
	        return otp; //returning value of otp
	    }
	
	 
	 
	@PostMapping("/sendOTP")
	public String sendOTP(@RequestParam("email")String email,Model model)throws ServletException
	{
            
		
		if(userRepository.findUserByEmail(email).isPresent())
		{
			//user email assign
			forgotEmail=email;
			//otp generaor
		     otpSting  =generateOTP();//function calling
		  System.out.println("OTP : "+otpSting);
			
			//System.out.println(email);
			
			model.addAttribute("otp","Enter OTP");	
		  return "forgot";
			
		}
		else
		{
			model.addAttribute("invaliduser","You are not Registered");
			return "forgot";
			
		}
		
		
		
	}
	
	
	
	@PostMapping("/checkOTP")
	public String checkOTP(@RequestParam("otp")String otp,Model model)throws ServletException
	{
          
	
		if(otp.equals(otpSting))
		{
			return "update";
		}
		else {
			
			model.addAttribute("invalidOTP","Please enter correct OTP");	
			return "forgot";
		}
	
	
	}
	
	
	@PostMapping("/update")
	public String update(@RequestParam("password")String password ,@RequestParam("repassword")String repassword,Model model)throws ServletException
	{
		
		System.out.print(forgotEmail);
		if(password.equals(repassword))
		{
			
			System.out.println("password match");
	
			User user= userRepository.findUserByEmail(forgotEmail).get();
				user.setPassword(bCryptPasswordEncoder.encode(password));
				userRepository.save(user);
				
			model.addAttribute("passwordchange","Password Change Succesfully");
			
		}
		else
		{
			System.out.println("password match not");
			model.addAttribute("matchpassword","password does not match");
		return "update";
			
		}
		
		
		
		return "login";
	}


	
	
	
}
