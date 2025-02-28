package org.jsp.cda.exceptionhandler;

import org.jsp.cda.exceptionclasses.CourseNotFoundException;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CourseExceptionHandler {
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<?> courseNotFoundExceptionHandler(CourseNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder()
				.status(HttpStatus.BAD_REQUEST.value()).message("No Course Found").body(e.getMessage()).build());
	}
}
