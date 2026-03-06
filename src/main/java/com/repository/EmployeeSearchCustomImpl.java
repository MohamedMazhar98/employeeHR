package com.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.dto.EmployeeResponseDTO;
import com.dto.EmployeeSearchDTO;

@Repository
public class EmployeeSearchCustomImpl implements EmployeeSearchCustom{
	@PersistenceContext
	EntityManager em;
	
	public List<EmployeeResponseDTO> getAllEmployeeDTO(EmployeeSearchDTO dto) {
        
        StringBuilder jpql = new StringBuilder(
            "SELECT NEW com.dto.EmployeeResponseDTO(e) " +
            "FROM Employee e WHERE 1=1 "
        );

        
        Map<String, Object> parameters = new HashMap<>();

        
        if (dto.getDepartmentName() != null) {
            jpql.append("AND e.department.id = :deptName ");
            
            Long deptIdLong = Long.valueOf(dto.getDepartmentName());
            parameters.put("deptName", deptIdLong);
        }

        if (isNotEmpty(dto.getEmployeeName())) {
            jpql.append("AND LOWER(e.firstName) LIKE LOWER(:empName) ");
            parameters.put("empName", "%" + dto.getEmployeeName() + "%");
        }

        if (dto.getMinSalary() != null) {
            jpql.append("AND e.salary >= :minSalary ");
            parameters.put("minSalary", dto.getMinSalary());
        }

        if (dto.getMaxSalary() != null) {
            jpql.append("AND e.salary <= :maxSalary ");
            parameters.put("maxSalary", dto.getMaxSalary());
        }

        
        TypedQuery<EmployeeResponseDTO> query = em.createQuery(jpql.toString(), EmployeeResponseDTO.class);

        
        parameters.forEach(query::setParameter);

        return query.getResultList();
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
