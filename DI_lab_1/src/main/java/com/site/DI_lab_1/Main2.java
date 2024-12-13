package com.site.DI_lab_1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main2 {
	public static void main(String[] args) {
		// load the Spring XML configuration
		ApplicationContext context = new

		ClassPathXmlApplicationContext("beansConfig.xml");
		// retrieve the Student bean from the context
		Student student = context.getBean("student",

				Student.class);

		// use the student
		student.getDetails();
		student.getDetailsWithSubject();
	}
}
