package com.petshopapp.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.PetDaoImpl;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/Home")
public class Home extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	/**
	 * This method is used to get pet details and send to edit.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {	
		HttpSession session = request.getSession();// session for get customer details
		Customers customerDetails = (Customers) session.getAttribute("customer");	
		// Petdao used for get petlist
		PetDaoImpl petdao = new PetDaoImpl();
		List<PetDetails> petList = petdao.showAllpetsDetails(customerDetails);	
		request.setAttribute("PetList", petList);//send pet list through request object
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}

	}

}
