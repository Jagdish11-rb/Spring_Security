package com.practice.SpringSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@EnableWebSecurity(debug = true) // For track web security in debug level
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class SpringSecurityApplication {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/Spring_Security_SS?autoReconnect=true&useSSL=false";
		String username = "postgres";
		String password = "Jagdish@11";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Successfully connected to PgSQL.");
		} catch (SQLException e) {
			System.out.println("Failed to connect PgSQL.");
			e.printStackTrace();
		}

		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
