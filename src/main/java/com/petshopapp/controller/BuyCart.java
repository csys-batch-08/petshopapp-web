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
import com.petshopapp.exception.LowWalletBalance;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.model.Orders;
import com.petshopapp.model.PetDetails;

@WebServlet("/BuyCart")
public class BuyCart extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	   HttpSession session=request.getSession();
           PrintWriter write=response.getWriter();
           Orders orders = new Orders();
   		OrdersDAO ordersDao = new OrdersDAO();
   		
   		
   		OrderItems orderItems = new OrderItems();
   		OrderItemsDAO orderItemsDao = new OrderItemsDAO();
   		
   		PetDAO petDao = new PetDAO();
   		
   		CustomerDAO customerDao = new CustomerDAO();
   		CartItemsDAO cartDao = new CartItemsDAO();
   		
   		
   		
   		int itemId = Integer.parseInt(request.getParameter("itemId"));
   		
   		CartItems cartItems = cartDao.showCartItem(itemId);

   		Customers customerDetails = (Customers) session.getAttribute("customer");
   			
   		PetDetails pet = petDao.showCurrentPet(cartItems.getPet().getPetId());
   		
   		Customers petCustomerDetails = customerDao.customerDetails(pet.getCustomer().getCustomerId());
   		
   		if (customerDetails.getWallet() >= (cartItems.getTotalPrice())) {
   			
   			if (pet.getAvilableQty() >= cartItems.getQuantity()) {

   		 orders.getCustomer().setCustomerId(customerDetails.getCustomerId());
   		   orders.setTotalprice(cartItems.getTotalPrice());
   		   
   		   // insert values in orders
   		   ordersDao.insertOrder(orders);
   		     
   		   int orderId=ordersDao.getCurrentOrderId();  
   		   orderItems.getOrders().setOrderId(orderId);
   		   orderItems.getPet().setPetId(pet.getPetId());
   		   orderItems.setQuantity(cartItems.getQuantity());
   		   orderItems.setUnitPrice(cartItems.getUnitPrice());
   		   orderItems.setTotalPrice(cartItems.getTotalPrice());
   		   
   		   // insert the values in order items
   		   orderItemsDao.insertOrderItems(orderItems);
   		   
   		   //update pet available quantity
   		   pet.setAvilableQty((pet.getAvilableQty()-cartItems.getQuantity()));
   		   petDao.updatePetAvailableQuantity(pet);
   		   
   		   //update buyer wallet
   		   customerDetails.setWallet(customerDetails.getWallet()-(cartItems.getTotalPrice()));
   		   customerDao.updateCustomerWallet(customerDetails);
   		   
   		   //update seller wallet
   		   petCustomerDetails.setWallet(petCustomerDetails.getWallet()+(cartItems.getTotalPrice()));
   		   customerDao.updateCustomerWallet(petCustomerDetails);
   			       
   		
   			    	cartDao.deleteCartItem(cartItems.getItemId());
   		   
   			    	write.print("order placed sucussfully \n deducted Amount : "+cartItems.getTotalPrice()+
   			    			"\n Wallet Amount : "+customerDetails.getWallet());
   			} else {

   		write.print("Quantity not avilable");
   			}
   		}

   		else {
   			try{
   		
   		   		throw new LowWalletBalance();
   		   		}
   		   		catch(LowWalletBalance e){	
   		   			write.print(e+"\n Low wallet balance "+customerDetails.getWallet()+
   		   					       "\n Product amount"+ cartItems.getTotalPrice());
   		   		}
   			 
   		}
    }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
    	doGet(req, resp);
    }
}
