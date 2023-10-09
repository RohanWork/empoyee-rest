# Employee Management System - Spring Boot Project

This is a Spring Boot project designed for managing employee data, including Create, Read, Update, and Delete (CRUD) operations. The project is built in Java and utilizes a MySQL database with a specific table structure to store employee information.

## Required Table Structure

In this project, we utilize a MySQL database with name of **`rest`**, a clear and organized table structure to store employee information. Below, you'll find a detailed breakdown of the tables and their columns.

## The Main Table : `employees`
The `employees` table has the following structure:

- **empid : (INT NOT NULL)**
    - This is the primary key used to uniquely identify each employee.
 
- **fname : (VARCHAR(25) NOT NULL)**
    - This column stores the first name of the employee. It's mandatory and cannot be left empty.
 
- **lname : (VARCHAR(25) NOT NULL)**
    - Similar to the first name, this column contains the last name of the employee. It's also mandatory and cannot be null.
       
- **mailid : (VARCHAR(100) NOT NULL)**
    - Here, we store the email address of the employee. Like other fields, it's mandatory to provide a valid email.
      
- **department : (VARCHAR(25) NOT NULL)**:
    - This column specifies the department to which the employee belongs, ensuring organized categorization.
      
- **location : (VARCHAR(25) NOT NULL)**:
    - It stores the location or office where the employee is situated, facilitating easy tracking.
      
- **salary :  (DECIMAL(10, 2) NOT NULL)**:
    - The salary of each employee is maintained here with precision using the DECIMAL data type (10, 2).

Each column in the employees table has strict constraints to guarantee data quality, preventing null values and empty strings from being stored. This structured approach ensures the reliability of employee data within the system.

## Here is the SQL command to create `employees` table
    
      CREATE TABLE employees (
        empid INT PRIMARY KEY,
        fname VARCHAR(25),
        lname VARCHAR(25),
        mailid VARCHAR(255),
        department VARCHAR(25),
        location VARCHAR(25),
        salary DECIMAL(10, 2)
      );

## Features

- **Create Employee :** Easily add new employee records to the database.
- **Read Employee :** Effortlessly retrieve detailed employee information, including convenient searching by employee ID.
- **Update Employee :** Seamlessly modify and update existing employee records.
- **Delete Employee :** Swiftly remove employee records from the database.
- **Additional Operations :** Explore specialized operations tailored to specific employee management needs in the [Additional Operations](#additional-operations) section.

## Additional Operations

In addition to the basic CRUD (Create, Read, Update, Delete) operations, this Employee Management REST API project offers some extra functionalities to make managing employee data even more versatile.

## Audit Table Structure

The `employees_audit` table is designed to record audit entries for changes made to employee records. This table helps maintain a historical record of important changes within the employee management system.

## The Audit Table : `employees_audit`
The `employees_audit` table has the following structure:

- **empid : (INT NOT NULL)**
    - This is the primary key used to uniquely identify each employee.

- **fname : (VARCHAR(25) NOT NULL)**
    - This column stores the first name of the employee. It's mandatory and cannot be left empty.

- **lname : (VARCHAR(25) NOT NULL)**
    - Similar to the first name, this column contains the last name of the employee. It's also mandatory and cannot be null.

- **mailid : (VARCHAR(100) NOT NULL)**
    - Here, we store the email address of the employee. Like other fields, it's mandatory to provide a valid email.

- **department : (VARCHAR(25) NOT NULL)**:
    - This column specifies the department to which the employee belongs, ensuring organized categorization.

- **location : (VARCHAR(25) NOT NULL)**:
    - It stores the location or office where the employee is situated, facilitating easy tracking.

- **salary :  (DECIMAL(10, 2) NOT NULL)**:
    - The salary of each employee is maintained here with precision using the DECIMAL data type (10, 2).

- **action : (VARCHAR(10) NOT NULL)**
    - The action column specifies the nature of the event (e.g., "Create," "Update," "Delete"), allowing you to trace every change.

- **row_ins_tms : (TIMESTAMP)**
    - This timestamp records when the audit entry was inserted, offering a chronological view of events (defaults to the current timestamp).

- **row_del_tms : (TIMESTAMP)**
    - Indicating when the corresponding employee record was deleted, if applicable, for a comprehensive historical record.

Each row in the employees_audit table captures a pivotal moment in the journey of an employee within the organization. This table functions as the historical archive, recording crucial events and changes related to employee records. It's like the backstage pass to understanding the evolution of your workforce management system.

## Here is the SQL command to create `employees_audit` table

    CREATE TABLE employees_audit (
      empid INT NOT NULL,
      fname VARCHAR(25) NOT NULL,
      lname VARCHAR(25) NOT NULL,
      mailid VARCHAR(255) NOT NULL,
      dapartment VARCHAR(25) NOT NULL,
      location VARCHAR(25) NOT NULL,
      salary DECIMAL(10, 2) NOT NULL,
      action VARCHAR(10) NOT NULL,
      row_ins_tms TIMESTAMP,
      row_del_tms TIMESTAMP
    );

## Audit Trail

The audit functionality enables you to monitor and preserve a log of significant events and modifications within the employee management system. It plays a crucial role in upholding data integrity and accountability. The following audit operations are available:

- **Filter by Action Type :**  `GET /audit?action={actionType}`
    - Filter audit logs by specific action types (e.g., Created, Updated, Deleted).

- **Filter by Employee ID :**  `GET /audit?empid={empid}`
    - Filter audit logs by employee ID to review actions related to a specific employee.

## Example

Here's an example of an audit entry in the `employees_audit` table:

| empid | fname  | lname  | mailid                 | department | location   | salary   | action  | row_ins_tms        | row_del_tms        |
|-------|--------|--------|------------------------|------------|------------|----------|---------|---------------------|---------------------|
| 101   | john   | doe    | john.doe@example.com   | hr         | new york   | 50000.00 | created  | 2023-09-25 10:15:00 |                 |
| 101   | john   | doe  | john.doe@example.com | it         | san Francisco | 55000.00 | updated  | 2023-09-26 14:30:00 |                 |
| 102   | alice  | johnson| alice.johnson@example.com | finance | chicago    | 60000.00 | created  | 2023-09-27 09:45:00 |                 |
| 102   | alice  | johnson| alice.johnson@example.com | finance | chicago    | 60000.00 | deleted  | 2023-09-27 10:00:00 | 2023-09-27 15:20:00 |

In this example, audit entries are created when employees are added (`Create`) or updated (`Update`). The `row_del_tms` field remains `NULL` until an employee is deleted, at which point it records the deletion or updation timestamp.

## Getting Started

To run this project locally, follow these steps:
1. Make sure you have Java and Maven installed on your machine.
2. Clone [employee-rest](https://github.com/RohanWork/empoyee-rest) repository to your local machine.
3. Configure your MySQL database settings in the application properties.
    - spring.datasource.url=jdbc:mysql://localhost:3306/rest
    - Under rest database create `employees` and `employees_audit` table with the mentioned entities as in [The Main Table - employees](#the-main-table--employees) and [The Audit Table - employees_audit](#the-audit-table--employees_audit).
4. Build and run the Spring Boot application.
5. You can find the local testing files for the endpoints includes [JSON Contracts](https://github.com/RohanWork/empoyee-rest/blob/main/json%20contracts) and [Postman Collection](https://github.com/RohanWork/empoyee-rest/blob/main/employee-rest%20(git%20version).postman_collection.json)
6. You are good to go.

