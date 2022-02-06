package com.petshopapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.model.Customers;

@WebServlet("/AddItem")
public class AddItem extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Session for get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");

		// Check whether customer address present or not

		// If it address is none send redirect to message page
		if (customerDetails.getAddress().equals("none")) {

			// message content
			request.setAttribute("message", "Please add address brefore add pet");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("redirect.jsp?");
			try {
				requestDispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

			// if address present then send redirect to add item page
		} else {
			try {
				response.sendRedirect("additem.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
