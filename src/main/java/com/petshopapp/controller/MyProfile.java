package com.petshopapp.controller;
import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Customers;

@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    		HttpSession session=request.getSession();	
    		CustomerDAO customerDao=new CustomerDAO();
    		Customers  customerDetails = (Customers) session.getAttribute("customer");
    		customerDetails =customerDao.customerDetails(customerDetails.getCustomerId());
    		session.setAttribute("customer", customerDetails);
		    RequestDispatcher requestDispatcher =request.getRequestDispatcher("myprofile.jsp");
		    requestDispatcher.forward(request, response);
    }
  
}
