package org.jsp.cda.controller;

import org.jsp.cda.entity.Administrator;
import org.jsp.cda.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {
	@Autowired
	private AdministratorService service;
	
	@PostMapping("/{uid}")
	public ResponseEntity<?> saveAdministrator(@RequestBody Administrator administrator,@PathVariable int uid){
		return service.saveAdmistrator(administrator,uid);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllAdministrator(){
		return service.findAllAdministrator();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findAdministratorByID(@PathVariable int id){
		return service.findAdministratorById(id);
	}
	
	@PatchMapping("/aid/did")
	public ResponseEntity<?> assignDeprtmentToAdministrator(@PathVariable int aid,@PathVariable int did){
		return service.assignDeprtmentToAdministrator(aid,did);
	}
}
