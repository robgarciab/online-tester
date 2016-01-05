package com.katari.examples.testmaker.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

	public static String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	} 
	
	public static User getUserDetails() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
