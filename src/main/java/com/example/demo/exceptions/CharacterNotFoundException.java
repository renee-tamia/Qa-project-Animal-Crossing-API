package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No character exists with the provided information")
public class CharacterNotFoundException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public CharacterNotFoundException() {
		super();
		
	}

	public CharacterNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CharacterNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CharacterNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CharacterNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
}

	
	
}
