package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.PetDaoImpl;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.PetDetails;

@WebServlet("/DeletePet")
public class DeletePet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	
	/**
	 * This method is used to update particular pet status to delete
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter write = response.getWriter(); // print writer for AJAX response
			int petId = Integer.parseInt(request.getParameter("petId"));// pet id for update status
			PetDetails pet = new PetDetails();
			PetDaoImpl petdao = new PetDaoImpl();
			pet.setPetId(petId);
			petdao.delete(pet);	
			write.print("Pet item deleted successfully");// AJAX response message
		} catch (IOException|NullPointerException|NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
