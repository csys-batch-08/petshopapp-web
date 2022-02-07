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

@WebServlet("/ValidateEmail")
public class ValidateEmail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	/**
	 * This method is used to for validate customer email
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Get Email Id
		Customers customer = new Customers();
		CustomerDAO customerDao = new CustomerDAO();

		try {
			String email = request.getParameter("email");
			customer.setEmail(email);
			PrintWriter write = response.getWriter();

			if (email.length() > 0) {
				boolean condition = customerDao.validateEmail(customer);
				if (!condition) {
					write.print("Email not available");
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
