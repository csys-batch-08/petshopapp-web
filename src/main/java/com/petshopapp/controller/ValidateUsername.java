package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Customers;

@WebServlet("/ValidateUsername")
public class ValidateUsername extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Get user name details
		Customers customer = new Customers();
		CustomerDAO customerDao = new CustomerDAO();
		String userName = request.getParameter("userName");
		customer.setUserName(userName);
		boolean condition = customerDao.validateUsername(customer);

		// ajax response
		PrintWriter write = null;
		try {
			write = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (userName.length() > 0) {
			if (!condition) {
				write.print("User Name Not available");
			} else {
				write.print("Available");
			}
		}

	}
}
