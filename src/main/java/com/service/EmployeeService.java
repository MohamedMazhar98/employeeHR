package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.EmployeeCreateDTO;
import com.dto.EmployeeResponseDTO;
import com.dto.EmployeeSearchDTO;
import com.entity.Department;
import com.entity.Employee;
import com.repository.DepartmentRepository;
import com.repository.EmployeeRepository;

@Service
public class EmployeeService {

	
	private EmployeeRepository repository;
	
	private DepartmentRepository deptRepository;
	
	public EmployeeService(EmployeeRepository repository, DepartmentRepository deptRepository) {
		super();
		this.repository = repository;
		this.deptRepository = deptRepository;
	}
	
	public List<EmployeeResponseDTO> search(EmployeeSearchDTO dto) {
        // Chiamata diretta al metodo creato nell'EntityManager
        return repository.getAllEmployeeDTO(dto);
    }

	public List<EmployeeResponseDTO> getAllEmployees() {

	    List<Employee> employees = repository.findAll();

	    return employees.stream().map(e -> {

	        Integer deptId = (e.getDepartment() != null) 
	                ? e.getDepartment().getId() 
	                : null;

	        String city = (e.getDepartment() != null 
	                        && e.getDepartment().getLocation() != null)
	                ? e.getDepartment().getLocation().getCity()
	                : null;

	        return new EmployeeResponseDTO(
	        		e.getEmployeeId(),
	                e.getFirstName(),
	                e.getLastName(),
	                e.getSalary(),
	                deptId,
	                city,
	                null
	        );

	    }).collect(Collectors.toList());
	}
	
	public List<Department> getAllDepartments() {
	    return deptRepository.findAll();
	}
	
	private EmployeeResponseDTO mapToResponseDTO(Employee employee) {

	    EmployeeResponseDTO dto = new EmployeeResponseDTO();
	    dto.setFirstName(employee.getFirstName());
	    dto.setLastName(employee.getLastName());
	    dto.setSalary(employee.getSalary());

	    if (employee.getDepartment() != null) {
	        dto.setDepartmentID(employee.getDepartment().getId());
	    }

	    return dto;
	}
	
	public EmployeeResponseDTO createEmployee(EmployeeCreateDTO dto) {

	    Employee employee = new Employee();
	    employee.setFirstName(dto.getFirstName());
	    employee.setLastName(dto.getLastName());
	    employee.setSalary(dto.getSalary());
	    
	    employee.setEmail(dto.getEmail());         
	    
	    if (dto.getHireDate() != null) {
	        employee.setHireDate(dto.getHireDate());
	    } else {
	        employee.setHireDate(new java.util.Date()); 
	    }
	    
	    employee.setJobId(dto.getJobId() != null ? dto.getJobId() : "IT_PROG");

	    
	    Department department = deptRepository
	            .findById(dto.getDepartmentId())
	            .orElseThrow(() -> new RuntimeException("Department not found"));

	    employee.setDepartment(department);

	    repository.save(employee);

	    return mapToResponseDTO(employee);
	}
	
	public EmployeeResponseDTO updateEmployee(Integer id, EmployeeCreateDTO dto) {
	    
	    Employee employee = repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

	    
	    employee.setFirstName(dto.getFirstName());
	    employee.setLastName(dto.getLastName());
	    employee.setSalary(dto.getSalary());
	    
	    
	    employee.setEmail(dto.getEmail()); 
	    employee.setJobId(dto.getJobId() != null ? dto.getJobId() : employee.getJobId());

	    
	    if (dto.getHireDate() != null) {
	        employee.setHireDate(dto.getHireDate());
	    }

	    
	    if (dto.getDepartmentId() != null) {
	        Department department = deptRepository.findById(dto.getDepartmentId())
	                .orElseThrow(() -> new RuntimeException("Department not found"));
	        employee.setDepartment(department);
	    }

	    
	    Employee updatedEmployee = repository.save(employee);

	    return mapToResponseDTO(updatedEmployee);
	}
	
	
	public EmployeeResponseDTO getEmployee(Integer id)
	{
		 Employee employee = repository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
		 return mapToResponseDTO(employee);
	}
}
