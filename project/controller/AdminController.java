package com.shopping.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
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
import com.shopping.project.model.Category;
import com.shopping.project.model.Product;
import com.shopping.project.service.CategoryService;
import com.shopping.project.service.ProductService;


@Controller
public class AdminController {

	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	@Autowired
	private ProductService productservice;
	
	@Autowired 
	private CategoryService categoryservice;
	
	@GetMapping("/admin")
	public String adminHome()
	{
		return "adminHome";
	}
	
	//categories-Section
	
	@GetMapping("/admin/categories")
	public String getCat(Model model)
	{
		model.addAttribute("categories",categoryservice.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model)
	{
		model.addAttribute("category",new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute Category category)
	{
		categoryservice.addCategory(category);
		return "redirect:/admin/categories ";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id)
	{
		categoryservice.deleteCatbyId(id);
		return "redirect:/admin/categories ";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id,Model model)
	{
		Optional<Category>category=categoryservice.getCategoryById(id);
		if(category.isPresent())
		{
			model.addAttribute("category",category.get());
			return "categoriesAdd";
		}else {
			return "404";
		}
		
	}
	
	//product--section
	@GetMapping("/admin/products")
	public String products(Model model)
	{
		model.addAttribute("products",productservice.getAllProduct());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model)
	{
		model.addAttribute("productDTO",new ProductDTO());
		model.addAttribute("categories",categoryservice.getAllCategory());
		return "productsAdd";
	}
	
	
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute ProductDTO productDTO,@RequestParam("productImage")MultipartFile file,@RequestParam("imgName")String imgName) throws IOException{
	
		Product product=new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryservice.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setDescription(product.getDescription());
		String imageUUID;
		
		if(!file.isEmpty())
		{
			imageUUID=file.getOriginalFilename();
			
			Path fileNameAndPath=Paths.get(uploadDir,imageUUID);
			Files.write(fileNameAndPath,file.getBytes());
		}
		else {
			imageUUID=imgName;
		}
		
		product.setImageName(imageUUID);
		productservice.addProduct(product);
		
		
	return "redirect:/admin/products";
	}
	


	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable int id)
	{
         productservice.deleteproductbyId(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable int id,Model model)
	{
		Product product=productservice.getProductById(id).get();
		ProductDTO productDTO=new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		
		model.addAttribute("categories",categoryservice.getAllCategory());
		model.addAttribute("productDTO",productDTO);
		
			return "productsAdd";
		
		
	}
	
	
}
