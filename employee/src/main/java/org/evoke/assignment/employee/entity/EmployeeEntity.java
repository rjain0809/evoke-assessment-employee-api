package org.evoke.assignment.employee.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "employee")
public class EmployeeEntity implements Serializable {

	private static final long serialVersionUID = 3048159933705224191L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer empId;
	protected String name;
	protected String email;
	protected String phone;
	protected String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdOn;

	public EmployeeEntity() {
		super();
	}


	public EmployeeEntity(Integer empId, String name, String email, String phone, String createdBy, Date createdOn) {
		super();
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getEmpId() {
		return empId;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + "]";
	}

}
