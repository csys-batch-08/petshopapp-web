package com.petshopapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CartItemsDAO;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;

@WebServlet("/MyCart")
public class MyCart extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		// Get customer details
		HttpSession session = request.getSession();
		Customers customerDetails= (Customers) session.getAttribute("customer");
		
		// Get cart list
		CartItemsDAO cartItemDao = new CartItemsDAO();		
		List<CartItems> cartList = cartItemDao.showAllCartItems(customerDetails);
		
		// Send cart list to mycart.jsp
		request.setAttribute("cartList", cartList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("mycart.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}
}
