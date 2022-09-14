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
import com.cst438.domain.StudentDTO;
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

	//@PatchMapping("/student/{student_id}")
	//@PutMapping("/student")
	@PatchMapping("/student")
	@Transactional
	public void updateStudentStatus(
		/*		@RequestBody Student rs*/
			@RequestBody StudentDTO rs
			/*,  @PathVariable int student_id
			, @RequestParam("status_code") int status_code */
	) {
		//Optional<Student> student = studentRepository.findById(student_id);
		//Optional<Student> student = studentRepository.findById(rs.getStudent_id());
		Optional<Student> optionalStudent = studentRepository.findById(rs.student_id);
		optionalStudent.ifPresentOrElse(student->{
				
			System.out.println("Line 71 StudentController.java says student_id:"
					/*				+student_id	*/
					/*			+", rs.student_id:"+rs.getStudent_id()*/
				+", rs.student_id:"+rs.student_id
					/*		+", status_code:"+status_code	*/
				/*		+", rs.getStatusCode:"+rs.getStatusCode()*//**/
				+", rs.getStatusCode:"+rs.statusCode	
							);//works!
				
			student.setStatusCode(rs.statusCode);
				
			studentRepository.save(student);
		}
		,()->{
			System.out.println("Line 85 StudentController.java says student not in db yet "
					/*				+student_id	*/
					/*			+", rs.student_id:"+rs.getStudent_id()*/
				+", rs.student_id:"+rs.student_id
					/*		+", status_code:"+status_code	*/
				/*		+", rs.getStatusCode:"+rs.getStatusCode()*//**/
				+", rs.getStatusCode:"+rs.statusCode	
							);//works!			
		});

		//rs.setStatusCode(status_code);//I'm trying actually to send it through in rs but..not working yet..this works though
		//problem is I'm putting user id in two places, the url and the request body - I know that's wrong - it's a working hack
		//student.setStatusCode(status_code);//this is probably not necessary
		//return 
				//studentRepository.save(rs);
		/*Um, nevermind, I think I did this all wrong:
		 * What happens in the database when a student's registration is on hold (referring to stories required in assignment 2 for registration section) ?

David Wisneski
5:59pmSep 13 at 5:59pm
If you look at code in the addCourse method in the Schedule controller,  if the status is not 0, then the student is not allowed to add a new Course to their schedule
...or did I? No, I guess not, not really, just trying to update the status code: student.getStatusCode()==0 line 80 scheduleCtlr.
		 * this is what I'm working on now
		 * */
		
	}

	
}
