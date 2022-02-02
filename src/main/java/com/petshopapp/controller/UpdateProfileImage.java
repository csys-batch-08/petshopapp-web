package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Customers;

@WebServlet("/updateProfileImage")
public class UpdateProfileImage extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		//Get customer details
		HttpSession session = request.getSession();
		Customers customer = (Customers) session.getAttribute("customer");
		
		//update customer image
		customer.setImage(request.getParameter("image"));
		CustomerDAO customerDao = new CustomerDAO();
		customerDao.updateCustomerProfileImage(customer);
		
		//ajax response message
		PrintWriter write;
		try {
			write = response.getWriter();
			write.print("Profile picture updated");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
