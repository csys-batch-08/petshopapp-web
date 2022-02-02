package com.petshopapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.PetDetails;

@WebServlet("/PetDescription")
public class PetDescription extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		// Get Pet Details
		int petid = Integer.parseInt(request.getParameter("petid"));
		PetDAO petdao = new PetDAO();
		PetDetails petDescription = petdao.showCurrentPet(petid);
		
		//Send details to petdescription.jsp	
		request.setAttribute("petDescription", petDescription);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("petdescription.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
