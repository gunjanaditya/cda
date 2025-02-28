package org.jsp.cda.exceptionclasses;

import lombok.Builder;

@Builder
public class InvalidCredentialsFound extends RuntimeException{
	private String message;
	@Override
	public String getMessage() {
		return this.message;
	}
}
