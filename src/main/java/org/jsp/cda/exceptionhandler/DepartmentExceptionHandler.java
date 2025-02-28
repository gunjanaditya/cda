package org.jsp.cda.exceptionhandler;

import org.jsp.cda.exceptionclasses.NosuchDepartmentFound;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DepartmentExceptionHandler {
	@ExceptionHandler(NosuchDepartmentFound.class)
	public ResponseEntity<?> nosuchDepartmentFoundHandler(NosuchDepartmentFound e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseStructure.builder().status(HttpStatus.BAD_REQUEST.value())
						.message("No department found in DB").body(e.getMessage()).build());
	}
}
