package com.rest.rohan.mapper.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
	private String action;
	private Timestamp row_del_tms;
	private Timestamp row_ins_tms;
	
	public EmployeeEntity() {
		super();
	}

	public EmployeeEntity(int empid, String fname, String lname, String mailid, String department, String location,
			BigDecimal salary, String action, Timestamp row_del_tms, Timestamp row_ins_tms) {
		super();
		this.empid = empid;
		this.fname = fname;
		this.lname = lname;
		this.mailid = mailid;
		this.department = department;
		this.location = location;
		this.salary = salary;
		this.action = action;
		this.row_ins_tms = row_ins_tms;
		this.row_del_tms = row_del_tms;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getRow_del_tms() {
		return row_del_tms;
	}

	public void setRow_del_tms(Timestamp row_del_tms) {
		this.row_del_tms = row_del_tms;
	}

	public Timestamp getRow_ins_tms() {
		return row_ins_tms;
	}

	public void setRow_ins_tms(Timestamp row_ins_tms) {
		this.row_ins_tms = row_ins_tms;
	}
}
