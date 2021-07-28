package org.evoke.assignment.employee.details;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.evoke.assignment.employee.controller.EmployeeController;
import org.evoke.assignment.employee.entity.EmployeeEntity;
import org.evoke.assignment.employee.model.Employee;
import org.evoke.assignment.employee.model.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ControllerEmployeeTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	EmployeeController empcontroller;

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

		ResponseEntity<ResponseMessage> responseEntity = testRestTemplate
				.postForEntity("http://localhost:" + port + API, employee, ResponseMessage.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	void testExceptionInSaveEmployee() {
		Employee employee = new Employee();
		ResponseEntity<ResponseMessage> responseEntity = testRestTemplate
				.postForEntity("http://localhost:" + port + API, employee, ResponseMessage.class);
		assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
		assertEquals("Employee Name should not be empty", responseEntity.getBody().getMessage());
	}

	@Test
	void testallEmployee() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<List<Employee>> response = testRestTemplate.exchange("http://localhost:" + port + API,
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<Employee>>() {
				});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(!(response.getBody()).isEmpty());
	}

	@Test
	void testUpdateEmployee() {
		Employee employee = new Employee();
		employee.setCreatedBy("Smith");
		employee.setCreatedOn(new Date(System.currentTimeMillis()));
		employee.setEmail("smith@gmail.com");
		employee.setEmpId(103);
		employee.setName("Van Diesel");
		employee.setPhone("989823456");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);

		ResponseEntity<ResponseMessage> response = testRestTemplate.exchange("http://localhost:" + port + API,
				HttpMethod.PUT, entity, ResponseMessage.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Employee " + employee.getEmpId() + " data is updated", response.getBody().getMessage());
	}

	@Test
	void testExceptionUpdateEmployee() {
		Employee employee = new Employee();
		employee.setEmpId(34334);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);

		ResponseEntity<ResponseMessage> response = testRestTemplate.exchange("http://localhost:" + port + API,
				HttpMethod.PUT, entity, ResponseMessage.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Employee id is not available", response.getBody().getMessage());
	}

	//@Test
	void deleteEmployee() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Map<String, Integer> param = new HashMap<>();
		param.put("empid", 102);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
		
		//when(empcontroller.deleteEmployee(1)).thenReturn(new ResponseEntity<ResponseMessage>(HttpStatus.OK));
		ResponseEntity<ResponseMessage> response = testRestTemplate.exchange("http://localhost:" + port + API +"/{empid}",
				HttpMethod.DELETE, entity, ResponseMessage.class,param);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void deleteEmployeeExceptiontest() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Map<String, Integer> param = new HashMap<>();
		param.put("empid", 102);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
		
		ResponseEntity<ResponseMessage> response = testRestTemplate.exchange("http://localhost:" + port + API +"/{empid}",
				HttpMethod.DELETE, entity, ResponseMessage.class,param);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Employee id is not available",response.getBody().getMessage());
	}
	
	@Test
	void getEmployeeByIdTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Map<String, Integer> param = new HashMap<>();
		param.put("id", 40);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
		
		ResponseEntity<ResponseMessage> response = testRestTemplate.exchange("http://localhost:" + port + API +"/{id}",
				HttpMethod.GET, entity, ResponseMessage.class,param);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void getEmployeeByIdExceptionTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Map<String, Integer> param = new HashMap<>();
		param.put("id", 4032);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
		
		ResponseEntity<ResponseMessage> response = testRestTemplate.exchange("http://localhost:" + port + API +"/{id}",
				HttpMethod.GET, entity, ResponseMessage.class,param);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Employee Not available", response.getBody().getMessage());
	}
}
