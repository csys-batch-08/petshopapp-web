
package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Customers;

@WebServlet("/register")
public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		//Get register details
		CustomerDAO customerDao = new CustomerDAO();
		Customers customer = new Customers();
		customer.setFirstName(request.getParameter("username"));
		customer.setLastName(request.getParameter("lastname"));
		customer.setGender(request.getParameter("gender"));
		customer.setUserName(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		customer.setEmail(request.getParameter("email"));
		customer.setNumber(Long.parseLong(request.getParameter("mobile")));
		boolean register = customerDao.insertNewCustomer(customer);
        
		// registration completed message
		if (register) {
			request.setAttribute("message", "registration completed successfully login now");
			
		} 
		// registration not completed message
		else {
			request.setAttribute("message", "Something went to wrong please try again");
		}
		
		// redirect based on result
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("redirect.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}