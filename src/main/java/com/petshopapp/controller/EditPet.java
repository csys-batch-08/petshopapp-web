package com.petshopapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.PetDetails;

@WebServlet("/EditPet")
public class EditPet extends HttpServlet{
	
      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) {
      		doGet(request, response);
      		
      }
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	
    	// User selected pet detail id
    	int petId = Integer.parseInt(request.getParameter("petid"));
    	
    	// Objects used for get pet details based on the id
   		PetDAO petdao = new PetDAO();
   		PetDetails pet = petdao.showCurrentPet(petId);
   		
   		// send pet details as object editpet.jsp
   		request.setAttribute("pet", pet);  		
   		RequestDispatcher requestDispatcher=request.getRequestDispatcher("editpet.jsp");
   		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {		
			e.printStackTrace();
		}
   		
   	   
       		
       }
  
}
