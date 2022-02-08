package com.petshopapp.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.AdminDAO;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Admin;
import com.petshopapp.model.Customers;

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	/**
	 * This method is used for login validation and and redirection based on
	 * validation
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// User details
		String userName = request.getParameter("usernameinput");
		String passwowrd = request.getParameter("passwordinput");
		HttpSession session = request.getSession();
		Customers customerDetails = new Customers();
		CustomerDAO customerDao = new CustomerDAO();
		String filename = "userdata.ser";

		customerDetails.setUserName(userName);
		customerDetails.setPassword(passwowrd);
		String firstName = customerDao.customerLoginValidation(customerDetails);

		FileOutputStream file = null;
		ObjectOutputStream out = null;
		// login validation
		if (firstName != null) {
			// customer login
			if (firstName.charAt(0) == 'C') {
				customerDetails = customerDao.customerDetails(userName);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home");
				try {
					file = new FileOutputStream(filename);
					out = new ObjectOutputStream(file);
					// Method for serialization of object
					out.writeObject(customerDetails);

					session.setAttribute("customer", customerDetails);
					requestDispatcher.forward(request, response);
				} catch (ServletException | IOException | NullPointerException e) {
					Logger.printStackTrace(e);
					Logger.runTimeException(e.getMessage());
				} finally {
					try {
						if (out != null) {
							out.close();
						}
						if (file != null) {
							file.close();
						}
					} catch (IOException e) {
						Logger.printStackTrace(e);
						Logger.runTimeException(e.getMessage());
					}
				}
			}
			// Admin login
			else {
				Admin admin;
				AdminDAO adminDao = new AdminDAO();
				admin = adminDao.show(userName);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminHome");
				try {
					file = new FileOutputStream(filename);
					out = new ObjectOutputStream(file);
					// Method for serialization of object
					out.writeObject(admin);
					session.setAttribute("Admin", admin);
					requestDispatcher.forward(request, response);
				} catch (ServletException | IOException |NullPointerException e) {
					Logger.printStackTrace(e);
					Logger.runTimeException(e.getMessage());
				} finally {
					try {
						if (out != null) {
							out.close();
						}
						if (file != null) {
							file.close();
						}
					} catch (IOException e) {
						Logger.printStackTrace(e);
						Logger.runTimeException(e.getMessage());
					}
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
				Logger.printStackTrace(e);
				Logger.runTimeException(e.getMessage());
			}
		}
	}
}