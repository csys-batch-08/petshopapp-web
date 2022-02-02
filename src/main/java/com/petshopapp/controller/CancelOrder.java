package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.daoimpl.OrderItemsDAO;
import com.petshopapp.daoimpl.OrdersDAO;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.model.Orders;
import com.petshopapp.model.PetDetails;

@WebServlet("/CancelOrder")
public class CancelOrder extends HttpServlet{
	
      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) {
      		doGet(request, response);
      		
      }
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	   	
    	   //print writer for ajax response
        	 PrintWriter write=null;
			try {
				write = response.getWriter();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			// session used for customer details
     	    HttpSession session=request.getSession();
     	    Customers customerDetails = (Customers) session.getAttribute("customer");
     	   
     		CustomerDAO customerDao = new CustomerDAO();
     		Customers customer = new Customers();
     		
     		// petdetails and petdao for update pet available qantity
     		PetDetails pet = new PetDetails();
     		PetDAO petDao = new PetDAO();
     		
     		//order item and order objects are used cancel orders and order items
     		OrderItemsDAO orderItemDao = new OrderItemsDAO();
     		List<OrderItems> orderItemList = new ArrayList<OrderItems>();
     		Orders order = new Orders();
     		OrdersDAO orderDao = new OrdersDAO();
     		
     		// order id for cancel id
     		int orderId = Integer.parseInt(request.getParameter("orderId"));
     		order.setOrderId(orderId);
     		orderItemList = orderItemDao.getCurrentOrderItemDetails(orderId);

     		int Amount = 0;
     		
     		for (OrderItems orderItem : orderItemList) {
     			
     	    // get order item pet details
     		pet = petDao.showCurrentPet(orderItem.getPet().getPetId());
            
     		
     		// update pet quantity
     		pet.setAvilableQty((pet.getAvilableQty() + orderItem.getQuantity()));
     		petDao.updatePetAvailableQuantity(pet);
            
     		// refund amount 
     		Amount += orderItem.getTotalPrice();
     		
     		//update buyer customer wallet
     		customerDetails.setWallet(customerDetails.getWallet() + (orderItem.getTotalPrice()));
     		customerDao.updateCustomerWallet(customerDetails);
            
     		// update seller wallet
     		customer = customerDao.customerDetails(pet.getCustomer().getCustomerId());
     		customer.setWallet(customer.getWallet() - orderItem.getTotalPrice());
     		customerDao.updateCustomerWallet(customer);

     	}
     	    orderDao.updateOrderStatus(order);
     	    
     	    //ajax response message
     	    write.print("order cancelld "+
     	                "\n credit amount :" + Amount + 
     	                "\n Total Wallet balance :" + customerDetails.getWallet());
       		
       }

}
