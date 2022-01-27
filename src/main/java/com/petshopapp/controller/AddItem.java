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

    	doGet(req, resp);
    }
}
