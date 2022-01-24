package com.petshopapp.exception;

public class ItemAlreadyInCart extends Exception {
	
	String message="This item Already in cart";
    
	@Override
	public String toString() {
		return message;
	}

}
