package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Customers;

@WebServlet("/ValidateEmail")
public class ValidateEmail extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    

    	 PrintWriter write=response.getWriter();
    	 
         Customers customer =new Customers();
         CustomerDAO customerDao=new CustomerDAO();
         String email=request.getParameter("email");
         customer.setEmail(email);
         boolean condition=customerDao.validateEmail(customer);
         if(condition==false){
           write.print("Email not available");
         }
         else{
        	 write.print("Available");
         }
               }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	doGet(req, resp);
    }
}
