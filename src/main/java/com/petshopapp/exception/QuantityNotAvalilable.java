package com.petshopapp.exception;

public class QuantityNotAvalilable extends Exception {
	
	String message="Quantity Not Available";
   
	@Override
	public String toString() {
		return message;
	}

}
