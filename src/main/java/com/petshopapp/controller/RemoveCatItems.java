package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CartItemsDAO;
import com.petshopapp.model.CartItems;

@WebServlet("/RemoveCartItems")
public class RemoveCatItems extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	
    	PrintWriter write=response.getWriter();
        HttpSession session=request.getSession();    
        CartItemsDAO cartItemsDao=new CartItemsDAO();
        List<CartItems> cartList=(List<CartItems>)session.getAttribute("cartList");
        for(CartItems cartItems:cartList){
              cartItemsDao.deleteCartItem(cartItems.getItemId());
        }
        write.print("All Cart Items Are Deleted");

    }
      
}
