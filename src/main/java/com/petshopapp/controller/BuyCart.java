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
import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.daoimpl.OrderItemsDAO;
import com.petshopapp.daoimpl.OrdersDAO;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.model.Orders;
import com.petshopapp.model.PetDetails;

@WebServlet("/BuyCart")
public class BuyCart extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    
    	  int itemId=Integer.parseInt(req.getParameter("itemId"));
    	   HttpSession session=req.getSession();
           PrintWriter write=resp.getWriter();
    	   CartItemsDAO cartDao=new CartItemsDAO();
    	   CartItems cartItems=cartDao.showCartItem(itemId);
    	   
    	   Customers customerDetails=(Customers)session.getAttribute("customer"); 
    	   Orders orders=new Orders();
    	   OrdersDAO ordersDao=new OrdersDAO();
    	   OrderItems orderItems=new OrderItems();
    	   OrderItemsDAO orderItemsDao=new OrderItemsDAO();
    	   PetDAO petDao=new PetDAO();
    	   
    	   PetDetails pet =petDao.showCurrentPet(cartItems.getPet().getPetId());
    	   CustomerDAO customerDao=new CustomerDAO();   
    	   Customers petCustomerDetails=customerDao.customerDetails(pet.getCustomer().getUserName());
    	   
    	   
    	   if(customerDetails.getWallet()>(cartItems.getTotalPrice())){  
    		   if(pet.getAvilableQty()>=cartItems.getQuantity()){	
    			   
    	   orders.getCustomer().setCustomerId(customerDetails.getCustomerId());
    	   orders.setTotalprice(cartItems.getTotalPrice());
    	   
    	   // insert values in orders
    	   ordersDao.insertOrder(orders);
    	     
    	   int orderId=ordersDao.getCurrentOrderId();  
    	   orderItems.getOrders().setOrderId(orderId);
    	   orderItems.getPet().setPetId(pet.getPetId());
    	   orderItems.setQuantity(cartItems.getQuantity());
    	   orderItems.setUnitPrice(pet.getPetprice());
    	   orderItems.setTotalPrice(cartItems.getTotalPrice());
    	   
    	   // insert the values in order items
    	   orderItemsDao.insertOrderItems(orderItems);
    	   
    	   //update pet available quantity
    	   pet.setAvilableQty((pet.getAvilableQty()-cartItems.getQuantity()));
    	   petDao.updatePetAvailableQuantity(pet);
    	   
    	   //update buyer wallet
    	   customerDetails.setWallet(customerDetails.getWallet()-cartItems.getTotalPrice());
    	   customerDao.updateCustomerWallet(customerDetails);
    	   
    	   //update seller wallet
    	   petCustomerDetails.setWallet(petCustomerDetails.getWallet()+cartItems.getQuantity());
    	   customerDao.updateCustomerWallet(petCustomerDetails);
    	   
    	   write.print("order placed sucussfully");
    	   }
    		   else{
    			   
    			   write.print("Quantity not avilable");
    		   }
    	   }
    	   
    	   else{
    		   write.print("low Wallet balance");
    	   }
    }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
    	doGet(req, resp);
    }
}
