package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;

@WebServlet("/ValidateUsername")
public class ValidateUsername extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used to for validate customer username
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Get user name details
		Customers customer = new Customers();
		CustomerDAO customerDao = new CustomerDAO();
		try {
			PrintWriter write = response.getWriter();
			String userName = request.getParameter("userName");
			customer.setUserName(userName);
			boolean condition = customerDao.validateUsername(customer);
		if (userName.length() > 0) {
			if (!condition) {
				write.print("User Name Not available");
			} else {
				write.print("Available");
			}
		}
		} catch (IOException|NullPointerException|NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
