package com.petshopapp.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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


@WebServlet("/PetDescription")
public class PetDescription extends HttpServlet{	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PetDetails petDescription = new PetDetails();
  		PetDAO petdao = new PetDAO();
  		int petid = Integer.parseInt(request.getParameter("petid"));
  		petDescription = petdao.showCurrentPet(petid);
  		HttpSession session=request.getSession();
  		Customers customer=(Customers) session.getAttribute("customer");
  		CustomerDAO customerDao=new CustomerDAO();
  		customer=customerDao.customerDetails(customer.getCustomerId());
  		session.setAttribute("customer", customer);
  		//session.setAttribute("petDescription", petDescription);
  	    RequestDispatcher requestDispatcher =request.getRequestDispatcher("petdescription.jsp");
	    requestDispatcher.forward(request, response);   
    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
}
