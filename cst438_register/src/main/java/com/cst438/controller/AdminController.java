package com.cst438.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;
import com.cst438.domain.AdminRepository;
import com.cst438.domain.Admin;
import com.cst438.domain.AdminDTO;

@RestController
@CrossOrigin(origins={"http://localhost:3000","http://localhost:8080"})
public class AdminController {
	@Autowired
	AdminRepository adminRepository;
	
	/*
	 * get admin.
	 */
	@GetMapping("/admin")
	public Admin getAdmin(@AuthenticationPrincipal OAuth2User principal) {
		
		System.out.println("Line 31 AdminController getAdmin /admin called."+Math.random());
		//System.out.println("Line 32 AdminController getAdmin /admin called."+principal.getName());
		System.out.println("Line 32 AdminController getAdmin /admin called."+principal.getAttribute("name"));
		System.out.println("Line 32 AdminController getAdmin /admin called."+principal.getAttribute("email"));
		System.out.println("Line 33 AdminController getAdmin /admin called."+principal.getAttributes());
		//Optional<Student> optionalStudent = studentRepository.findById(id);
		//if (optionalStudent.isPresent()) {	Student student=optionalStudent.get();
		//	System.out.println("/student "+student.getName()+" "+student.getStudent_id());
		//	return student;
		//} else {
		//	System.out.println("/student "+id+" not found. ");
		//	throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student not found. " );
		//}
		Admin admin = adminRepository.findByEmail(principal.getAttribute("email"));
		Optional<Admin>optionalAdmin=Optional.ofNullable(admin);
		if(optionalAdmin.isPresent())System.out.println("Line 51 AdminController says admin is present (is admin)");
		else System.out.println("Line 52 AdminController says admin is not present (is not admin)");
		
		//Admin a=new Admin("a","b","c",4);
		//return a;
		if(optionalAdmin.isPresent())return admin;
		//return null;//hmmm not sure how to intercept so maybe instead:
		return new Admin("","NOT ADMIN!","",-1);
	}
	
}
