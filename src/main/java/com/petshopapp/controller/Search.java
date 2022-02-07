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
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/Search")
public class Search extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used to get pet list based on searched words
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		// Get search details
		String search = request.getParameter("search").toLowerCase();
		// Get searched pet list
		PetDAO petdao = new PetDAO();
		List<PetDetails> petListSearch = petdao.searchPetDetails(search, customerDetails.getCustomerId());
		//send details to serach.jsp
		request.setAttribute("PetListSearch", petListSearch);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("search.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
