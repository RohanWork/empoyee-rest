package com.rest.rohan.dao;

import java.util.List;

import com.rest.rohan.mapper.entity.EmployeeEntity;

public interface EmployeeDao {

	List<EmployeeEntity> getAllEmployees() throws Exception;
	
	List<EmployeeEntity> getEmployeeById(int empid) throws Exception;
	
	public int createEmployee(EmployeeEntity request) throws Exception;
}
