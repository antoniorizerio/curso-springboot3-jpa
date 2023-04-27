package com.educandoweb.course.entities;

import java.util.Set;

public class Person {

	private Long id;
	private String name;
	private Person boss;
	private Set employees;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Person getBoss() {
		return boss;
	}
	public void setBoss(Person boss) {
		this.boss = boss;
	}
	
	public Set getEmployees() {
		return employees;
	}
	public void setEmployees(Set employees) {
		this.employees = employees;
	}
}
