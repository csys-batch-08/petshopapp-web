package com.petshopapp.exception;

public class InvalidWalletAmount extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	String message="Invalid Amount";
    
	@Override
	public String toString() {
		return message;
	}

}
