package com.afci.exception;

public class InvalidBookDataException extends RuntimeException {
	
	 private static final long serialVersionUID = 1L;

	 public InvalidBookDataException(String message) {
	        super(message);
	    }

}
