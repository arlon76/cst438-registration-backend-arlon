package com.cst438.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.Enrollment;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/student")
	@Transactional
	public Student createNewStudent( /* @RequestParam("name") String name*/) {

		String name = "arlon2"; // student's email
		String student_email = "test@csumb.edu"; // student's email
		int student_status_code = 0;
		
		Student s=new Student();
		s.setName(name);
		s.setEmail(student_email);
		s.setStatusCode(student_status_code);
		
		//if (student!= null && course!=null && student.getStatusCode()==0) {

		Student student=studentRepository.save(s);
		
		return student;
		//ok this is super wierd I've changed stuff and for some reason can't push.
		//this has had super progress: user arlon1 goes into the db here.
		//in other words this does change the db but the info is hardcoded here,
		//request parameters don't work yet. You can see they're there but...
		//I'm doing something wrong with the parameters being sent in
		//but this is a lot farther than I had it yesterday because the db is changed!
		// I see what I was doing there's a passphrase AND a passWORD for ssh...
		// No, I can't get it to push...
	}
	
}
