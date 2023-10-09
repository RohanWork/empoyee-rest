package com.rest.rohan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.rest.rohan.exception.ValidationException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import com.rest.rohan.dao.EmployeeDao;
import com.rest.rohan.mapper.entity.EmployeeEntity;
import com.rest.rohan.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;

	public static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}
	
	@Override
	public List<EmployeeEntity> getAllEmployees() throws Exception {
		List<EmployeeEntity> employees = employeeDao.getAllEmployees();
		
		if (employees.isEmpty()) {
			throw new ValidationException("Not able to retrieve any records from the database (ServiceImpl:Line:"+getLineNumber()+")");
		}else
			return employees;		
	}
	
	@Override
	public List<EmployeeEntity> getEmployeeById(int empid) throws Exception{
		List<EmployeeEntity> employeeIdRecord = employeeDao.getEmployeeById(empid);
		if (employeeIdRecord.isEmpty())
			throw new ValidationException("Record not found for id "+ empid);
		else
			return employeeIdRecord;
	}
	
	@Override
	public void createEmployee(EmployeeEntity request) throws Exception{
		List<EmployeeEntity> employeeIds = employeeDao.getAllEmployees();
		boolean exists = employeeIds.stream().anyMatch(employee -> employee.getEmpid() == request.getEmpid() || employee.getMailid().equals(request.getMailid()));
		if (exists)
			throw new ValidationException("The empid/mailid already exists hence cannot create record");
		else
		{
			int rowsAffected = employeeDao.createEmployee(request);
			if (rowsAffected!=1)
				throw new ValidationException("Error while adding new employee (ServiceImpl:Line:"+getLineNumber()+")");
		}
	}

	@Override
	public List<EmployeeEntity> auditTable(int empid) throws Exception{
		int rowPresent = isRecordExists(empid);
		if (rowPresent==0)
			throw new ValidationException("Employee recored not available (ServiceImpl:Line:"+getLineNumber()+")");
		else {
			List<EmployeeEntity> auditRecord = employeeDao.auditTable(empid);
			if (auditRecord.isEmpty())
				throw new ValidationException("Not able to retrieve any records from the database (ServiceImpl:Line:"+getLineNumber()+")");
			else
				return auditRecord;
		}
	}

	@Override
	public void deleteEmployee(int empid) throws Exception{
		int rowPresent = isRecordExists(empid);
		if (rowPresent==0)
			throw new ValidationException("Employee recored not available (ServiceImpl:Line:"+getLineNumber()+")");
		else {
			int rowCount = verifyRecords();
			if (rowCount == 1)
				throw new ValidationException("Only one record is present not able to perform delete operation (ServiceImpl:Line:"+getLineNumber()+")");
			else {
				int rowResult = employeeDao.deleteEmployee(empid);
				if (rowResult != 1)
					throw new ValidationException("Error while deleting record (ServiceImpl:Line:"+getLineNumber()+")");
			}
		}
	}

	@Override
	public void updateEmployee(EmployeeEntity employeeEntity) throws Exception {
		int rowPresent = isRecordExists(employeeEntity.getEmpid());
		if (rowPresent==0)
			throw new ValidationException("Record not present for the given empid (ServiceImpl:Line:"+getLineNumber()+")");
		else
			employeeDao.updateEmployee(employeeEntity);
	}

	public int verifyRecords() throws Exception{
		return employeeDao.verifyRecords();
	}

	public int isRecordExists(int empid) throws Exception{
		List<EmployeeEntity> res = employeeDao.getEmployeeById(empid);
		if (res.isEmpty())
			return 0;
		else
			return 1;
	}

}