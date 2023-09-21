package com.rest.rohan.daoImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rest.rohan.dao.EmployeeDao;
import com.rest.rohan.exception.ValidationException;
import com.rest.rohan.mapper.entity.EmployeeEntity;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private final String tableName1 = "employees";
	protected final String tableName2 = "employees_audit";
	
	private String sqlAllEmployees() throws Exception{
		return "select distinct * from "+tableName1+" order by empid";
	}
	
	private String sqlGetEmployeeById() throws Exception {
	    return "select * from "+tableName1+" where empid=:empid";
	} 
	
	private String sqlAddEmployeeToEM() {
		return "INSERT INTO "+tableName1+" (empid, fname, lname, mailid, department, location, salary) VALUES (:empid, :fname, :lname, :mailid, :department, :location, :salary)";
	}

	private String sqlAddEmployeeToEA() {
		return "INSERT INTO "+tableName2+" (empid, fname, lname, mailid, department, location, salary, action, row_ins_tms, row_del_tms) VALUES (:empid, :fname, :lname, :mailid, :department, :location, :salary, :action, :row_ins_tms, :row_del_tms)";
	}


	@Override
	public List<EmployeeEntity> getAllEmployees() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource();		
		return namedParameterJdbcTemplate.query(sqlAllEmployees(), param, new BeanPropertyRowMapper<>(EmployeeEntity.class));
	}
	
//	@Override
//	public List<EmployeeEntity> getAllEmployees() throws Exception {
//		List<EmployeeEntity> employees = new ArrayList<>();
//		MapSqlParameterSource param = new MapSqlParameterSource();		
//		return namedParameterJdbcTemplate.query(sqlAllEmployees(), param, new ResultSetExtractor<List<EmployeeEntity>>() {
//
//			@Override
//			public List<EmployeeEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
//				while(rs.next()) {
//					EmployeeEntity employeeEntity = new EmployeeEntity();
//					employeeEntity.setEmpid(rs.getInt("empid"));
//
//					employees.add(employeeEntity);
//				}
//				return employees;
//			}
//		});
//	}
	
	@Override
	public List<EmployeeEntity> getEmployeeById(int empid) throws Exception{
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("empid", empid);
		return namedParameterJdbcTemplate.query(sqlGetEmployeeById(), param, new BeanPropertyRowMapper<>(EmployeeEntity.class)); 
	}
	
	@Override
	public int createEmployee(EmployeeEntity request) throws Exception {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("empid", request.getEmpid());
		params.addValue("fname", request.getFname());
		params.addValue("lname", request.getLname());
		params.addValue("mailid", request.getMailid());
		params.addValue("department", request.getDepartment());
		params.addValue("location", request.getLocation());
		params.addValue("salary", request.getSalary());

		int rowCount = namedParameterJdbcTemplate.update(sqlAddEmployeeToEM(), params);

		if (rowCount!=1) {
			throw new ValidationException("Error while adding new employee to table");
		}
		else
		{
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());

			params.addValue("action", "insert");
			params.addValue("row_ins_tms", currentTimestamp);
			params.addValue("row_del_tms", null);
			int res = namedParameterJdbcTemplate.update(sqlAddEmployeeToEA(), params);

			if (res!=1)
				throw new ValidationException("Error while adding new employee to audit table");
			else
				return res;
		}
	}

}