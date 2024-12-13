package com.site.ConstructorBasedDI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
//load the Spring XML configuration
		ApplicationContext context = new ClassPathXmlApplicationContext("beansConfig.xml");
// retrieve the Student bean from the context
		Student student = context.getBean("student", Student.class);

// use the student objecct
		System.out.println("Full Name: " + student.getFullName());
		student.getDetails();
	}
}