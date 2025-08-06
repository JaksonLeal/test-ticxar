package com.ticxar.test_back.exception;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginException() {
		super("No se pudo loguear");
		// TODO Auto-generated constructor stub
	}

	public LoginException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
