package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EmployeeCreateDTO;
import com.dto.EmployeeResponseDTO;
import com.dto.EmployeeSearchDTO;
import com.entity.Department;
import com.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:8081")
public class EmployeeController {

    
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}

	@PostMapping("/search")
    public List<EmployeeResponseDTO> search(@RequestBody EmployeeSearchDTO dto) {
        return service.search(dto);
    }
    
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        return service.getAllEmployees();
    }
    
    @GetMapping("/departments")
    public List<Department> getDepartmentsLookup() {
        return service.getAllDepartments();
    }
    
    @PostMapping("/new")
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeCreateDTO dto) {
        return service.createEmployee(dto);
    }
    
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Integer id, @RequestBody EmployeeCreateDTO dto) {
        return service.updateEmployee(id, dto);
    }
    
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Integer id) {
        return service.getEmployee(id);
    }
    
    
}