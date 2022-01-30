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

@WebServlet("/updateProfileImage")
public class UpdateProfileImage extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	 PrintWriter write=response.getWriter();
    	 HttpSession session=request.getSession();
    	 String image=request.getParameter("image");
    	 Customers customer=(Customers)session.getAttribute("customer");
    	 customer.setImage(image);
    	 CustomerDAO customerDao=new CustomerDAO();
    	 customerDao.updateCustomerProfileImage(customer);
    	 write.print("Profile picture updated");
    	 
      }
      
}
