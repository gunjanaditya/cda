package org.jsp.cda.controller;

import org.jsp.cda.entity.Department;
import org.jsp.cda.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService service;

	@PostMapping
	public ResponseEntity<?> saveDeparment(@RequestBody Department d) {
		return service.saveDepartment(d);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findDepartmentById(@PathVariable int id) {
		return service.findDepartmentById(id);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<?> findDepartmentByName(@PathVariable String name) {
		return service.findDepartmentByName(name);
	}

	@GetMapping
	public ResponseEntity<?> findAllDepartment() {
		return service.findAllDepartment();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDepartmentById(@PathVariable int id) {
		return service.deleteDepartmentById(id);
	}

}
