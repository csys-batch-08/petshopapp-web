package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.OrderItemsDAO;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.model.PetDetails;

@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	    
    	    PetDAO petdao = new PetDAO();
    	    System.out.println("its working");
    		PetDetails pet = new PetDetails();
    		HttpSession session=req.getSession();
    		Customers customerDetails = (Customers) session.getAttribute("customer");     	
    		OrderItemsDAO orderItemDao = new OrderItemsDAO();
    		List<OrderItems> orderItemList = orderItemDao.showMyOrdersItemsList(customerDetails);
    		SimpleDateFormat formet = new SimpleDateFormat("dd-MM-yyyy");
    		session.setAttribute("orderItemsList",orderItemList);
    		int length =orderItemList.size();
    		String date[]= new String[length];
    		for(int i=0;i<length;i++) {
    		Date orderdate=orderItemList.get(i).getOrders().getOrderDate();
    		String sDate=formet.format(orderdate);
    		date[i]=sDate;
    		}
    		session.setAttribute("orderDate", date);
		    RequestDispatcher rd =req.getRequestDispatcher("myorders.jsp");
		    rd.forward(req, resp);
    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doGet(req, resp);
    }
}
