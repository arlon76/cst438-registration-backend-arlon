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
	public static final String TEST_STUDENT_EMAIL = "test-email@csumb.edu";
	public static final String TEST_STUDENT_NAME  = "test name";
	public static final int TEST_STUDENT_STATUS_CODE  = 0;
	public static final String TEST_STUDENT_STATUS  = "test status";
	public static final int TEST_YEAR = 2022;
	public static final String TEST_SEMESTER = "Fall";

	@MockBean
	StudentRepository studentRepository;

	@Autowired
	private MockMvc mvc;

	@Test
	public void createNewStudent()  throws Exception {

		MockHttpServletResponse response;

		Student student = new Student();
		student.setEmail(TEST_STUDENT_EMAIL);
		student.setName(TEST_STUDENT_NAME);
		student.setStatusCode(0);
		student.setStudent_id(1);
		
		// given  -- stubs for database repositories that return test data
	    given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(student);
  
	    // create the DTO (data transfer object) for the student to add.  primary key course_id is 0.
	    StudentDTO studentDTO = new StudentDTO();
	    studentDTO.setStudent_id(TEST_STUDENT_ID);
				
		// then do an http post request with body of studentDTO as JSON
		response = mvc.perform(
				MockMvcRequestBuilders
			      .post("/student")
			      .content(asJsonString(studentDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
	
		// verify that return status = OK (value 200) 
		assertEquals(200, response.getStatus());
	/*			
		// verify that returned data has non zero primary key
		ScheduleDTO.CourseDTO result = fromJsonString(response.getContentAsString(), ScheduleDTO.CourseDTO.class);
		assertNotEquals( 0  , result.id);
		
		// verify that repository save method was called.
		verify(enrollmentRepository).save(any(Enrollment.class));
		
		// do http GET for student schedule 
		response = mvc.perform(
				MockMvcRequestBuilders
			      .get("/schedule?year=" + TEST_YEAR + "&semester=" + TEST_SEMESTER)
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// verify that return status = OK (value 200) 
		assertEquals(200, response.getStatus());
		
		// verify that returned data contains the added course 
		ScheduleDTO scheduleDTO = fromJsonString(response.getContentAsString(), ScheduleDTO.class);
		
		boolean found = false;		
		for (ScheduleDTO.CourseDTO sc : scheduleDTO.courses) {
			if (sc.course_id == TEST_COURSE_ID) {
				found = true;
			}
		}
		assertEquals(true, found, "Added course not in updated schedule.");
		
		// verify that repository find method was called.
		verify(enrollmentRepository, times(1)).findStudentSchedule(TEST_STUDENT_EMAIL, TEST_YEAR, TEST_SEMESTER);
		*/
	}

	private byte[] asJsonString(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
