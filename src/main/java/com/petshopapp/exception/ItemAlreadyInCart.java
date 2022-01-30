package com.petshopapp.exception;

public class ItemAlreadyInCart extends Exception {
	
	private static final long serialVersionUID = 1L;
    
	@Override
	public String toString() {
		return "This item Already in cart";
	}

}
