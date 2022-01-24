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

import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/AddItem")
public class AddItem extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		HttpSession session=req.getSession();
    		Customers customerDetails=(Customers) session.getAttribute("customer");
    		CustomerDAO customerDao=new CustomerDAO();
    	    customerDetails=customerDao.customerDetails(customerDetails.getCustomerId());
    	    PrintWriter out=resp.getWriter();
    	    if(customerDetails.getAddress().equals("none")) {
    	    	 out.print("<script type=\"text/javascript\">alert('Before Add pet please update address');"
 	            		+ "window.location = 'myprofile.jsp';</script>");
	
    	    }
    	    else {
    	    	resp.sendRedirect("AddItem.jsp");	 
    	    	}
    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doGet(req, resp);
    }
}
