package com.petshopapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.PetDaoImpl;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.PetDetails;

@WebServlet("/EditPet")
public class EditPet extends HttpServlet {

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
		try {
			int petId = Integer.parseInt(request.getParameter("petid"));// User selected pet id
			// Objects used for get pet details based on the id
			PetDaoImpl petdao = new PetDaoImpl();
			PetDetails pet = petdao.showCurrentPet(petId);
			// send pet details as object editpet.jsp
			request.setAttribute("pet", pet);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("editpet.jsp");
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException | NullPointerException | NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}

	}

}
