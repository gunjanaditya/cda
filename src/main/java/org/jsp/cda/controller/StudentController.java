package org.jsp.cda.controller;

import org.jsp.cda.entity.Student;
import org.jsp.cda.service.StudentService;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService service;
	
	@PostMapping("/{uid}")
	public ResponseEntity<?> saveStudent(@RequestBody Student student, @PathVariable int uid){
		return service.saveStudent(student , uid);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findStudentById(@PathVariable int id){
		return service.findStudentById(id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllStudent(){
		return service.findAll();
	}
	
	@PatchMapping("/{sid}/{did}")
	public ResponseEntity<?> assignDepartmentToStudent(@PathVariable int sid, @PathVariable int did){
		return service.assignDepartmentToStudent(sid,did);
	}
	
	@PostMapping("/photo/{sid}")
	public ResponseEntity<?> uploadPhoto(@RequestParam MultipartFile file ,@PathVariable int sid){
		return service.uploadPhoto(file,sid);
	}
	
	
}
