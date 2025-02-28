package org.jsp.cda.controller;

import java.time.LocalDateTime;

import org.jsp.cda.entity.User;
import org.jsp.cda.service.UserService;
import org.jsp.cda.utility.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class UserController {

	//@Autowired
	private UserService service;
	
	//Constructor injection----->>>
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	//--------------------------
	//setter Injection
//	public UserService getService() {
//		return service;
//	}
//	@Autowired
//	public void setService(UserService service) {
//		this.service = service;
//	}
	//----------------------------------

	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> findUserByUsernameAndPassword(@RequestBody AuthUser au){
		return service.findUserByUsernameAndPassword(au);
	}





	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User u) {
		return service.saveUser(u);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id) {
		return service.findUserById(id);
	}
	@Operation(summary = "To fetch all users", description = "this API is used to fetch all the users from database")
	
	@GetMapping
	public ResponseEntity<?> findAllUser(){
		return service.findAllUser();
	}
	
	@PostMapping("/verify/otp/{otp}/{uid}")
	public ResponseEntity<?> verifyOTP(@PathVariable int otp, @PathVariable int uid){
		return service.verifyOTP(otp,uid);
	}
	
	@RequestMapping("/simple")
	@Scheduled(fixedDelay = 1000)
	public void simple() {
		System.out.println("hi simple method called at "+ LocalDateTime.now());
	}
	

}
