package com.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.dto.EmployeeSearchDTO;
import com.entity.Employee;

public class EmployeeSearch {
	public static Specification<Employee> filter(EmployeeSearchDTO dto) {

	    return (root, query, cb) -> {
	        List<Predicate> predicates = new ArrayList<>();

	        // Filtri sul departmentId
	        if (dto.getDepartmentName() != null) {
	            predicates.add(cb.equal(root.get("departmentId"), dto.getDepartmentName()));
	        }

	        // Filtri sul nome del dipendente
	        if (isNotEmpty(dto.getEmployeeName())) {
	            predicates.add(
	                cb.like(cb.lower(root.get("firstName")), "%" + dto.getEmployeeName().toLowerCase() + "%")
	            );
	        }

	        // Filtri sul salario
	        if (dto.getMinSalary() != null) {
	            predicates.add(cb.greaterThanOrEqualTo(root.get("salary"), dto.getMinSalary()));
	        }

	        if (dto.getMaxSalary() != null) {
	            predicates.add(cb.lessThanOrEqualTo(root.get("salary"), dto.getMaxSalary()));
	        }

	        return cb.and(predicates.toArray(new Predicate[0]));
	    };
	}
	
	private static boolean isNotEmpty(String value) {
	    return value != null && !value.trim().isEmpty();
	}

	
}