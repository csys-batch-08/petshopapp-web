package com.petshopapp.dao;

import java.util.List;

import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;

public interface CartItemsInterface {
   
	   
	   public void insertCartItem(CartItems cartit);
	   
	   public void updateCartItemQuantity(int itemId, int qty);
	   
	   public void deleteCartItem(int itemId);
	   
	   public List<CartItems> showAllCartItems(Customers customer);
	   
	   public CartItems showCartItem(int itemId);
			
}
