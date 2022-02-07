package com.petshopapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.PetDetails;

@WebServlet("/PetDescription")
public class PetDescription extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
	/**
	 * This method is used for get pet details and send redirect petdescription.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
		// Get Pet Details
		int petid = Integer.parseInt(request.getParameter("petid"));
		PetDAO petdao = new PetDAO();
		PetDetails petDescription = petdao.showCurrentPet(petid);
		//Send details to petdescription.jsp	
		request.setAttribute("petDescription", petDescription);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("petdescription.jsp");
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException | NullPointerException| NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}

}
