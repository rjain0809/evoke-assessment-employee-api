package org.evoke.assignment.employee.services;

import java.util.List;

import org.evoke.assignment.employee.entity.EmployeeEntity;
import org.evoke.assignment.employee.model.Employee;

public interface IEmployeeService {

	Employee saveEmployee(Employee emp);
	EmployeeEntity getEmpDetailById(Integer id);
	List<EmployeeEntity> getEmployeeDetail();
	void employeeDelete(int id);
	Employee updateEmployeeDetail(Employee emp);
}
