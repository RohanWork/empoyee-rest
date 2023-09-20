package com.rest.rohan;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
//@ComponentScan(basePackages = "com.rest.rohan")
public class RestApiApplication {
	
//	@Autowired
//    private DataSource dataSource;


	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

//	@PostConstruct
//    public void checkDbConnection() {
//        try (Connection connection = dataSource.getConnection()) {
//            // The connection was successfully established.
//            System.out.println("Database connection successful.");
//        } catch (SQLException e) {
//            // Handle any exceptions here.
//            e.printStackTrace();
//        }
//    }
}
