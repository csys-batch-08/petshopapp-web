package com.petshopapp.controller;


import java.io.IOException;
import java.io.PrintWriter;
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
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
    		HttpSession session=req.getSession();
    		Customers customerDetails=(Customers) session.getAttribute("customer");
    		CustomerDAO customerDao=new CustomerDAO();
    	    customerDetails=customerDao.customerDetails(customerDetails.getCustomerId());
    	   
    	    if(customerDetails.getAddress().equals("none")) {
    	    	 PrintWriter out;
    				try {
    					out = resp.getWriter();
    					 out.print("<script type=\"text/javascript\">alert('Before Add pet please update address');"
    		 	            		+ "window.location = 'myprofile.jsp';</script>");
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    	    	
    	    }
    	    else {
    	    	try {
					resp.sendRedirect("additem.jsp");
				} catch (IOException e) {	
					e.printStackTrace();
				}	 
    	    	}
    }
}
  

