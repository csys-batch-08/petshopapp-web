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
import com.petshopapp.exception.InvalidWalletAmount;
import com.petshopapp.model.Customers;

@WebServlet("/UpdateWallet")
public class UpdateWallet extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 PrintWriter write=response.getWriter();
    	 HttpSession session=request.getSession();
    	 double wallet = Integer.parseInt(request.getParameter("wallet"));
    	 boolean message = true;
    	
    		if (wallet <= 0) {
    			try {
    				throw new InvalidWalletAmount();
    			} catch (InvalidWalletAmount e) {
    				write.print(e);
    			}
    			message = false;
    		}
    		if (message) {
    			Customers customer = (Customers) session.getAttribute("customer");
    			customer.setWallet(customer.getWallet() + wallet);
    			CustomerDAO customerDao = new CustomerDAO();
    			customerDao.updateCustomerWallet(customer);
    			write.print("Amount Added");
    		}
               }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	doGet(req, resp);
    }
}
