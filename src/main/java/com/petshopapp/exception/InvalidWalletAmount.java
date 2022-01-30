package com.petshopapp.exception;

public class InvalidWalletAmount extends Exception {
	
	private static final long serialVersionUID = 1L;
	    
	@Override
	public String toString() {
		return "Invalid Amount";
	}

}
