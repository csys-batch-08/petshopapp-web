package com.petshopapp.exception;

public class ItemAlreadyInCart extends Exception {
	
	private static final long serialVersionUID = 1L;
	String message="This item Already in cart";
    
	@Override
	public String toString() {
		return message;
	}

}
