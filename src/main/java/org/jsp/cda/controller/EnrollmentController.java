package org.jsp.cda.controller;

import org.jsp.cda.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService service;

	@PostMapping("/{sid}/{cid}")
	public ResponseEntity<?> saveEnrollment(@PathVariable int sid, @PathVariable int cid) {
		return service.saveEnrollment(sid, cid);
	}

	@GetMapping
	public ResponseEntity<?> findAllEnrollment() {
		return service.findAllEnrollment();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findEnrollmentById(@PathVariable int id) {
		return service.findEnrollmentById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEnrollmentById(@PathVariable int id) {
		return service.deleteEnrollmentById(id);
	}

	// findAll
	// findById
	// deleteById

	// find enrollment by course id(one course can have many enrollments)
	@GetMapping("/courses/{cid}")
	public ResponseEntity<?> findEnrollmentByCourseId(@PathVariable int cid) {
		return service.findEnrollmentByCourseId(cid);
	}

//	find all enrollments by student id (one student can enrolled to many courses)
	@GetMapping("/students/{sid}")
	public ResponseEntity<?> findEnrollmentsByStudentId(@PathVariable int sid) {
		return service.findEnrollmentsByStudentId(sid);
	}

	// assign dept and faculty to course.
	//
}
