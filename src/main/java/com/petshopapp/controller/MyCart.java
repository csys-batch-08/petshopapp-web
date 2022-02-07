package com.petshopapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CartItemsDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;

@WebServlet("/MyCart")
public class MyCart extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used for get cart list and redirect to mycart.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {	
		HttpSession session = request.getSession();// Get customer details
		Customers customerDetails= (Customers) session.getAttribute("customer");
		CartItemsDAO cartItemDao = new CartItemsDAO();		
		List<CartItems> cartList = cartItemDao.showAllCartItems(customerDetails);// Get cart list	
		request.setAttribute("cartList", cartList);// Send cart list to mycart.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("mycart.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}

	}
}
