package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.PetDetails;

@WebServlet("/DeletePet")
public class DeletePet extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    	 PrintWriter write=response.getWriter();
    	 int petId=Integer.parseInt(request.getParameter("petId"));
    	 PetDetails pet=new PetDetails();
    	 PetDAO petdao=new PetDAO(); 
    	 pet.setPetId(petId);
    	 petdao.delete(pet);
    	 write.print("Pet item deleted successfully");
      }
      
}
