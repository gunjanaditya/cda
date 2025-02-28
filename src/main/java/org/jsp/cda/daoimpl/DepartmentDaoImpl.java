package org.jsp.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
	@Autowired
	private DepartmentRepository repo;
	@Override
	public Department saveDepartment(Department d) {
		 return repo.save(d);
	}
	@Override
	public Optional findDepartmentById(int id) {
		return repo.findById(id);
	}
	@Override
	public void deleteDepartmentById(int id) {
		 repo.deleteById(id);
	}
	@Override
	public List<Department> findAllDepartment() {
		return repo.findAll();
	}
	@Override
	public List<Department> findDepartmentByName(String name) {
		return repo.findByName(name);
	}

}
