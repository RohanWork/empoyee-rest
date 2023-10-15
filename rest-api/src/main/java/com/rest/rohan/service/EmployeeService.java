package com.rest.rohan.service;

import java.util.List;
import com.rest.rohan.mapper.entity.EmployeeEntity;

public interface EmployeeService {
	
	List<EmployeeEntity> getAllEmployees() throws Exception;
	
	List<EmployeeEntity> getEmployeeById(int empid) throws Exception;
	
	void createEmployee(EmployeeEntity request) throws Exception;

	List<EmployeeEntity> auditTable(int empid) throws Exception;

	void deleteEmployee(int empid) throws Exception;

	void updateEmployee(EmployeeEntity employeeEntity) throws Exception;
}
