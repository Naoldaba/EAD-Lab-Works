package com.itsc.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.itsc.exam.dao.DBConnectionManager;

@SpringBootApplication
@ImportResource("classpath:spring-servlet.xml")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Started"); 
	}

	@Bean
	public DBConnectionManager dBConnection() {
		DBConnectionManager dbContext = new DBConnectionManager();
		
		dbContext.setUrl("jdbc:mysql://localhost:3306/todoapp?useSSL=false&serverTimezone=UTC");
		dbContext.setUsername("root");
		dbContext.setPassword("scutumCentaures23");
		return dbContext;
	}
}