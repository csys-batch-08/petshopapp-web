package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Admin;

@WebServlet("/UpdatePetStatus")
public class UpdatePetStatus extends HttpServlet{	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		HttpSession session=request.getSession();
    		int petId = Integer.parseInt(request.getParameter("petId"));
    		String status = request.getParameter("status");
    		Admin admin = (Admin) session.getAttribute("Admin");
    		PetDAO petDao = new PetDAO();
    		petDao.updatePetStatus(petId, status, admin.getAdminId());
    	    PrintWriter write=response.getWriter();
    	    write.print("Status updated");   		
    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	doGet(req, resp);
    }
}
