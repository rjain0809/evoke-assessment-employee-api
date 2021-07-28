package org.evoke.assignment.employee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.evoke.assignment.employee.entity.EmployeeEntity;
import org.evoke.assignment.employee.handler.EmployeeNotFound;
import org.evoke.assignment.employee.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	private EntityManager em;

	private static Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);

	// Insert Employee Detail
	public EmployeeEntity saveEmployeeDetail(EmployeeEntity emp) {
		logger.info("start EmployeeRepo::saveEmployeeDetail(emp)");

		em.persist(emp);
		logger.info("End EmployeeRepo::saveEmployeeDetail(emp)");
		return emp;
	}

	//update employee detail
	public EmployeeEntity updateEmployee(EmployeeEntity emp) {
		logger.info("Start EmployeeRepository::updateEmployeeDetail(emp)");
		EmployeeEntity updateEmployee = null;

		updateEmployee = em.merge(emp);
		logger.info("End EmployeeRepository::updateEmployeeDetail(emp)");
		return updateEmployee;
	}

	// Get Employee Detail based on employee id
	public EmployeeEntity findEmployeeById(Integer id) {
		logger.info("start EmployeeRepo::findEmployeeById(id)");
		EmployeeEntity employeeDetail = null;

		employeeDetail = em.find(EmployeeEntity.class, id);
		logger.info("Employee Detail by id: {}", employeeDetail);
		logger.info("End EmployeeRepo::findEmployeeById(id)");
		return employeeDetail;
	}

	// Get all employee detail
	public List<EmployeeEntity> getAllEmployeeDetail() {
		logger.info("start EmployeeRepo::getAllEmployeeDetail()");
		Query query = null;
		List<EmployeeEntity> listemp = null;

		query = em.createQuery("SELECT emp FROM EmployeeEntity emp", EmployeeEntity.class);
		listemp = query.getResultList();
		logger.info("List of Employee {}", listemp);
		logger.info("End EmployeeRepo::getAllEmployeeDetail()");
		return listemp;
	}

	// Delete employee detail
	public void deleteEmployeeData(int id) {
		logger.info("start EmployeeRepo::deleteEmployeeData(id)");
		EmployeeEntity emp = null;

		emp = em.find(EmployeeEntity.class, id);
		logger.info("Employee data {}", emp);
		if (emp == null) {
			logger.error("Employee data is not available in database");
			throw new EmployeeNotFound("Please Provide Correct Employee Id");
		} else {
			em.remove(emp);
			logger.info("Employee is removed");
		}
		logger.info("End EmployeeRepo::deleteEmployeeData(id)");
	}
}
