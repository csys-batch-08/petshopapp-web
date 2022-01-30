package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.AdminDAO;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.model.Admin;
import com.petshopapp.model.Customers;

@WebServlet("/login")
public class Login extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	PrintWriter out=resp.getWriter();
    	String userName=req.getParameter("usernameinput");
		String passwowrd=req.getParameter("passwordinput");
		
		Customers customerValidation=new Customers();
		customerValidation.setUserName(userName);
		customerValidation.setPassword(passwowrd);
		CustomerDAO customerDao=new CustomerDAO();		
		String firstName=customerDao.customerLoginValidation(customerValidation);
		HttpSession session=req.getSession();
	
		if (firstName != null) {
			if (firstName.charAt(0) == '1') {			
				Customers customerDetails=new Customers();
				
				customerDetails = customerDao.customerDetails(userName);				
				session.setAttribute("customer", customerDetails);              
			    RequestDispatcher rd =req.getRequestDispatcher("home.jsp");
			    rd.forward(req, resp);
			
			}
			else {
				Admin admin;
				AdminDAO adminDao=new AdminDAO();			
						admin = adminDao.show(userName);
						session.setAttribute("Admin", admin);				         
						resp.sendRedirect("adminhome.jsp");										
			}				
}		else {	           
	            out.print("<script type=\"text/javascript\">alert('invalid username or password');"
	            		+ "window.location = 'index.jsp';</script>");	         
}

    }
      
}
