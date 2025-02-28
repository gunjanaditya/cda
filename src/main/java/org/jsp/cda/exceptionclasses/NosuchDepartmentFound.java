package org.jsp.cda.exceptionclasses;

import lombok.Builder;

@Builder
public class NosuchDepartmentFound extends RuntimeException{
	private String message;
	@Override
	public String getMessage() {
		return this.message;
	}
}
