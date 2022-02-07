package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.petshopapp.logger.Logger;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.model.Orders;
import com.petshopapp.model.PetDetails;

@WebServlet("/BuyCart")
public class BuyCart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	/**
	 * This method is used to buy cart item and update customer wallet and pet available quantity
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Session to get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		// orders and ordersdao object for store and update the values of order details
		Orders orders = new Orders();
		OrdersDAO ordersDao = new OrdersDAO();
		// orderitmes and orderitemsdao object for store and update the values of order
		// items details
		OrderItems orderItems = new OrderItems();
		OrderItemsDAO orderItemsDao = new OrderItemsDAO();
		// petdao for update pet available quantity
		PetDAO petDao = new PetDAO();
		// customer dao for update seller customer wallet
		CustomerDAO customerDao = new CustomerDAO();
		CartItemsDAO cartDao = new CartItemsDAO();
		
		try {
		// print writer for ajax response
		PrintWriter write = response.getWriter();
		// cart item id for buy a cart item
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		// to get cart item details
		CartItems cartItems = cartDao.showCartItem(itemId);
		// to get cart item pet details
		PetDetails pet = petDao.showCurrentPet(cartItems.getPet().getPetId());
		// get seller customer details for update
		Customers petCustomerDetails = customerDao.customerDetails(pet.getCustomer().getCustomerId());
		// Check buyer customer wallet id higher or equal cart price
		if (customerDetails.getWallet() >= (cartItems.getTotalPrice())) {
			// check cart quantity available or not
			if (pet.getAvilableQty() >= cartItems.getQuantity()) {
				// insert values in orders
				orders.getCustomer().setCustomerId(customerDetails.getCustomerId());
				orders.setTotalprice(cartItems.getTotalPrice());
				ordersDao.insertOrder(orders);
				// insert the values in order items
				int orderId = ordersDao.getCurrentOrderId();
				orderItems.getOrders().setOrderId(orderId);
				orderItems.getPet().setPetId(pet.getPetId());
				orderItems.setQuantity(cartItems.getQuantity());
				orderItems.setUnitPrice(cartItems.getUnitPrice());
				orderItems.setTotalPrice(cartItems.getTotalPrice());
				orderItemsDao.insertOrderItems(orderItems);
				// update pet available quantity
				pet.setAvilableQty((pet.getAvilableQty() - cartItems.getQuantity()));
				petDao.updatePetAvailableQuantity(pet);
				// update buyer wallet
				customerDetails.setWallet(customerDetails.getWallet() - (cartItems.getTotalPrice()));
				customerDao.updateCustomerWallet(customerDetails);
				// update seller wallet
				petCustomerDetails.setWallet(petCustomerDetails.getWallet() + (cartItems.getTotalPrice()));
				customerDao.updateCustomerWallet(petCustomerDetails);
				cartDao.deleteCartItem(cartItems.getItemId());
				write.print("Order placed sucussfully \n Deducted Amount : Rs. " + cartItems.getTotalPrice()
						+ "\n Wallet Amount : Rs. " + customerDetails.getWallet());
			} else {
				write.print("Quantity not avilable");
			}
		}
		else {
			try {
				throw new LowWalletBalance();
			} catch (LowWalletBalance e) {
				write.print(e + "\n Current wallet amount : Rs. " + customerDetails.getWallet()
						+ "\n Product amount : Rs. " + cartItems.getTotalPrice());
			}
		}
		}
		catch (NumberFormatException|NullPointerException|IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
