package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/Home")
public class Home extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		HttpSession session=req.getSession();
    		Customers customerDetails=(Customers) session.getAttribute("customer");
    	    PetDAO petdao = new PetDAO();
			List<PetDetails> petList = new ArrayList<PetDetails>();
			petList = petdao.showAllpetsDetails(customerDetails);
			session.setAttribute("PetList", petList);  
		    RequestDispatcher rd =req.getRequestDispatcher("home.jsp");
		    rd.forward(req, resp);
    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doGet(req, resp);
    }
}
