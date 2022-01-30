package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.petshopapp.exception.LowWalletBalance;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.model.Orders;
import com.petshopapp.model.PetDetails;

@WebServlet("/BuyNow")
public class BuyNow extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	   HttpSession session=request.getSession();
           PrintWriter write=response.getWriter();
           
           int quantity=Integer.parseInt(request.getParameter("quantity"));

           PetDetails pet=( PetDetails)session.getAttribute("petDescription");
           Customers customerDetails=(Customers)session.getAttribute("customer");           
           Orders orders=new Orders();
           OrdersDAO ordersDao=new OrdersDAO();
           OrderItems orderItems=new OrderItems();
           OrderItemsDAO orderItemsDao=new OrderItemsDAO();
           PetDAO petDao=new PetDAO();
           CustomerDAO customerDao=new CustomerDAO();
           
           Customers petCustomerDetails=customerDao.customerDetails(pet.getCustomer().getCustomerId());
         
           if(customerDetails.getWallet()>=(quantity*pet.getPetprice())){  
        	   if(pet.getAvilableQty()>=quantity){	   
           orders.getCustomer().setCustomerId(customerDetails.getCustomerId());
           orders.setTotalprice((quantity*pet.getPetprice()));
           
           // insert values in orders
           ordersDao.insertOrder(orders);
             
           int orderId=ordersDao.getCurrentOrderId();  
           orderItems.getOrders().setOrderId(orderId);
           orderItems.getPet().setPetId(pet.getPetId());
           orderItems.setQuantity(quantity);
           orderItems.setUnitPrice(pet.getPetprice());
           orderItems.setTotalPrice((quantity*pet.getPetprice()));
           
           // insert the values in order items
           orderItemsDao.insertOrderItems(orderItems);
           
           //update pet available quantity
           pet.setAvilableQty((pet.getAvilableQty()-quantity));
           petDao.updatePetAvailableQuantity(pet);
           
           //update buyer wallet
           customerDetails.setWallet(customerDetails.getWallet()-(quantity*pet.getPetprice()));
           customerDao.updateCustomerWallet(customerDetails);
           
           //update seller wallet
           petCustomerDetails.setWallet(petCustomerDetails.getWallet()+(quantity*pet.getPetprice()));
           customerDao.updateCustomerWallet(petCustomerDetails);
           
           write.print("order placed sucussfully "+
                       "\n Order Amount : "+(quantity*pet.getPetprice())+
                       "\n Current wallet Balance : "+customerDetails.getWallet());
           }
        	   else{
        		   write.print("Quantity not avilable");
        	   }
           }
           
           else{
        	   
        	   try{
           		throw new LowWalletBalance();
           		}
           		catch(LowWalletBalance e){	
           			write.print(e+
           					"\n Low wallet balance : "+customerDetails.getWallet()+
           					"\n Product Amount : "+(quantity*pet.getPetprice()));
           		}
        	 
           }
    }
      

}
