package com.afci.projet_spring_book.exception;

public class DuplicateBookException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	 public DuplicateBookException(String message) {
	        super(message);
	    }
}
