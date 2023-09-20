package com.rest.rohan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rest.rohan.mapper.entity.EmployeeEntity;

public interface EmployeeService {
	
	public List<EmployeeEntity> getAllEmployees() throws Exception;
	
	public List<EmployeeEntity> getEmployeeById(int empid) throws Exception;
	
	public void createEmployee(EmployeeEntity request) throws Exception;

}
