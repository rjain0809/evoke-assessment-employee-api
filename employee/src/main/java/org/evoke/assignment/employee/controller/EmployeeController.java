package org.evoke.assignment.employee.controller;

import java.util.Date;
import java.util.List;

import org.evoke.assignment.employee.entity.EmployeeEntity;
import org.evoke.assignment.employee.handler.EmployeeNotFound;
import org.evoke.assignment.employee.model.Employee;
import org.evoke.assignment.employee.model.ResponseMessage;
import org.evoke.assignment.employee.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "evoke/api")
public class EmployeeController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService empService;

	@GetMapping("/")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployeeDetail() {
		logger.info("Start EmployeeController::getAllEmployeeDetail()");
		List<EmployeeEntity> listemp = null;

		listemp = empService.getEmployeeDetail();
		logger.info("End EmployeeService::getAllEmployeeDetail()");
		return new ResponseEntity<>(listemp, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ResponseMessage> saveEmployeeDetail(@RequestBody Employee employee) {
		logger.info("Start EmployeeController::saveEmployeeDetail(employee)");
		Employee emp = null;
		ResponseMessage response = null;

		emp = empService.saveEmployee(employee);
		response = new ResponseMessage();
		if (emp.getEmpId() != null) {
			response.setMessage("Employee Created");
			response.setStatus(true);
		}
		logger.info("End EmployeeController::saveEmployeeDetail(employee)");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseMessage> updateEmployeeDetail(@RequestBody Employee employee) {
		logger.info("Start EmployeeController::updateEmployeeDetail(employee)");
		
		Employee emp = null;
		ResponseMessage response = null;

		emp = empService.updateEmployeeDetail(employee);

		response = new ResponseMessage();
		response.setMessage("Employee " + emp.getEmpId() + " data is updated");
		response.setStatus(true);
		logger.info("End EmployeeController::updateEmployeeDetail(employee)");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{empid}")
	public ResponseEntity<ResponseMessage> deleteEmployee(@PathVariable Integer empid) {
		logger.info("Start EmployeeController::deleteEmployee(empid)");
		ResponseMessage response = null;
		Date date = null;

		if (empid != null) {
			response = new ResponseMessage();
			date = new Date(System.currentTimeMillis());
			empService.employeeDelete(empid);
			response.setMessage("Employee " + empid + " Data is Deleted");
			response.setStatus(true);
			response.setResponsedate(date);
		}
		logger.info("End EmployeeController::deleteEmployee(empid)");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Integer id) {
		logger.info("Start EmployeeController::getEmployeeById(id)");
		EmployeeEntity employee = null;

		employee = empService.getEmpDetailById(id);

		logger.info("End EmployeeController::getEmployeeById(id)");
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
}
