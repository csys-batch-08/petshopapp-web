package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.petshopapp.daoimpl.CartItemsDAO;

@WebServlet("/RemoveCartItem")
public class RemoveCatItem extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	
    	PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession();      
    	int itemId=Integer.parseInt(request.getParameter("itemId"));		
        CartItemsDAO cartItemsDao=new CartItemsDAO();
        cartItemsDao.deleteCartItem(itemId);
        writer.print("Cart Item Removed");
    }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	doGet(req, resp);
    }
}
