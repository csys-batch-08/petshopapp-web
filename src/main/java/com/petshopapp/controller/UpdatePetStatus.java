package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Admin;

@WebServlet("/UpdatePetStatus")
public class UpdatePetStatus extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		//Get Admin details
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("Admin");
		
		//Update status
		PetDAO petDao = new PetDAO();
		int petId = Integer.parseInt(request.getParameter("petId"));
		String status = request.getParameter("status");
		petDao.updatePetStatus(petId, status, admin.getAdminId());
		
		//ajax response message
		PrintWriter write;
		try {
			write = response.getWriter();
			write.print("Status updated");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
