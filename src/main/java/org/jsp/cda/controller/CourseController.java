package org.jsp.cda.controller;

import org.jsp.cda.entity.Course;
import org.jsp.cda.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	private CourseService service;

	@PostMapping
	public ResponseEntity<?> saveCourse(@RequestBody Course c) {
		return service.saveCourse(c);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> fetchCourseById(@PathVariable int id) {
		return service.fetchCourseById(id);
	}

	@GetMapping
	public ResponseEntity<?> fetchAllCourse() {
		return service.fetchAllCourse();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourseByID(@PathVariable int id) {
		return service.deleteCourseById(id);
	}

	@PatchMapping("/addfaculty/{cid}/{fid}")
	public ResponseEntity<?> assignFacultyToCourse(@PathVariable(name = "cid") int id, @PathVariable int fid) {
		return service.assignFacultyToCourse(id, fid);
	}

	@PatchMapping("/adddept/{cid}/{deptid}")
	public ResponseEntity<?> assignDepartmentToCourse(@PathVariable(name = "cid") int id, @PathVariable int deptid) {
		return service.assignDepartmentToCourse(id, deptid);
	}
	// assign faculty to course
	
}
