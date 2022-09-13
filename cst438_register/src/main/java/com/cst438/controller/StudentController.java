package com.cst438.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cst438.domain.Enrollment;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;

@RestController
public class StudentController {
	
	@PostMapping("/student")	
	public String createNewStudent() {
		
		return "student id = 12398";
	}
	//copied from schedule controller:
	/* 
	 * helper method to transform course, enrollment, student entities into 
	 * a an instance of ScheduleDTO to return to front end.
	 * This makes the front end less dependent on the details of the database.
	 */
	/*
	 * private ScheduleDTO createSchedule(int year, String semester, Student s,
	 * List<Enrollment> enrollments) { ScheduleDTO result = new ScheduleDTO();
	 * result.semester = semester; result.year = year; result.student_email =
	 * s.getEmail(); result.student_id = s.getStudent_id();
	 * ArrayList<ScheduleDTO.CourseDTO> courses = new ArrayList<>();
	 * 
	 * for (Enrollment e : enrollments) { ScheduleDTO.CourseDTO courseDTO =
	 * createCourseDTO(e); courses.add(courseDTO); } result.courses = courses;
	 * return result; }
	 */
}
