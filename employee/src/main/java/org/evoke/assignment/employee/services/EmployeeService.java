package org.evoke.assignment.employee.services;

import java.util.Date;
import java.util.List;

import org.evoke.assignment.employee.dao.EmployeeRepository;
import org.evoke.assignment.employee.entity.EmployeeEntity;
import org.evoke.assignment.employee.handler.EmployeeNotFound;
import org.evoke.assignment.employee.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	EmployeeRepository emprepository;

	private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	// Saving employee detail
	@Override
	public Employee saveEmployee(Employee emp) {
		EmployeeEntity employeeEntity = null;
		Employee employee = null;
		Date date = null;

		logger.info("Start EmployeeService::saveEmployee(emp)");

		if (emp == null) {
			logger.error("Employee is empty");
			throw new EmployeeNotFound("Employee Data is not available");
		}
		date = new Date(System.currentTimeMillis());
		emp.setCreatedOn(date);

		employeeEntity = new EmployeeEntity();
		employeeEntity.setName(emp.getName());
		employeeEntity.setEmail(emp.getEmail());
		employeeEntity.setPhone(emp.getPhone());
		employeeEntity.setCreatedBy(emp.getCreatedBy());
		employeeEntity.setCreatedOn(date);

		employeeEntity = emprepository.saveEmployeeDetail(employeeEntity);
		if (employeeEntity == null) {
			logger.error("Employee is not created");
			throw new EmployeeNotFound("Employee not created");
		}
		employee = new Employee();
		employee.setCreatedBy(employeeEntity.getCreatedBy());
		employee.setCreatedOn(employeeEntity.getCreatedOn());
		employee.setEmail(employeeEntity.getEmail());
		employee.setEmpId(employeeEntity.getEmpId());
		employee.setName(employeeEntity.getName());
		employee.setPhone(employeeEntity.getPhone());
		logger.info("Saving the Employee: {}", employee);
		logger.info("End EmployeeService::saveEmployee(emp)");

		return employee;
	}

	// Get employee detail by id
	@Override
	public EmployeeEntity getEmpDetailById(Integer id) {
		EmployeeEntity employee = null;
		logger.info("start EmployeeService::getEmpDetailById(id)");

		employee = emprepository.findEmployeeById(id);
		if (employee == null) {
			logger.error("Employee id is not available in the database");
			throw new EmployeeNotFound("Employee Not available");
		}
		logger.info("End EmployeeService::getEmpDetailById(id)");
		return employee;
	}

	// All employee detail
	@Override
	public List<EmployeeEntity> getEmployeeDetail() {
		logger.info("Start EmployeeService::getEmployeeDetail()");
		List<EmployeeEntity> listemp = null;
		listemp = emprepository.getAllEmployeeDetail();

		if (listemp.isEmpty()) {
			logger.error("listemp is empty no data is available in the database");
			throw new EmployeeNotFound("No Employee Data is available in the dataabase");
		}
		logger.info("Employee detail {}", listemp);
		logger.info("End EmployeeService::getEmployeeDetail()");
		return listemp;
	}

	// Delete employee detail
	@Override
	public void employeeDelete(int id) {
		logger.info("Start EmployeeService::employeeDelete(id)");
		EmployeeEntity empentity = null;
		empentity = emprepository.findEmployeeById(id);
		if (empentity == null) {
			throw new EmployeeNotFound("Employee id is not available");
		}
		emprepository.deleteEmployeeData(id);
		logger.info("End EmployeeService::employeeDelete(id)");
	}

	@Override
	public Employee updateEmployeeDetail(Employee emp) {
		logger.info("Start EmployeeService::updateEmployeeDetail(emp)");
		EmployeeEntity empentity = null;
		if (emp == null) {
			throw new EmployeeNotFound("Provide Employee data");
		}
		empentity = emprepository.findEmployeeById(emp.getEmpId());
		if (empentity == null) {
			throw new EmployeeNotFound("Employee id is not available");
		}
		empentity.setEmpId(emp.getEmpId());
		empentity.setCreatedBy(emp.getCreatedBy());
		empentity.setCreatedOn(new Date(System.currentTimeMillis()));
		empentity.setEmail(emp.getEmail());
		empentity.setName(emp.getName());
		empentity.setPhone(emp.getPhone());

		emprepository.updateEmployee(empentity);
		logger.info("End EmployeeService::updateEmployeeDetail(emp)");
		return emp;
	}

}
