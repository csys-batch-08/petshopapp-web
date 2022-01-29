package com.petshopapp.exception;

public class QuantityNotAvalilable extends Exception {
	
	private static final long serialVersionUID = 1L;
	String message="Quantity Not Available";
   
	@Override
	public String toString() {
		return message;
	}

}
