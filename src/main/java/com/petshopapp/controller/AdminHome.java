package com.petshopapp.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.PetDetails;

@WebServlet("/AdminHome")
public class AdminHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	/**
	 * This method is used to get not approved pet list and send to adminhome.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		PetDAO petdao = new PetDAO();
		List<PetDetails> petList = petdao.showNotApprovedPetList();
		request.setAttribute("PetList", petList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminhome.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
