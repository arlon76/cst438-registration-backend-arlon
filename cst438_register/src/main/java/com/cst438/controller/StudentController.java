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
	public Student createNewStudent( @RequestBody Student rs) {

		/*	String name = rs.getName(); // student's name
		String student_email = rs.getEmail(); // student's email
		int student_status_code = rs.getStatusCode();
		String student_status = rs.getStatus();	*/
		
		/*	Student s=new Student();
		s.setName(name);
		s.setEmail(student_email);
		s.setStatusCode(student_status_code);
		s.setStatus(student_status);	*/
		
		//if (student!= null && course!=null && student.getStatusCode()==0) {

		//Student student=studentRepository.save(s);
		//Student student=studentRepository.save(rs);
		
		//return student;
		return studentRepository.save(rs);
		//more leaps in progress: rs has the parameters, name works and db changed - this method became one line - not sure if that's right...
	}
	
}
