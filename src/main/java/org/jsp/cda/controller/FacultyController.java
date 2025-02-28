package org.jsp.cda.controller;

import org.jsp.cda.entity.Faculty;
import org.jsp.cda.entity.Student;
import org.jsp.cda.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
	@Autowired
	private FacultyService service;
	
	
	@PostMapping("/{uid}")
	public ResponseEntity<?> saveFaculty(@RequestBody Faculty faculty, @PathVariable int uid){
		return service.saveFaculty(faculty , uid);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findFacultyById(@PathVariable int id){
		return service.findFacultyById(id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllFaculty(){
		return service.findAllFaculty();
	}
	
	@PatchMapping("/{fid}/{did}")
	public ResponseEntity<?> assignDepartmentToFaculty(@PathVariable int fid, @PathVariable int did){
		return service.assignDepartmentToFaculty(fid,did);
	}
	
}
