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

@WebServlet("/UpdateProfile")
public class updateprofile extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    	 boolean flag=true;
    	 PrintWriter writer=resp.getWriter();
         HttpSession session=req.getSession();
         Customers customerDetails=(Customers)session.getAttribute("customer");
         Customers customers=new Customers();
         CustomerDAO customerDao=new CustomerDAO();
         String firstName=req.getParameter("firstname");
         String lastName=req.getParameter("lastname");
         String userName=req.getParameter("username");
         customers.setUserName(userName);
         
         if((customerDao.validateUsername(customers)==false) && (!userName.equals(customerDetails.getUserName()))) {
   
        	flag=false;
         }
         String password=req.getParameter("password");
         String email=req.getParameter("email");
         customers.setEmail(email);
         if((customerDao.validateUsername(customers)==false) && (!email.equals(customerDetails.getEmail()))) {

         	flag=false;
          }
         long number=Long.parseLong(req.getParameter("number"));
         String gender=req.getParameter("gender");
         
         if(flag) {
         customerDetails.setFirstName(firstName);
         customerDetails.setLastName(lastName);
         customerDetails.setUserName(userName);
         customerDetails.setPassword(password);
         customerDetails.setEmail(email);
         customerDetails.setNumber(number);
         customerDetails.setGender(gender);        
         customerDao.updateCustomerDetails(customerDetails);
         }
             writer.print("	<script type=\"text/javascript\"> alert('Profile updated successfully'); window.location = 'myprofile.jsp';</script>");
    }
      
}
