package com.petshopapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.AdminDAO;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Admin;
import com.petshopapp.model.Customers;

@WebServlet("/login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// User details
		String userName = request.getParameter("usernameinput");
		String passwowrd = request.getParameter("passwordinput");

		HttpSession session = request.getSession();

		Customers customerDetails = new Customers();
		CustomerDAO customerDao = new CustomerDAO();
		customerDetails.setUserName(userName);
		customerDetails.setPassword(passwowrd);

		String firstName = customerDao.customerLoginValidation(customerDetails);

		// login validation
		if (firstName != null) {

			// customer login
			if (firstName.charAt(0) == 'C') {
				customerDetails = customerDao.customerDetails(userName);
				session.setAttribute("customer", customerDetails);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home");
				try {
					requestDispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}

			}

			// Admin login
			else {
				Admin admin;
				AdminDAO adminDao = new AdminDAO();
				admin = adminDao.show(userName);
				session.setAttribute("Admin", admin);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminHome");
				try {
					requestDispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}

			}
		}
		// If user name password does not match send redirect page
		else {

			request.setAttribute("message", "Invalid username or password");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("redirect.jsp");
			try {
				requestDispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		}

	}
}