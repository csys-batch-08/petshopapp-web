package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CartItemsDaoImpl;
import com.petshopapp.daoimpl.PetDaoImpl;
import com.petshopapp.exception.ItemAlreadyInCart;
import com.petshopapp.exception.QuantityNotAvalilable;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used to add pet into cart item
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		PetDaoImpl petDao = new PetDaoImpl();
		CartItemsDaoImpl cartItemDao = new CartItemsDaoImpl();
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		List<CartItems> cartList = cartItemDao.showAllCartItems(customerDetails);
		PrintWriter write =null;
		PetDetails pet =null;
      try {
    	  write = response.getWriter();
    	  pet = petDao.showCurrentPet(Integer.parseInt(request.getParameter("petid")));
    	  int quantity = Integer.parseInt(request.getParameter("quantity"));
    	  if (quantity <= pet.getAvilableQty()) {
  			for (CartItems cartitems : cartList) {
  				if (cartitems.getPet().getPetId() == pet.getPetId()) {
  				  throw new ItemAlreadyInCart();
  				}
  			}

				CartItems cart = new CartItems();
				cart.getPet().setPetId(pet.getPetId());
				cart.getCustomer().setCustomerId(customerDetails.getCustomerId());
				cart.setQuantity(quantity);
				cart.setUnitPrice(pet.getPetprice());
				cart.setTotalPrice(quantity * pet.getPetprice());
				CartItemsDaoImpl cartItemsDao = new CartItemsDaoImpl();
				cartItemsDao.insertCartItem(cart);
				write.print("Pet item add to cart");
      }
    	  else {
  				throw new QuantityNotAvalilable();	
  		}
    	  }
      catch(IOException |NullPointerException|NumberFormatException e) {  
    	  Logger.printStackTrace(e);
  	      Logger.runTimeException(e.getMessage());  
      }catch (ItemAlreadyInCart e) {
			write.print(e);
		}
      catch (QuantityNotAvalilable e) {
			write.print(e + "\n\n Available Pet Quantity : " + pet.getAvilableQty());
		}
	}
}
