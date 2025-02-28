package org.jsp.cda.service;

import org.jsp.cda.entity.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface DepartmentService {

	ResponseEntity<?> saveDepartment(Department d);

	ResponseEntity<?> findDepartmentById(int id);

	ResponseEntity<?> findAllDepartment();

	ResponseEntity<?> deleteDepartmentById(int id);

	ResponseEntity<?> findDepartmentByName(String name);

}
