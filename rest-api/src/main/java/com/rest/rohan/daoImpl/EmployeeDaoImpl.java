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
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());

	private final String tableName1 = "employees";
	protected final String tableName2 = "employees_audit";

	private String sqlAllEmployees() throws Exception {
		return "select distinct * from " + tableName1 + " order by empid";
	}

	private String sqlGetEmployeeById() throws Exception {
		return "select * from " + tableName1 + " where empid=:empid";
	}

	private String sqlAddEmployeeToEM() {
		return "INSERT INTO " + tableName1 + " (empid, fname, lname, mailid, department, location, salary) VALUES (:empid, :fname, :lname, :mailid, :department, :location, :salary)";
	}

	private String sqlAddEmployeeToEA() {
		return "INSERT INTO " + tableName2 + " (empid, fname, lname, mailid, department, location, salary, action, row_ins_tms, row_del_tms) VALUES (:empid, :fname, :lname, :mailid, :department, :location, :salary, :action, :row_ins_tms, :row_del_tms)";
	}

	private String sqlAuditTableById() {
		return "select * from " + tableName2 + " where empid=:empid";
	}

	private String sqlDeleteEmployeeById(){
		return "DELETE FROM "+tableName1+" WHERE empid = :empid";
	}

	private String sqlUpdateAuditTable(){
		return "UPDATE "+tableName2+" SET action = :action, row_del_tms = :row_del_tms WHERE empid = :empid and action='insert'";
	}

	private String sqlVerifyRecords(){
		return "SELECT COUNT(*) FROM "+tableName1;
	}

	private String sqlUpdateMainTable() {
		return "update "+tableName1+" set fname=:fname, lname=:lname, department=:department, location=:location, salary=:salary where empid=:empid";
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
	public List<EmployeeEntity> getEmployeeById(int empid) throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("empid", empid);
		return namedParameterJdbcTemplate.query(sqlGetEmployeeById(), param, new BeanPropertyRowMapper<>(EmployeeEntity.class));
	}

	@Override
	public int createEmployee(EmployeeEntity request) throws Exception {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("empid", request.getEmpid());
		params.addValue("fname", request.getFname().toLowerCase());
		params.addValue("lname", request.getLname().toLowerCase());
		params.addValue("mailid", request.getMailid().toLowerCase());
		params.addValue("department", request.getDepartment().toLowerCase());
		params.addValue("location", request.getLocation().toLowerCase());
		params.addValue("salary", request.getSalary());

		int rowCount = namedParameterJdbcTemplate.update(sqlAddEmployeeToEM(), params);

		if (rowCount != 1) {
			throw new ValidationException("Error while adding new employee to table (DI:L:110)");
		} else {
			params.addValue("action", ("insert").toLowerCase());
			params.addValue("row_ins_tms", currentTimestamp);
			params.addValue("row_del_tms", null);
			int res = namedParameterJdbcTemplate.update(sqlAddEmployeeToEA(), params);

			if (res != 1)
				throw new ValidationException("Error while adding new employee to audit table (DI:L:118)");
			else
				return res;
		}
	}

	@Override
	public void updateEmployee(EmployeeEntity request) throws Exception {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("empid", request.getEmpid());
		params.addValue("fname", request.getFname().toLowerCase());
		params.addValue("lname", request.getLname().toLowerCase());
		params.addValue("mailid", request.getMailid().toLowerCase());
		params.addValue("department", request.getDepartment().toLowerCase());
		params.addValue("location", request.getLocation().toLowerCase());
		params.addValue("salary", request.getSalary());

		int rowAffected = namedParameterJdbcTemplate.update(sqlUpdateMainTable(), params);
		if (rowAffected==0)
			throw new ValidationException("Error while updating record (DI:L:137)");
		else {
			params.addValue("action", ("update").toLowerCase());
			params.addValue("row_ins_tms", currentTimestamp);
			params.addValue("row_del_tms", null);
			int res = namedParameterJdbcTemplate.update(sqlAddEmployeeToEA(), params);
			if (res!=1)
				throw new ValidationException("Error while inserting new record in update (DI:L:144)");
		}
	}

	@Override
	public List<EmployeeEntity> auditTable(int empid) throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("empid", empid);
		return namedParameterJdbcTemplate.query(sqlAuditTableById(), param, new BeanPropertyRowMapper<>(EmployeeEntity.class));
	}


	@Override
	public int deleteEmployee(int empid) throws Exception{
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("empid", empid);
		int rowCount = namedParameterJdbcTemplate.update(sqlDeleteEmployeeById(), param);
		if (rowCount!=1)
			throw new ValidationException("Error while deleting record (DI:L:162)");
		else {
			param.addValue("action","delete");
			param.addValue("row_del_tms",currentTimestamp);
			int rowAffect = namedParameterJdbcTemplate.update(sqlUpdateAuditTable(), param);
			if (rowAffect!=1){
				throw new ValidationException("Error while updating audit table in delete (DI:L:168)");
			}
			else
				return rowAffect;
		}
	}

	@Override
	public int verifyRecords() throws Exception {
//		List<Integer> rowCount = namedParameterJdbcTemplate.query(sqlVerifyRecords(), new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Integer.class));
//		int[] rowCountArray = rowCount.stream().mapToInt(Integer::intValue).toArray();
		int rowCount = namedParameterJdbcTemplate.queryForObject(sqlVerifyRecords(), new MapSqlParameterSource(), Integer.class);
		return rowCount;

//		return rowCount.get(0);
	}
}