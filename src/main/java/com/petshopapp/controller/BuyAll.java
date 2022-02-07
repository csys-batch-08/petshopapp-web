package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/BuyAll")
public class BuyAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	/**
	 * This method is used to buy all cart items and update wallet of user and update available quantity
	 * of the pet
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Session for get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		CustomerDAO customerDao = new CustomerDAO();
		// objects for update insert values
		PetDAO petDao = new PetDAO();
		Orders orders = new Orders();
		OrdersDAO ordersDao = new OrdersDAO();
		OrderItems orderItems = new OrderItems();
		OrderItemsDAO orderItemsDao = new OrderItemsDAO();
		CartItemsDAO cartItemsDao = new CartItemsDAO();
		boolean flage = true;
		try {
			PrintWriter write = response.getWriter();
			double totalPrice = (Double) session.getAttribute("totalAmount");
			// Cart item for buy all cart items
			List<CartItems> cartList = cartItemsDao.showAllCartItems(customerDetails);
			// Check customer wallet higher or equal to total price
			if (customerDetails.getWallet() >= totalPrice) {
				for (CartItems cartItems : cartList) {
					PetDetails petDetails = petDao.showCurrentPet(cartItems.getPet().getPetId());
					if (petDetails.getAvilableQty() < cartItems.getQuantity()) {
						flage = false;
						write.print("\n Sorry we can't process this request now " + "\n Quantity not Avialble Pet Id "
								+ cartItems.getPet().getPetId());
					}
				}
				// if customer wallet and cart quantity available place order
				if (flage) {
					// Store the orders values
					orders.getCustomer().setCustomerId(customerDetails.getCustomerId());
					orders.setTotalprice(totalPrice);
					ordersDao.insertOrder(orders);
					// get current orderId
					int orderId = ordersDao.getCurrentOrderId();
					for (CartItems cartItems : cartList) {
						// Store the order items values
						orderItems.getOrders().setOrderId(orderId);
						orderItems.getPet().setPetId(cartItems.getPet().getPetId());
						orderItems.setQuantity(cartItems.getQuantity());
						orderItems.setUnitPrice(cartItems.getUnitPrice());
						orderItems.setTotalPrice(cartItems.getTotalPrice());
						orderItemsDao.insertOrderItems(orderItems);
						PetDetails petDetails = petDao.showCurrentPet(cartItems.getPet().getPetId());
						// update pet available quantity
						petDetails.setAvilableQty((petDetails.getAvilableQty() - cartItems.getQuantity()));
						petDao.updatePetAvailableQuantity(petDetails);
						// update seller wallet
						Customers petCustomerDetails = customerDao
								.customerDetails(petDetails.getCustomer().getCustomerId());
						petCustomerDetails.setWallet(petCustomerDetails.getWallet() + (cartItems.getTotalPrice()));
						customerDao.updateCustomerWallet(petCustomerDetails);
						cartItemsDao.deleteCartItem(cartItems.getItemId());
					}
					// update buyer wallet
					customerDetails.setWallet(customerDetails.getWallet() - totalPrice);
					customerDao.updateCustomerWallet(customerDetails);
					// message for ajax response
					write.print("Order placed successfully \n Deducted amount : RS." + totalPrice
							+ "0 \n Wallet Amount : Rs." + customerDetails.getWallet() + "0");
				}
			}
			// If cart quantity is not available throws exception
			else {
				try {
					throw new LowWalletBalance();
				} catch (LowWalletBalance e) {
					write.print(e + "\n Your wallet balance : Rs. " + customerDetails.getWallet()
							+ "0 \n Total cart amount : Rs. " + totalPrice);
				}
			}
		} catch (IOException | NullPointerException | NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
