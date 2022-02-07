package com.petshopapp.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;


@WebServlet("/UserList")
public class UserList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used to for get customer list and redirect to userlist.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Petdao used for get petlist
		CustomerDAO customerDao=new CustomerDAO();
		List<Customers> customerList = customerDao.customersList();	
		//send pet list through request object
		request.setAttribute("customerList", customerList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("userlist.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}

	}

}
