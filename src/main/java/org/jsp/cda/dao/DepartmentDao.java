package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


public interface DepartmentDao {

	Department saveDepartment(Department d);

	Optional<Department> findDepartmentById(int id);

	void deleteDepartmentById(int id);

	List<Department> findAllDepartment();

	List<Department> findDepartmentByName(String name);

}
