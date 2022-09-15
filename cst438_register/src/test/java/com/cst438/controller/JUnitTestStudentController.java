/**		JUnitTestStudentController
 * 
 */
package com.cst438.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.cst438.controller.ScheduleController;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.cst438.service.GradebookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.context.ContextConfiguration;

/**
 * @author arlon
 *
 */
@ContextConfiguration(classes = { StudentController.class })
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest

public class JUnitTestStudentController {

	static final String URL = "http://localhost:8080";
	public static final int TEST_STUDENT_ID = 19;
	public static final String TEST_STUDENT_EMAIL = "2test-email4@csumb.edu";
	public static final String TEST_STUDENT_NAME  = "test name";
	public static final int TEST_STUDENT_STATUS_CODE  = 42;
	public static final String TEST_STUDENT_STATUS  = "Writing JUnit Tests";
	public static final int TEST_YEAR = 2022;
	public static final String TEST_SEMESTER = "Fall";

	@MockBean
	StudentRepository studentRepository;

	@Autowired
	private MockMvc mvc;

	@Test
	public void createNewStudent()  throws Exception {

		MockHttpServletResponse response;

		System.out.println("Line 85 JUnitTestStudentController.java starting - what happens, right? (Arlon wants to know what happens)");
		
		Student student = new Student();		
		student.setEmail(TEST_STUDENT_EMAIL);
		student.setName(TEST_STUDENT_NAME);
		student.setStatusCode(TEST_STUDENT_STATUS_CODE);
		student.setStatus(TEST_STUDENT_STATUS);
		student.setStudent_id(TEST_STUDENT_ID);			//ok so there's a new student with these characteristics. Lets print it out:
		
		System.out.println("Line 93 JUnitTestStudentController.java created a new 'Student' with these characteristics:"
				+" name: "+student.getName()
				+" id: "+student.getStudent_id()
				+" Email: "+student.getEmail()
				+" StatusCode: "+student.getStatusCode()
				+" Status: "+student.getStatus()
			);
		
	// given  -- stubs for database repositories that return test data
//		   given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(student);//causing the email to be already found. So it's a working line.
	   given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(null);//causing the email to be already found. So it's a working line.
	   given(studentRepository.save(any())).willReturn(student);//denotes the answer
	   given(studentRepository.findById(TEST_STUDENT_ID)).willReturn(Optional.ofNullable(student));//denotes the answer
	//I don't understand this line yet. At all. One question I have is, does createNewStudent in StudentController happen?
	    //no, doesn't look like it. So how does this work then?
	    //one thing I'm seeing is that what this line says, the other file does. like, the other file is returning email found,
	    //even when it's not, except here. maybe if I comment it out.
	    //as commented out - I did that because it's causing the email to be already found. So it's a working line.
	    //after reading mockito when/then I think what it does is short-circuit that method, giving it its return value.
	    
	    // create the DTO (data transfer object) for the student to add.  primary key course_id is 0.
	    StudentDTO studentDTO = new StudentDTO();
	    studentDTO.setStudent_id(TEST_STUDENT_ID);
	    studentDTO.setName(TEST_STUDENT_NAME);
	    studentDTO.setEmail(TEST_STUDENT_EMAIL);
	    studentDTO.setStatus(TEST_STUDENT_STATUS);
	    studentDTO.setStatusCode(TEST_STUDENT_STATUS_CODE);
	    	//thats the same thing again but in DTO form. I don't at all understand where this is going.
  				
		// then do an http post request with body of studentDTO as JSON
		response = mvc.perform(
				MockMvcRequestBuilders
			      .post("/student")
			      .content(asJsonString(studentDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		//this DOES call the original method. Data printed is what's here.
		//test 1 passed. And that student was not added to the db even though I know from testing with postman
		//that the regular method does work. So what has this done so far? 
		//well it verifies asJsonString works, it verifies the original method gets called, it uses this data
		//

			//this is 400 and it says because the email was already added.
		// verify that return status = OK (value 200) 
			assertEquals(200, response.getStatus());
			//that was from the given line, above

		
		//it's not, it's choking, on the save line for some reason.
		//I saw in the book to use the @AutoConfigureTestDatabase annotation but it didn't say where or how to use it.
		
		//sort of stuck here with these questions:
			//not sure of the objective here
			//	I know I'm testing createNewStudent, and it's already proved some peripheral aspects of it, but
			//this currently chokes on the save line in the real file.
			//it doesn't go past that line (per print statements in that file)
			// it does fine with postman testing the file - the feature works in other words - this test file doesn't
		//it causes that other file to choke.
		//so this next line, check the result - the result is that that method chokes and doesnt return anything
		//reminder: I did change pom.xml like the book says to add h2...didn't help
			
		// verify that returned data has non zero primary key
		Student result = fromJsonString(response.getContentAsString(), Student.class);
		assertNotEquals( 0  , result.getStudent_id());
		
		// verify that repository save method was called.
		verify(studentRepository).save(any(Student.class));

		
		// verify that returned data contains the added student 
		Student rstudent = fromJsonString(response.getContentAsString(), Student.class);
/*			boolean found = false;		
		//for (Student s : scheduleDTO.courses) {
			if (rstudent.getStudent_id() == TEST_STUDENT_ID) {
				found = true;
			}
		// }
		assertEquals(true, found, "Added student not in db.");
		// verify that repository find method was called.
		verify(studentRepository, times(1)).findByEmail(TEST_STUDENT_EMAIL);
	*/
		
		
		//now say it's there already, same test, but with a 400:
		given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(student);//causing the email to be already found. So it's a working line.
		response = mvc.perform(
				MockMvcRequestBuilders
			      .post("/student")
			   /*   .content(asJsonString(studentDTO))*/	
			      .contentType(MediaType.APPLICATION_JSON)
			   /*   .accept(MediaType.APPLICATION_JSON)	*/		)
			.andReturn().getResponse();
		assertEquals(400, response.getStatus());
		//that was from the given line, above
			
		// do http GET for student 
		response = mvc.perform(
				MockMvcRequestBuilders
			      .get("/student?id="+result.getStudent_id() )
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		// verify that return status = OK (value 200) 
		assertEquals(200, response.getStatus());		
		
	}
	private static String asJsonString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static <T> T  fromJsonString(String str, Class<T> valueType ) {
		try {
			return new ObjectMapper().readValue(str, valueType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
