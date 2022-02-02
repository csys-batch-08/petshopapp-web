package com.petshopapp.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Customers;

@WebServlet("/UpdateAddress")
public class UpdateAddress extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Get customer details
		HttpSession session = request.getSession();
		Customers customer = (Customers) session.getAttribute("customer");
        
		//update values
		customer.setAddress(request.getParameter("address"));
		customer.setCity(request.getParameter("city"));
		customer.setPincode(Integer.parseInt(request.getParameter("pincode")));

		//check address are not equal to none
		if (!customer.getAddress().equals("none") || !customer.getCity().equals("none")) {
			CustomerDAO customerDao = new CustomerDAO();
			customerDao.updateAddressDetails(customer);
			request.setAttribute("message", "Address updated");
		} else {
			request.setAttribute("message", "Address or city can't be none");

		}
		
		// redirect based on message
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("redirect.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
