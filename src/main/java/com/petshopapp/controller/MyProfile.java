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
import com.petshopapp.daoimpl.CustomerDaoImpl;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;

@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	/**
	 * This method is used for get customer details pet list and redirect to
	 * myprofile.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		// Update customer details
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		customerDetails = customerDao.customerDetails(customerDetails.getCustomerId());
		String filename = "userdata.ser";
		FileOutputStream file=null;
		ObjectOutputStream out=null;
		try {
			file = new FileOutputStream(filename);
			out = new ObjectOutputStream(file);
			// Method for serialization of object
			out.writeObject(customerDetails);
			out.close();
			file.close();
			session.setAttribute("customer", customerDetails);
			// redirect to my profile
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("myprofile.jsp");
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException|NullPointerException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}finally {
			try {
				if(out!=null) {
					out.close();
				}
				if(file!=null) {
					file.close();
				}
			}catch (Exception e) {
				Logger.printStackTrace(e);
				Logger.runTimeException(e.getMessage());
			}
			
		}
	}
}
