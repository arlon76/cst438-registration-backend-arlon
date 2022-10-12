package com.cst438.controller;
 
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;
 
@Controller
//@CrossOrigin(origins={"http://localhost:3000","http://localhost:3000/","http://localhost:8080"})
public class LoginController {
	/*
	 * used by React Login front end component to test if user is 
	 * logged in.  
	 *   HTTP response 401 indicates user is not logged in
	 *   Otherwise, if user is logged in, a redirect response takes user to the 
 	*	Semester front end page.
	 */
	@Value("${frontend.post.login.url}")
	String redirect_url;
	@GetMapping("/user")
	public String user (@AuthenticationPrincipal OAuth2User principal){
		// used by front end to display user name.
		System.out.println("Line 25 LoginController.java says: redirect string is: "+redirect_url);// That prints correct, http://localhost:3000
		//return "redirect:http://localhost:3000/semester";
		return "redirect:"+redirect_url;
		//return ""+redirect_url;
		//return "{redirect:"+redirect_url+"}";
	}
}
