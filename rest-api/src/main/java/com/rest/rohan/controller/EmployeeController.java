package com.rest.rohan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.rest.rohan.mapper.entity.EmployeeEntity;
import com.rest.rohan.response.EmployeeResponse;
import com.rest.rohan.service.EmployeeService;

@Controller
@RequestMapping("/empmgn")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping (value = "/employees", produces = "application/json; charset=UTF-8")
	public ResponseEntity<EmployeeResponse> getAllEmployees() throws Exception {
		EmployeeResponse response = new EmployeeResponse(); 
		response.setStatusCode(HttpStatus.OK.value()); 
		response.setStatusMessage("Success");
		response.setGetAllEmployees(employeeService.getAllEmployees()); 
		return ResponseEntity.ok(response);
	}
	
	@GetMapping (value = "/employees/{empid}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable int empid) throws Exception{
		EmployeeResponse response = new EmployeeResponse();
		response.setStatusCode(HttpStatus.OK.value());
		response.setStatusMessage("Success");
		response.setGetEmployeeById(employeeService.getEmployeeById(empid));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping (value = "/employees", produces = "application/json; charset=UTF-8")
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeEntity request) throws Exception{
		EmployeeResponse response = new EmployeeResponse();
		response.setStatusCode(HttpStatus.OK.value());
		response.setStatusMessage("Success");
//		response.setCreateEmployee(employeeService.createEmployee(request));
		employeeService.createEmployee(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping (value = "/employeesAudit/{empid}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<EmployeeResponse> auditTable(@PathVariable int empid) throws Exception{
		EmployeeResponse response = new EmployeeResponse();
		response.setStatusCode(HttpStatus.OK.value());
		response.setStatusMessage("Success");
		response.setAuditTable(employeeService.auditTable(empid));
		return ResponseEntity.ok(response);
	}

	@PutMapping (value = "/employees", produces = "application/json; charset=UTF-8")
	public ResponseEntity<EmployeeResponse> deleteEmployee(@RequestBody EmployeeEntity employeeEntity) throws Exception{
		EmployeeResponse response = new EmployeeResponse();
		response.setStatusCode(HttpStatus.OK.value());
		response.setStatusMessage("Success");
		employeeService.updateEmployee(employeeEntity);
		return ResponseEntity.ok(response);
	}

	@GetMapping (value = "/employeeDelete/{empid}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable int empid) throws Exception{
		EmployeeResponse response = new EmployeeResponse();
		response.setStatusCode(HttpStatus.OK.value());
		response.setStatusMessage("Success");
		employeeService.deleteEmployee(empid);
		return ResponseEntity.ok(response);
	}

}
