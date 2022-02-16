package com.petshopapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDaoImpl;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	/**
	 * This method is used to update customer profile and redirect to redirect.jsp 
	 * then redirect to myprofile.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		try {
			boolean flag = true;
			Customers customer = new Customers();
			CustomerDaoImpl customerDao = new CustomerDaoImpl();
			customer.setUserName(request.getParameter("username"));
			customer.setEmail(request.getParameter("email"));
			if ((!customerDao.validateUsername(customer))
					&& (!customer.getUserName().equals(customerDetails.getUserName()))) {
				flag = false;
			}
			if ((!customerDao.validateUsername(customer))
					&& (!customer.getEmail().equals(customerDetails.getEmail()))) {
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
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException | NumberFormatException | NullPointerException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
