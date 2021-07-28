package org.evoke.assignment.employee.details;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.evoke.assignment.employee.dao.EmployeeRepository;
import org.evoke.assignment.employee.entity.EmployeeEntity;
import org.evoke.assignment.employee.handler.EmployeeNotFound;
import org.evoke.assignment.employee.services.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class EmployeeServiceTest {

	@Autowired
	private IEmployeeService service;

	@MockBean
	private EmployeeRepository repo;

	/*
	 * @Test void testGetEmployeeInfo() {
	 * when(repo.getAllEmployeeDetail()).thenReturn(Stream.of( new
	 * EmployeeEntity("John", "John@gmail.com", "989878789", "Smith HR", new
	 * Date(System.currentTimeMillis())), new EmployeeEntity("Warn",
	 * "John@gmail.com", "989878789", "Mike HR", new
	 * Date(System.currentTimeMillis()))) .collect(Collectors.toList()));
	 * assertEquals(2, service.getEmployeeDetail().size()); }
	 */

	@Test
	void testGetEmployeeInfoException() {
		List emptylist = Collections.emptyList();
		when(repo.getAllEmployeeDetail()).thenReturn(emptylist);
		assertThrows(EmployeeNotFound.class, () -> service.getEmployeeDetail());
	}

	@Test
	void testdeleteEmployeeData() {
		doThrow(EmployeeNotFound.class).when(repo).deleteEmployeeData(0);
		assertThrows(EmployeeNotFound.class, () -> service.employeeDelete(0));
	}

	/*
	 * @Test void testGetEmpDetailById() {
	 * when(repo.findEmployeeById(1)).thenReturn(new EmployeeEntity("Smith",
	 * "smith@gmail.com", "98348934893", "Yun HR", new
	 * Date(System.currentTimeMillis()))); assertEquals("Smith",
	 * service.getEmpDetailById(1).getName()); assertEquals("smith@gmail.com",
	 * service.getEmpDetailById(1).getEmail()); }
	 */

	/*
	 * @Test void testSaveEmployee() { EmployeeEntity emp = new
	 * EmployeeEntity("Smith", "smith@gmail.com", "98348934893", "Yun HR", new
	 * Date(System.currentTimeMillis()));
	 * when(repo.saveEmployeeDetail(emp)).thenReturn(emp); String empName =
	 * service.saveEmployee(emp).getName(); assertEquals(emp.getName(), empName); }
	 */
	
	
}
