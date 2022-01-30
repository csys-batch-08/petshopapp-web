package com.petshopapp.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MyCart")
public class MyCart extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	    CartItemsDAO cartItemDao = new CartItemsDAO();
//    		Customers customerDetails = new Customers();
//    		HttpSession session=request.getSession();
//    		customerDetails = (Customers) session.getAttribute("customer");
//    		List<CartItems> cartList = new ArrayList<CartItems>();
//    		cartList = cartItemDao.showAllCartItems(customerDetails);
//    		session.setAttribute("cartList", cartList);   
//		    RequestDispatcher requestDispatcher =request.getRequestDispatcher("mycart.jsp");
//		    requestDispatcher.forward(request, response);
    }
  

}
