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

@WebServlet("/Search")
public class Search extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	List<PetDetails> petListSearch = new ArrayList<PetDetails>();
    	HttpSession session=req.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
    	String search=req.getParameter("search").toLowerCase();
    	PetDetails pet = new PetDetails();
    	PetDAO petdao = new PetDAO();
    	petListSearch = petdao.searchPetDetails(search,customerDetails.getCustomerId());
    	session.setAttribute("PetListSearch", petListSearch); 
    	RequestDispatcher rd =req.getRequestDispatcher("Search.jsp");
	    rd.forward(req, resp);		
    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
}
