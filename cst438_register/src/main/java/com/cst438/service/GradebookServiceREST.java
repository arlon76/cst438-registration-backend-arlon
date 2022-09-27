package com.cst438.service;
import static java.lang.System.out;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.cst438.domain.EnrollmentDTO;


public class GradebookServiceREST extends GradebookService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${gradebook.url}")
	String gradebook_url;
	
	public GradebookServiceREST() {
		System.out.println("REST grade book service");
	}

	@Override
	public void enrollStudent(String student_email, String student_name, int course_id) {
		
		//TODO  complete this method in homework 4
		EnrollmentDTO enrollment = new EnrollmentDTO();
		enrollment.course_id = course_id;
		enrollment.studentEmail = student_email;
		enrollment.studentName = student_name;
		
		out.println("Line 29 Gradebook ServiceREST says: Post to gradebook "+enrollment);
		EnrollmentDTO response = restTemplate.postForObject(gradebook_url+"/enrollment", enrollment, EnrollmentDTO.class);
		out.println("Line 31 Gradebook ServiceREST says: Response from gradebook: "+response);
	}

}
