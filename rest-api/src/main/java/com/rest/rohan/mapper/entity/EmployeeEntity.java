package com.rest.rohan.mapper.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeEntity {
	
	private int empid;
	private String fname;
	private String lname;
	private String mailid;
	private String department;
	private String location;
	private BigDecimal salary;
	
	public EmployeeEntity() {
		super();
	}

	public EmployeeEntity(int empid, String fname, String lname, String mailid, String department, String location,
			BigDecimal salary) {
		super();
		this.empid = empid;
		this.fname = fname;
		this.lname = lname;
		this.mailid = mailid;
		this.department = department;
		this.location = location;
		this.salary = salary;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
}
