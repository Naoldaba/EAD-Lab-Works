package com.site.DI_lab_1;

import java.util.List;

public class Student {
	private String firstName;
	private String lastName;

	// setter & getter
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	private Address address;

	public void setAddress(Address address) {
		this.address = address;

	}

	public void getDetails() {
		System.out.printf("%s lives in %s city in %s", getFullName(), address.getCity(), address.getCountry());
	}

	private List<Subject> subjects; // collection of subjects
	// setter & getter

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void getDetailsWithSubject() {
		System.out.println("Name: " + getFullName());
		System.out.println("Subjects: ");
		for (Subject subject : subjects) {
			System.out.println("- " + subject.getSubjectName());
		}

	}
}