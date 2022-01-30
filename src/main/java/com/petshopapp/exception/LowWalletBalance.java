package com.petshopapp.exception;

public class LowWalletBalance extends Exception {

	private static final long serialVersionUID = 1L;

    
	@Override
	public String toString() {
		return"Low wallet balance please add amount";
	}

}
