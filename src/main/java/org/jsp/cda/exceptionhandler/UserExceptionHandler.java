package org.jsp.cda.exceptionhandler;

import org.jsp.cda.exceptionclasses.InvalidCredentialsFound;
import org.jsp.cda.exceptionclasses.UserNotFoundException;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler(InvalidCredentialsFound.class)
	public ResponseEntity<?> invalidCredentialsFoundHandler(InvalidCredentialsFound e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseStructure.builder().status(HttpStatus.BAD_REQUEST.value())
						.message("Invalid username or password").body(e.getMessage()).build());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder()
				.status(HttpStatus.BAD_REQUEST.value()).message("User not found...").body(e.getMessage()).build());
	}
}
