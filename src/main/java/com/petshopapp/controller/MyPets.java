package com.petshopapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/MyPets")
public class MyPets extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	    
    	    PetDAO petdao = new PetDAO();
    		PetDetails pet = new PetDetails();
    		HttpSession session=req.getSession();
    		List<PetDetails> myPetList = new ArrayList<PetDetails>();
    		Customers customerDetails = (Customers) session.getAttribute("customer");
    	//	myPetList = petdao.showMypetdetails(customerDetails.getCustomerId());
    	//	session.setAttribute("myPetList", myPetList);  
		    RequestDispatcher rd =req.getRequestDispatcher("MyPets.jsp");
		    rd.forward(req, resp);

    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
}
