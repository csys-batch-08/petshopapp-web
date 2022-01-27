package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/EditPet")
public class EditPet extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respose) throws ServletException, IOException {
    		HttpSession session=request.getSession();
    		PetDetails pet = new PetDetails();
    		PetDAO petdao = new PetDAO();
    		int petId = Integer.parseInt(request.getParameter("petid"));
    		session.setAttribute("updatePet", petId);
    		pet = petdao.showCurrentPet(petId);
    		session.setAttribute("pet", pet);
    	    respose.sendRedirect("EditPet.jsp");	 
    	    	
    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	doGet(req, resp);
    }
}
