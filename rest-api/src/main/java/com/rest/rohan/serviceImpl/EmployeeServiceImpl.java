package com.rest.rohan.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rest.rohan.exception.ValidationException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.rohan.dao.EmployeeDao;
import com.rest.rohan.mapper.entity.EmployeeEntity;
import com.rest.rohan.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public List<EmployeeEntity> getAllEmployees() throws Exception {
		List<EmployeeEntity> employees = new ArrayList<>();
		employees = employeeDao.getAllEmployees();
		
		if (employees.isEmpty()) {
			throw new ValidationException("Not able to retrieve any records from the database");
		}
		else
			return employees;		
	}
	
	@Override
	public List<EmployeeEntity> getEmployeeById(int empid) throws Exception{
		List<EmployeeEntity> employeeIdRecord = new ArrayList<>();
		employeeIdRecord = employeeDao.getEmployeeById(empid);
		if (employeeIdRecord.isEmpty()) {
			throw new ValidationException("Record not found for id "+ empid);
		}
		else
			return employeeIdRecord;
	}
	
	@Override
	public void createEmployee(EmployeeEntity request) throws Exception{
		List<EmployeeEntity> employeeIds = employeeDao.getAllEmployees();
		EmployeeEntity finalReq = request;
//		Optional<EmployeeEntity> empIds = employeeIds.parallelStream().filter(f -> f.equals(finalReq.getEmpid())).findFirst();
//		if (empIds.isPresent()) {
//			throw new ValidationException("Employee already exists with this Id");
//		}
//		else
		{
			int rowsAffected = employeeDao.createEmployee(request);
			
			if (rowsAffected!=1) {
				throw new ValidationException("Error while adding new employee");
			}
			else
				return;
		}
	}

}
