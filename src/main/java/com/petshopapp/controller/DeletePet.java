package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.PetDetails;

@WebServlet("/DeletePet")
public class DeletePet extends HttpServlet{
	

      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) {
      		doGet(request, response);
      		
      }
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	   
    	// print writer for ajax response
    	   PrintWriter write=null;
		try {
			write = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 // pet id for update status
      	 int petId=Integer.parseInt(request.getParameter("petId"));
      	 PetDetails pet=new PetDetails();
      	 PetDAO petdao=new PetDAO(); 
      	 pet.setPetId(petId);
      	 petdao.delete(pet);
      	 
      	 //ajax response message
      	 write.print("Pet item deleted successfully");
       		
       }
}
