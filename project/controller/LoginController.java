package com.shopping.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopping.project.global.GlobalData;
import com.shopping.project.model.Role;
import com.shopping.project.model.User;
import com.shopping.project.repository.RoleRepository;
import com.shopping.project.repository.UserRepository;
import com.shopping.project.service.EmailSenderService;


@Controller
public class LoginController {

	

	@Autowired
	private EmailSenderService senderService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login(){
		
		GlobalData.cart.clear();
		return "login";
		
	}
	@GetMapping("/register")
	public String register(){
		return "register";
		
	}
	
	
	
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user,HttpServletRequest request,Model model)throws ServletException
	{
		
		//System.out.println(userRepository.findByEmail(user.getEmail()));
		
		 String username=user.getEmail();
		
		if(userRepository.findUserByEmail(username).isPresent())
		{
			System.out.println("User present");
			model.addAttribute("userfound","User Already Registered : You can Login here");
			return "login";
			
			
		}
		else {
			System.out.println("User not present");
			String password=user.getPassword();
			user.setPassword(bCryptPasswordEncoder.encode(password));
			List<Role>roles=new ArrayList<>();
			roles.add(roleRepository.findById(2).get());
			user.setRoles(roles);
			userRepository.save(user);
		
			
			request.login(user.getEmail(), password);
			
			try {
				triggerMail(user.getEmail(),user.getFirstName());
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		
		return "redirect:/";
	}
	
	
	
	
	public void triggerMail(String email,String name) throws MessagingException {
		senderService.sendSimpleEmail(email,
				"Hello"+name,
				"Your Registration Is Successful with ShopperWeb");
	
       }
	
	
	
}
