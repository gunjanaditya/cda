package org.jsp.cda.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	List<Department> findByName(String name);

}
