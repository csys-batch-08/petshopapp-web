package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.CartItemsDAO;

@WebServlet("/RemoveCartItem")
public class RemoveCatItem extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
		// cart item id for remove item
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		CartItemsDAO cartItemsDao = new CartItemsDAO();
		cartItemsDao.deleteCartItem(itemId);
		
		//ajax response message
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print("Cart Item Removed");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
