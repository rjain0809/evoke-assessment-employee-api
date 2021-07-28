package org.evoke.assignment.employee.details;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.evoke.assignment.employee.model.Employee;
import org.evoke.assignment.employee.model.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ControllerEmployeeTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	

	private static final String API = "/evoke/api/";

	@Test
	void testSaveEmployee() throws Exception {

		Employee employee = new Employee();
		employee.setCreatedBy("Smith");
		employee.setCreatedOn(new Date(System.currentTimeMillis()));
		employee.setEmail("smith@gmail.com");
		employee.setEmpId(43);
		employee.setName("Van Diesel");
		employee.setPhone("989823456");

		ResponseEntity<ResponseMessage> responseEntity = testRestTemplate.postForEntity(API, employee, ResponseMessage.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}
}
