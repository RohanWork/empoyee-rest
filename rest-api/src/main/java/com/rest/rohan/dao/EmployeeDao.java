package com.rest.rohan.dao;

import java.util.List;

import com.rest.rohan.mapper.entity.EmployeeEntity;

public interface EmployeeDao {

	List<EmployeeEntity> getAllEmployees() throws Exception;
	
	List<EmployeeEntity> getEmployeeById(int empid) throws Exception;
	
	int createEmployee(EmployeeEntity request) throws Exception;

	List<EmployeeEntity> auditTable(int empid) throws Exception;

	int deleteEmployee(int empid) throws Exception;

	int verifyRecords() throws Exception;
}
