package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CartItemsDaoImpl;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;

@WebServlet("/RemoveCartItems")
public class RemoveCatItems extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used to remove all cart items
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Get customer details		
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		// Get cart list
		CartItemsDaoImpl cartItemsDao = new CartItemsDaoImpl();
		List<CartItems> cartList = cartItemsDao.showAllCartItems(customerDetails);
		// remove all cart List
		for (CartItems cartItems : cartList) {
			cartItemsDao.deleteCartItem(cartItems.getItemId());
		}
		//ajax response message
		try {
			PrintWriter write = response.getWriter();
			write.print("All Cart Items Are Deleted");
		} catch (IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
