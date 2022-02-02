package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Customers;

@WebServlet("/ValidateEmail")
public class ValidateEmail extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Get Email Id
		Customers customer = new Customers();
		CustomerDAO customerDao = new CustomerDAO();
		String email = request.getParameter("email");
		customer.setEmail(email);
		
		//ajax response
		PrintWriter write = null;
		try {
			write = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (email.length() > 0) {
			boolean condition = customerDao.validateEmail(customer);
			if (!condition) {
				write.print("Email not available");
			} else {
				write.print("Available");
			}
		}

	}
}
