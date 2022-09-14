/**		StudentController
 * 
 * */
package com.cst438.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;
/**	Student controller with working createNewStudent and updateStudentStatusCode
 * @author arlon
 *
 */
@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	/**	Create New Student works, just send a Post request with the student_id and new statusCode
	 *	like:														to http://localhost:8080/student
	 *			{	"name":"New Guy"
	 *				,"email":"aasdfsdf@asdf.asd2f22f"
	 *				,"statusCode":0
	 *				,"status":"smart"
	 *			}
	 * 		if the email is in the database or if the fields are empty, it throws an exception.
	 * */
	@PostMapping("/student")
	@Transactional
	public Student createNewStudent( @RequestBody StudentDTO rs) throws ResponseStatusException {
					
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
			Student s=new Student();
			s.setName(rs.getName());s.setEmail(rs.getEmail());s.setStatus(rs.getStatus());s.setStatusCode(rs.getStatusCode());
			
			return studentRepository.save(s);//new student. it depends on the JSON 'student' sent in, parameter rs
											//it also depends on autowired studentRepository, and the annotations 
												//PostMapping & Transactional. It checks the email isn't already
													//present in the db and that the parameters aren't empty.
		}
	}
	
	/**	Update Student Status Code works, just send a Patch request with the student_id and new statusCode
	 *	like:														to http://localhost:8080/student
	 *			{"student_id":222,"statusCode":92}
	 * 		if the id's not in the database it throws an exception.
	 * */
	@PatchMapping("/student")
	@Transactional
	public Student updateStudentStatusCode(	@RequestBody StudentDTO rs) throws ResponseStatusException {	
		Optional<Student> optionalStudent = studentRepository.findById(rs.getStudent_id());
		optionalStudent.ifPresentOrElse(student->{	/*	there is a student with that ID	*/
					},()->{	/*	no student by that ID yet*/	});//how to return from that...instead:	
		if(optionalStudent.isPresent()){	//there's a student with that ID
			Student student=optionalStudent.get();
				System.out.println("Line 58 StudentController.java says:"
								+", rs.student_id:"+rs.getStudent_id()
						+", rs.getStatusCode:"+rs.getStatusCode()
				);//works!
			student.setStatusCode(rs.getStatusCode());
			Student dbstudent=studentRepository.save(student);
			return dbstudent;				
		}else {	/*	no student by that ID */
				System.out.println("Line 67 StudentController.java says that student id is not in the db yet "
								+", rs.student_id:"+rs.getStudent_id()
						+", rs.getStatusCode:"+rs.getStatusCode()
				);//works!
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "There's no student with that id ("+rs.getStudent_id()+")");
			//return null;			
		}
	}
}
