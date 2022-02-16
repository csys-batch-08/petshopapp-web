package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDaoImpl;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;

@WebServlet("/updateProfileImage")
public class UpdateProfileImage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used to update customer profile image 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		//Get customer details
		HttpSession session = request.getSession();
		Customers customer = (Customers) session.getAttribute("customer");
		try {
		//update customer image
		customer.setImage(request.getParameter("image"));
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		customerDao.updateCustomerProfileImage(customer);
		//ajax response message
		PrintWriter write=response.getWriter();
			write.print("Profile picture updated");
		} catch (IOException|NullPointerException|NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
