package com.dto;

import com.entity.Employee;

public class EmployeeResponseDTO {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private Double salary;
	private Integer departmentID;
	private String city;
	private String countryId;

	
	public EmployeeResponseDTO(Integer id, String firstName, String lastName, Double salary, Integer departmentID, String city,
			String countryId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.departmentID= departmentID;
		this.city = city;
		this.countryId = countryId;
	}
	
	public EmployeeResponseDTO(String firstName, Double salary, Integer departmentId) {
        this.firstName = firstName;
        this.salary = salary;
        this.departmentID = departmentId;
    }
	
	public EmployeeResponseDTO(Employee emp) {
		this.id = emp.getEmployeeId()
;		this.firstName = emp.getFirstName();
		this.lastName = emp.getLastName();
		this.salary = emp.getSalary();
		this.departmentID= emp.getDepartment().getId();
		this.city =emp.getDepartment().getLocation().getCity();
		this.countryId = emp.getDepartment().getLocation().getCountryId();
		
	}
	
	public EmployeeResponseDTO() {
	}
	
	// Getters e setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getSalary() {
		return salary;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
}