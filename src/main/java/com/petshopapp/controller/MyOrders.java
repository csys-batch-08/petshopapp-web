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

import com.petshopapp.daoimpl.OrderItemsDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;

@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used for get my order list and redirect to myorder.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		//Get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		//Get order details
		OrderItemsDAO orderItemDao = new OrderItemsDAO();
		List<OrderItems> orderItemList = orderItemDao.showMyOrdersItemsList(customerDetails);
		// Send orders details to myorders.jsp
		request.setAttribute("orderItemsList", orderItemList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("myorders.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}

	}

}
