package com.repository;

import java.util.List;

import com.dto.EmployeeResponseDTO;
import com.dto.EmployeeSearchDTO;

public interface EmployeeSearchCustom {
	
	List<EmployeeResponseDTO> getAllEmployeeDTO(EmployeeSearchDTO dto);

}
