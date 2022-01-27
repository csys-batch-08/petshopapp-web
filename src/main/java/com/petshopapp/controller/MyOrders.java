package com.petshopapp.controller;

import java.io.IOException;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.OrderItemsDAO;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;

@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet{
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	    
  
     		HttpSession session=req.getSession();
    		Customers customerDetails = (Customers) session.getAttribute("customer");     	
    		OrderItemsDAO orderItemDao = new OrderItemsDAO();
    		List<OrderItems> orderItemList = orderItemDao.showMyOrdersItemsList(customerDetails);
    		//session.setAttribute("orderItemsList",orderItemList);
		    RequestDispatcher rd =req.getRequestDispatcher("myorders.jsp");
		    rd.forward(req, resp);
    }
 
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
}
