package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.exception.InvalidWalletAmount;
import com.petshopapp.model.Customers;

@WebServlet("/UpdateWallet")
public class UpdateWallet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// ajax response 
		PrintWriter write = null;
		try {
			write = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Get customer details
		HttpSession session = request.getSession();
		Customers customer = (Customers) session.getAttribute("customer");
		
		
		double wallet = Integer.parseInt(request.getParameter("wallet"));
		boolean message = true;
		
		//check wallet amount grater then or equal 1000
		if (wallet < 1000) {
			try {
				throw new InvalidWalletAmount();
			} catch (InvalidWalletAmount e) {
				write.print(e);
			}
			message = false;
		}
		
		//customer wallet updation
		if (message) {	
			customer.setWallet(customer.getWallet() + wallet);
			CustomerDAO customerDao = new CustomerDAO();
			customerDao.updateCustomerWallet(customer);
			
			//response message
			write.print("Amount Added");
		}
	}
}
