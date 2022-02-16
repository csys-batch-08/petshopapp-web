package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.CartItemsDaoImpl;
import com.petshopapp.logger.Logger;

@WebServlet("/RemoveCartItem")
public class RemoveCatItem extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used to remove cart item
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {    
		try {
		// cart item id for remove item
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		CartItemsDaoImpl cartItemsDao = new CartItemsDaoImpl();
		cartItemsDao.deleteCartItem(itemId);
		//ajax response message
			PrintWriter writer= response.getWriter();
			writer.print("Cart Item Removed");
		} catch (IOException |NullPointerException|NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
