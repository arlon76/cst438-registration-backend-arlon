package com.cst438.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.CourseDTOG;
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
	public Student createNewStudent( @RequestBody Student rs) throws ResponseStatusException{
					
			//first check to make sure one's not already there and that the parameters aren't empty:
		Student potentiallyPreExistingStudent=studentRepository.findByEmail( rs.getEmail() );
		if (potentiallyPreExistingStudent!=null) {	//there's already that email in the db
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student email already exists "+rs.getEmail());
		}else if(rs.getName().isEmpty()){	//also check for empty parameters
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "You have to put a name in");
		}else if(rs.getEmail().isEmpty()){
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "You have to put an email in");
		}else if(rs.getStatus().isEmpty()){
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "You have to put a status in");
		} else {	// if everything checks out, save to db:
			return studentRepository.save(rs);//new student. it depends on the JSON 'student' sent in, parameter rs
											//it also depends on autowired studentRepository, and the annotations 
												//PostMapping & Transactional. It checks the email isn't already
													//present in the db and that the parameters aren't empty.
		}

	}
	
	@PatchMapping("/student/{student_id}/{status_code}")
	@Transactional
	public void updateStudentStatus( @PathVariable int student_id, @PathVariable int status_code ) {
		
		//System.out.println("Line 59 StudentController.java says student_id:"+student_id+", status_code:"+status_code);//works!
		
		
	}

	
}
