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

import com.petshopapp.daoimpl.CartItemsDAO;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.exception.ItemAlreadyInCart;
import com.petshopapp.exception.QuantityNotAvalilable;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Print writer for response message to ajax call
		PrintWriter write = null;
		try {
			write = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
 
		// Session for to get customer details and pet details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		
		// CartItemsDAo for add pet into cart items
		CartItemsDAO cartItemDao = new CartItemsDAO();
		
		//Getting pet details for add in cart items 
		PetDAO petDao=new PetDAO();		
		PetDetails pet = petDao.showCurrentPet(Integer.parseInt(request.getParameter("petid")));
		
		// Cart List for check pet Item all ready in cart or not 
		List<CartItems> cartList = new ArrayList<CartItems>();
		cartList = cartItemDao.showAllCartItems(customerDetails);
		
		// Get customer required quantity 
		int quantity = Integer.parseInt(request.getParameter("quantity"));
       
		boolean available = true;
		
		// Ensure Customer required quantity available or not
		if (quantity <= pet.getAvilableQty()) {
			for (CartItems cartitems : cartList) {
				
				// Ensure pet item all ready in cart or not 
				if (cartitems.getPet().getPetId() == pet.getPetId()) {
					
					//if pet item already in cart its throws user defined exception
					try {
						throw new ItemAlreadyInCart();
					} catch (ItemAlreadyInCart e) {
						
                    // print message in ajax response
						write.print(e);
					}				  
					available = false;
					break;
				}
			}

			// User required quantity available and pet item not in cart insert values in cart items
			if (available) {
				CartItems cart = new CartItems();
				cart.getPet().setPetId(pet.getPetId());
				cart.getCustomer().setCustomerId(customerDetails.getCustomerId());
				cart.setQuantity(quantity);
				cart.setUnitPrice(pet.getPetprice());
				cart.setTotalPrice(quantity * pet.getPetprice());
				CartItemsDAO cartItemsDao = new CartItemsDAO();
				cartItemsDao.insertCartItem(cart);
				
				// Ajax response message for successful message 
				write.print("item add to cart");
			}
			
		} 
		
		// user required quantity is not available send message of not available Quantity
		else {
			try {
				throw new QuantityNotAvalilable();
			} catch (QuantityNotAvalilable e) {
				write.print(e + "\n\n Available Pet Quantity : " + pet.getAvilableQty());
			}
		}

	}
}
