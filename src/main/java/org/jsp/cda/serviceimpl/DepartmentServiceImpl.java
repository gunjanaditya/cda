package org.jsp.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.exceptionclasses.NosuchDepartmentFound;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.jsp.cda.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao dao;

	@Override
	public ResponseEntity<?> saveDepartment(Department d) {
//		 Optional<Department> optional = dao.findDepartmentById(d.getId());
//		 if(optional.isPresent()) {
//			 throw new RuntimeException();
//		 }
		d = dao.saveDepartment(d);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department saved successfully").body(d).build());
	}

	@Override
	public ResponseEntity<?> findDepartmentById(int id) {
		Optional<Department> optional = dao.findDepartmentById(id);
		if (optional.isEmpty()) {
			throw NosuchDepartmentFound.builder().message("No Department Found By this Id").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department Fetched successfully").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> findAllDepartment() {
		List<Department> dept = dao.findAllDepartment();
		if (dept.isEmpty()) {
			throw NosuchDepartmentFound.builder().message("No Department Found In DB").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department Fetched successfully").body(dept).build());
	}

	@Override
	public ResponseEntity<?> deleteDepartmentById(int id) {
		Optional<Department> optional = dao.findDepartmentById(id);
		if (optional.isEmpty()) {
			throw NosuchDepartmentFound.builder().message("No Department Found By this Id...Unable to delete").build();
		}
		dao.deleteDepartmentById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department deleted successfully").body("User Deleted").build());
	}

	@Override
	public ResponseEntity<?> findDepartmentByName(String name) {
		List<Department> dept = dao.findDepartmentByName(name);
		if (dept.isEmpty()) {
			throw NosuchDepartmentFound.builder().message("No Department Found By this name").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Dpartment Found successfully").body(dept).build());
	}

}
