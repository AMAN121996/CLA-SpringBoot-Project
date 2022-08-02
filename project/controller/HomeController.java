package com.shopping.project.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.project.dto.ProductDTO;
import com.shopping.project.global.GlobalData;
import com.shopping.project.service.CategoryService;
import com.shopping.project.service.EmailSenderService;
import com.shopping.project.service.ProductService;


@Controller
public class HomeController {

	@Autowired
	private EmailSenderService senderService;
	
	@Autowired
	private ProductService productservice;
	
	@Autowired 
	private CategoryService categoryservice;
	
	
	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "index";
	} 
	
	
	
	
	@GetMapping("/about")
	public String about(Model model)
	{
		//model.addAttribute("cartCount",GlobalData.cart.size());
		return "about";
	} 
	
	@GetMapping("/laptop")
	public String laptop(Model model)
	{
		//model.addAttribute("cartCount",GlobalData.cart.size());
		return "laptop";
	} 
	
	
	@GetMapping("/computer")
	public String computer(Model model)
	{
		//model.addAttribute("cartCount",GlobalData.cart.size());
		return "computer";
	} 
	
	
	@GetMapping("/product")
	public String product(Model model)
	{
		model.addAttribute("products",productservice.getAllProduct());
		return "product";
	} 
	
	
	@GetMapping("/contact")
	public String contact(Model model)
	{
		
		return "contact";
	} 
	
	
	@GetMapping("/index")
	public String index(Model model)
	{
		
		return "index";
	} 
	
	
	
	@GetMapping("/shop")
	public String shop(Model model)
	{
		//model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("categories",categoryservice.getAllCategory());
		model.addAttribute("products",productservice.getAllProduct());
		
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		
		
		
		return "shop";
	} 
	
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model,@PathVariable int id)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("categories",categoryservice.getAllCategory());
		model.addAttribute("products",productservice.getAllProductsByCategoryId(id));
		
		return "shop";
	} 
	
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable int id)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("product",productservice.getProductById(id).get());
		model.addAttribute("products",productservice.getAllProduct());
		return "viewProduct";
	} 
	
	
	    @PostMapping("/contact")
		public String contact(@RequestParam("Name")String name,@RequestParam("Email")String sendmail,@RequestParam("Msg")String msg) throws IOException{	
		
	    	try {
				triggerMail(name,sendmail,msg);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
		return "index";
	}
	
	
	    public void triggerMail(String name,String sendmail,String msg) throws MessagingException {
	    	
	    	String adminEmail="amanahirwar74@gmail.com";
			senderService.sendSimpleEmail(adminEmail,"From:  "+sendmail,name+"said"+"\n"+msg);
		
		
			
			
	       }
	
	

	
}
