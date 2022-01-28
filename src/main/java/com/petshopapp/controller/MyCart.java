package com.petshopapp.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CartItemsDAO;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;

@WebServlet("/MyCart")
public class MyCart extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  CartItemsDAO cartItemDao = new CartItemsDAO();
    		Customers customerDetails = new Customers();
    		HttpSession session=request.getSession();
    		customerDetails = (Customers) session.getAttribute("customer");
    		List<CartItems> cartList = new ArrayList<CartItems>();
    		cartList = cartItemDao.showAllCartItems(customerDetails);
    	//	session.setAttribute("cartList", cartList);   
		    RequestDispatcher requestDispatcher =request.getRequestDispatcher("mycart.jsp");
		    requestDispatcher.forward(request, response);
    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	doGet(req, resp);
    }
}