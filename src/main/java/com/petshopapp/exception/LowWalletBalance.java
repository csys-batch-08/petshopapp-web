package com.petshopapp.exception;

public class LowWalletBalance extends Exception {

	private static final long serialVersionUID = 1L;
	String message="Low wallet balance please add amount ";
    
	@Override
	public String toString() {
		return message;
	}

}
