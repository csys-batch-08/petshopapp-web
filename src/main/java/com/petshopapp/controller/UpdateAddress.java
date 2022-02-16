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

@WebServlet("/UpdateAddress")
public class UpdateAddress extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	
	/**
	 * This method is used to update customer address and redirect to redirect.jsp 
	 * then redirect to myprofile.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
		// Get customer details
		HttpSession session = request.getSession();
		Customers customer = (Customers) session.getAttribute("customer");
		//update values
		customer.setAddress(request.getParameter("address"));
		customer.setCity(request.getParameter("city"));
		customer.setPincode(Integer.parseInt(request.getParameter("pincode")));
		//check address are not equal to none
		if (!customer.getAddress().equals("none") || !customer.getCity().equals("none")) {
			CustomerDaoImpl customerDao = new CustomerDaoImpl();
			customerDao.updateAddressDetails(customer);
			request.setAttribute("message", "Address updated");
		} else {
			request.setAttribute("message", "Address or city can't be none");
		}
		// redirect based on message
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("redirect.jsp");
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException|NullPointerException|NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}

}
