package com.petshopapp.exception;

public class InvalidWalletAmount extends Exception {
	
	String message="Invalid Amount";
    
	@Override
	public String toString() {
		return message;
	}

}
