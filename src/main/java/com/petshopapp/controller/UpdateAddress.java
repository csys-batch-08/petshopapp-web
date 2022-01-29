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
import com.petshopapp.model.Customers;

@WebServlet("/UpdateAddress")
public class UpdateAddress extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	
    	PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession();      
        String address = request.getParameter("address");
     	String city = request.getParameter("city");
     	int pinCode = Integer.parseInt(request.getParameter("pincode"));
     	Customers customer = (Customers) session.getAttribute("customer");
     	customer.setAddress(address);
     	customer.setCity(city);
     	customer.setPincode(pinCode);
     	CustomerDAO customerDao = new CustomerDAO();
     	customerDao.updateAddressDetails(customer);

       writer.print("<script type=\"text/javascript\"> alert('Address updated'); window.location = 'myprofile.jsp';</script>");
    }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	doGet(req, resp);
    }
}
