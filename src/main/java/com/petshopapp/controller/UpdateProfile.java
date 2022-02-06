package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Customers;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");

		boolean flag = true;
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Customers customer = new Customers();
		CustomerDAO customerDao = new CustomerDAO();

		customer.setUserName(request.getParameter("username"));
		customer.setEmail(request.getParameter("email"));

		if ((!customerDao.validateUsername(customer))
				&& (!customer.getUserName().equals(customerDetails.getUserName()))) {
			flag = false;
		}

		if ((!customerDao.validateUsername(customer)) && (!customer.getEmail().equals(customerDetails.getEmail()))) {
			flag = false;
		}

		if (flag) {
			customer.setCustomerId(customerDetails.getCustomerId());
			customer.setFirstName(request.getParameter("firstname"));
			customer.setLastName(request.getParameter("lastname"));
			customer.setPassword(request.getParameter("password"));
			customer.setNumber(Long.parseLong(request.getParameter("number")));
			customer.setGender(request.getParameter("gender"));

			customerDao.updateCustomerDetails(customer);
		}
		request.setAttribute("message", "Profile updated successfully");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("redirect.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
