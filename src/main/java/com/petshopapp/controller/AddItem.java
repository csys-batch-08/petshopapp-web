package com.petshopapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;

@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	/**
	 * This method is used to ensure customer address present or not. if address
	 * present it redirect to additem.jsp or its redirect to myprofile.jsp for to
	 * update customer address, redirection done by redirect.jsp based on request
	 * message
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");

		// If address is equals to none send redirect to message page
		if (customerDetails.getAddress().equals("none")) {
			request.setAttribute("message", "Please add address brefore add pet");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("redirect.jsp?");
			try {
				requestDispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				Logger.printStackTrace(e);
				Logger.runTimeException(e.getMessage());
			}
			// if address present then send redirect to add item page
		} else {
			try {
				response.sendRedirect("additem.jsp");
			} catch (IOException e) {
				Logger.printStackTrace(e);
				Logger.runTimeException(e.getMessage());
			}
		}
	}
}
