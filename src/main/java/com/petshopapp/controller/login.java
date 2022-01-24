package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.AdminDAO;
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Admin;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/login")
public class login extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	PrintWriter out=resp.getWriter();
    	String userName=req.getParameter("usernameinput");
		String passwowrd=req.getParameter("passwordinput");
		
		Customers customerValidation=new Customers(userName,passwowrd);
		CustomerDAO customerDao=new CustomerDAO();
		
		String firstName=customerDao.customerLoginValidation(customerValidation);

		HttpSession session=req.getSession();
		session.setAttribute("message", " ");
		if (firstName != null) {
			String name = firstName.substring(1);
			if (firstName.charAt(0) == '1') {
				session.setAttribute("message", "none");
				session.setAttribute("profileMessage", "none");
				Customers customerDetails=new Customers(userName,passwowrd);
				customerDetails = customerDao.customerDetails(userName);
				
				
				session.setAttribute("customer", customerDetails);              
				PetDAO petdao = new PetDAO();
				List<PetDetails> petList = new ArrayList<PetDetails>();
				petList = petdao.showAllpetsDetails(customerDetails);
				session.setAttribute("PetList", petList);  
				//req.setAttribute("petList", petList);
			    RequestDispatcher rd =req.getRequestDispatcher("home.jsp");
			    rd.forward(req, resp);
			//	resp.sendRedirect("home.jsp");
			}
			else {
				Admin admin;
				AdminDAO adminDao=new AdminDAO();
					try {
						admin = adminDao.show(userName);
						session.setAttribute("Admin", admin);
						resp.sendRedirect("AdminHome.jsp");
						//System.out.println(admin);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			}				
}		else {
	            session.setAttribute("message", "Invalid username or password");
	            out.print("	<script type=\"text/javascript\">alert('invalid username or password');"
	            		+ "window.location = 'index.jsp';</script>");
	          
	          //  resp.sendRedirect("index.jsp");
}

    }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doGet(req, resp);
    }
}
