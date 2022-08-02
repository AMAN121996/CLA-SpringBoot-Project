package com.shopping.project.service;





import org.springframework.security.core.userdetails.UserDetailsService;


import com.shopping.project.dto.UserRegistrationDto;

import com.shopping.project.model.User;




public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

	//User findByEmail(String email);
	
	
	
}