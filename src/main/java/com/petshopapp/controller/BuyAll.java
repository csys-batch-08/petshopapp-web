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

@WebServlet("/BuyAll")
public class BuyAll extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	   HttpSession session=request.getSession();
           PrintWriter write=response.getWriter();
           double totalPrice=(Double)session.getAttribute("totalAmount");
           
           List<CartItems> cartList=(List<CartItems>)session.getAttribute("cartList");
         
           Customers customerDetails=(Customers)session.getAttribute("customer");
                      
           PetDAO petDao=new PetDAO();
           CustomerDAO customerDao=new CustomerDAO();
           Orders orders=new Orders();
           OrdersDAO ordersDao=new OrdersDAO();
           OrderItems orderItems=new OrderItems();
           OrderItemsDAO orderItemsDao=new OrderItemsDAO(); 
           
           CartItemsDAO cartItemsDao=new CartItemsDAO();
           boolean flage=true;
           if(customerDetails.getWallet()>=totalPrice){
        	   for(CartItems cartItems: cartList){
        		   PetDetails petDetails=petDao.showCurrentPet(cartItems.getPet().getPetId());
        		   if(petDetails.getAvilableQty()<cartItems.getQuantity()){
        	   flage=false;
        	   write.print("\n Sorry we can't process this request now "+"\n Quantity not Avialble Pet Id "+cartItems.getPet().getPetId()+"\n remove that item and try again");
        		   }
        	   }  
        	   
           if(flage){
           orders.getCustomer().setCustomerId(customerDetails.getCustomerId());
           orders.setTotalprice(totalPrice);
           ordersDao.insertOrder(orders);

           int orderId=ordersDao.getCurrentOrderId();
           
           for(CartItems cartItems: cartList){
           
           orderItems.getOrders().setOrderId(orderId);
           orderItems.getPet().setPetId(cartItems.getPet().getPetId());
           orderItems.setQuantity(cartItems.getQuantity());
           orderItems.setUnitPrice(cartItems.getUnitPrice());
           orderItems.setTotalPrice(cartItems.getTotalPrice());

           orderItemsDao.insertOrderItems(orderItems);
           
           PetDetails petDetails=petDao.showCurrentPet(cartItems.getPet().getPetId());
           
           //update pet available quantity
           petDetails.setAvilableQty((petDetails.getAvilableQty()-cartItems.getQuantity()));
           petDao.updatePetAvailableQuantity(petDetails);
             
           //update seller wallet
           Customers petCustomerDetails=customerDao.customerDetails(petDetails.getCustomer().getCustomerId());
           petCustomerDetails.setWallet(petCustomerDetails.getWallet()+(cartItems.getTotalPrice()));
           customerDao.updateCustomerWallet(petCustomerDetails);
           
           cartItemsDao.deleteCartItem(cartItems.getItemId());
           
           } 
           //update buyer wallet
           customerDetails.setWallet(customerDetails.getWallet()-totalPrice);
           customerDao.updateCustomerWallet(customerDetails);
           
           write.print("order placed successfully \n deducted amount : "
                        +totalPrice+"\n Wallet Amount : "+customerDetails.getWallet());
           }
           }
           else{
        	   try{
        	   		throw new LowWalletBalance();
        	   		}
        	   		catch(LowWalletBalance e){	
        	   			write.print(e +
        	   					"\n Your Wallet Balance"+customerDetails.getWallet()+
        	   					"\n Total Cart Amount"+totalPrice);
        	   		}
        		 
           }
           
    }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
    	doGet(req, resp);
    }
}
