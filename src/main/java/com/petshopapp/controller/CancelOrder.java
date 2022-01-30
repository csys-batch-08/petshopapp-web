package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
        	 PrintWriter write=response.getWriter();
    	    HttpSession session=request.getSession();
    	    Customers customerDetails = (Customers) session.getAttribute("customer");
    	   
    		CustomerDAO customerDao = new CustomerDAO();
    		Customers customer = new Customers();
    		PetDetails pet = new PetDetails();
    		PetDAO petDao = new PetDAO();
    		OrderItemsDAO orderItemDao = new OrderItemsDAO();
    		List<OrderItems> orderItemList = new ArrayList<OrderItems>();
    		Orders order = new Orders();
    		OrdersDAO orderDao = new OrdersDAO();
    		int orderId = Integer.parseInt(request.getParameter("orderId"));
    		
    		order.setOrderId(orderId);
    		orderItemList = orderItemDao.getCurrentOrderItemDetails(orderId);

    		int Amount = 0;
    		
    		for (OrderItems orderItem : orderItemList) {
    			
    			
    		pet = petDao.showCurrentPet(orderItem.getPet().getPetId());

    		pet.setAvilableQty((pet.getAvilableQty() + orderItem.getQuantity()));
    		petDao.updatePetAvailableQuantity(pet);

    		Amount += orderItem.getTotalPrice();
    		
    		customerDetails.setWallet(customerDetails.getWallet() + (orderItem.getTotalPrice()));
    		customerDao.updateCustomerWallet(customerDetails);

    		customer = customerDao.customerDetails(pet.getCustomer().getCustomerId());
    		customer.setWallet(customer.getWallet() - orderItem.getTotalPrice());
    		customerDao.updateCustomerWallet(customer);

    	}
    	    orderDao.updateOrderStatus(order);
    	    write.print("order cancelld "+
    	                "\n credit amount :" + Amount + 
    	                "\n Total Wallet balance :" + customerDetails.getWallet());
      }
      

}
