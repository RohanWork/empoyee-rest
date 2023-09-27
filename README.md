README file description repository that includes a brief overview of your Spring Boot project for employee management (CRUD) with a focus on the table structure:

---

# Employee Management System - Spring Boot Project

This is a Spring Boot project designed for managing employee data, including Create, Read, Update, and Delete (CRUD) operations. The project is built in Java and utilizes a MySQL database with a specific table structure to store employee information.

## Table Structure

The project uses a MySQL database with the following table structure:

## `employees` Table

- `empid` (INT): Primary key for identifying employees.
- `fname` (VARCHAR(25)) NOT NULL: First name of the employee.
- `lname` (VARCHAR(25)) NOT NULL: Last name of the employee.
- `mailid` (VARCHAR(25)) NOT NULL: Email ID of the employee.
- `department` (VARCHAR(25)) NOT NULL: The department to which the employee belongs.
- `location` (VARCHAR(25)) NOT NULL: The location or office of the employee.
- `salary` (DECIMAL(10, 2)) NOT NULL: The salary of the employee.

Each column in the `employees` table has constraints to ensure that values are not null and do not contain empty strings.

## Features

- **Create Employee**: Add new employee records to the database.
- **Read Employee**: Retrieve employee information, including searching by employee ID.
- **Update Employee**: Modify existing employee records.
- **Delete Employee**: Remove employee records from the database.
- **Additional Operations**: Operations specific to employee management needs. Please refer to the [Additional Operations](#additional-operations) section.

## Additional Operations

In addition to the basic CRUD (Create, Read, Update, Delete) operations, this Employee Management REST API project offers a range of additional operations to enhance functionality.

## Employee Audit Table - `employees_audit`

The `employees_audit` table is designed to record audit entries for changes made to employee records. This table helps maintain a historical record of important events and changes within the employee management system.

## Table Structure

The `employees_audit` table has the following structure:

- `empid` (INT) NOT NULL: References the employee ID to associate the audit entry with a specific employee.
- `fname` (VARCHAR(25)) NOT NULL: First name of the employee at the time of the audit.
- `lname` (VARCHAR(25)) NOT NULL: Last name of the employee at the time of the audit.
- `mailid` (VARCHAR(25)) NOT NULL: Email ID of the employee.
- `department` (VARCHAR(25)) NOT NULL: The department to which the employee belongs.
- `location` (VARCHAR(25)) NOT NULL: The location or office of the employee at the time of the audit.
- `salary` (DECIMAL(10, 2)) NOT NULL: The salary of the employee at the time of the audit.
- `action` (VARCHAR(10)) NOT NULL: Describes the type of action (e.g., "Create," "Update," "Delete").
- `row_ins_tms` (TIMESTAMP): Records the timestamp when the audit entry is inserted (defaults to the current timestamp).
- `row_del_tms` (TIMESTAMP): Records the timestamp when the corresponding employee record is deleted (nullable).

Each column in the `employees_audit` table has specific purposes related to auditing employee data changes.

## Usage

- **empid**: References the employee ID to link audit entries to specific employees.
- **fname**: Stores the first name of the employee at the time of the audit.
- **lname**: Stores the last name of the employee at the time of the audit.
- **mailid**: Stores the email ID of the employee at the time of the audit.
- **department**: Stores the department of the employee at the time of the audit.
- **location**: Stores the location or office of the employee at the time of the audit.
- **salary**: Stores the salary of the employee at the time of the audit.
- **action**: Describes the type of action that triggered the audit entry (e.g., "Created," "Updated," "Deleted").
- **row_ins_tms**: Records the timestamp when the audit entry is inserted in table.
- **row_del_tms**: Records the timestamp when the corresponding employee record is deleted from `employees` table.

## Example

Here's an example of an audit entry in the `employees_audit` table:

| empid | fname  | lname  | mailid                 | department | location   | salary   | action  | row_ins_tms        | row_del_tms        |
|-------|--------|--------|------------------------|------------|------------|----------|---------|---------------------|---------------------|
| 101   | john   | doe    | john.doe@example.com   | hr         | new york   | 50000.00 | created  | 2023-09-25 10:15:00 |                 |
| 101   | john   | doe  | john.doe@example.com | it         | san Francisco | 55000.00 | updated  | 2023-09-26 14:30:00 |                 |
| 102   | alice  | johnson| alice.johnson@example.com | finance | chicago    | 60000.00 | created  | 2023-09-27 09:45:00 |                 |
| 102   | alice  | johnson| alice.johnson@example.com | finance | chicago    | 60000.00 | deleted  | 2023-09-27 10:00:00 | 2023-09-27 15:20:00 |

In this example, audit entries are created when employees are added (`Create`) or updated (`Update`). The `row_del_tms` field remains `NULL` until an employee is deleted, at which point it records the deletion or updation timestamp.

## Audit Trail

The audit functionality allows you to track and maintain a record of important events and changes within the employee management system. It's essential for ensuring data integrity and accountability. The following audit operations are available:

- **Filter by Action Type**: `GET /audit?action={actionType}` - Filter audit logs by specific action types (e.g., Created, Updated, Deleted).
- **Filter by Employee ID**: `GET /audit?empid={empid}` - Filter audit logs by the employee ID to view actions related to a specific employee.

## Getting Started

To run this project locally, follow these steps:

1. Clone this repository to your local machine.
2. Configure your MySQL database settings in the application properties.
    - spring.datasource.url=jdbc:mysql://localhost:3306/rest
    - Under rest create `employees` and `employees_audit` with the mentioned entities as [above](#employees-table).
3. Build and run the Spring Boot application.

Make sure you have Java and Maven installed on your machine.

