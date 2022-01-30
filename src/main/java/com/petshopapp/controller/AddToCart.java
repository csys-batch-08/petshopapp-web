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

import com.petshopapp.daoimpl.CartItemsDAO;
import com.petshopapp.exception.ItemAlreadyInCart;
import com.petshopapp.exception.QuantityNotAvalilable;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	 PrintWriter write=response.getWriter();
    	 HttpSession session=request.getSession();
    	 boolean available=true;
    	 
    	    CartItemsDAO cartItemDao=new CartItemsDAO();
    	    
    	    Customers customerDetails=(Customers)session.getAttribute("customer");
    	    
    	    PetDetails pet=(PetDetails) session.getAttribute("petDescription");
    	    
    	    List<CartItems> cartList=new ArrayList<CartItems>();
    	    
    	    cartList=cartItemDao.showAllCartItems(customerDetails);
    	    
    	    int quantity=Integer.parseInt(request.getParameter("quantity"));  
    	    
    	    if(quantity<=pet.getAvilableQty()){
    	    for(CartItems cartitems:cartList){
    	    	if(cartitems.getPet().getPetId()==pet.getPetId()){
    	    		try{
    	    		throw new ItemAlreadyInCart();
    	    		}
    	    		catch(ItemAlreadyInCart e){
    	    			
    	    			write.print(e);
    	    		}
    	    		available=false;  		
    	    		break;
    	    	}
    	    }
    	    
    	   if(available){
    	        
    	    CartItems cart=new CartItems();
    	    
    	    cart.getPet().setPetId(pet.getPetId());
    	    cart.getCustomer().setCustomerId(customerDetails.getCustomerId());
    	    cart.setQuantity(quantity);
    	    cart.setUnitPrice(pet.getPetprice());
    	    cart.setTotalPrice(quantity*pet.getPetprice());
    	    
    	    
    	    CartItemsDAO cartItemsDao=new CartItemsDAO();
    	    cartItemsDao.insertCartItem(cart);
    	    write.print("item add to cart");
    	   }
    	    }
    	    else{
    	    	try{
    	    		throw new QuantityNotAvalilable();
    	    	}
    	    	catch(QuantityNotAvalilable e){
    	    		write.print(e+"\n\n Available Pet Quantity : "+pet.getAvilableQty());
    	    	}
    	    }
    	 
      }
      
}
