package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDaoImpl;
import com.petshopapp.daoimpl.OrderItemsDaoImpl;
import com.petshopapp.daoimpl.OrdersDaoImpl;
import com.petshopapp.daoimpl.PetDaoImpl;
import com.petshopapp.exception.LowWalletBalance;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.model.Orders;
import com.petshopapp.model.PetDetails;

@WebServlet("/BuyNow")
public class BuyNow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	/**
	 * This method is used to buy pet item and update customer wallet and pet
	 * available quantity
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// session to get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		double wallet=0;
		double totalPrice=0;
		PrintWriter write =null;
		try {
			 write = response.getWriter();
			
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int petid = Integer.parseInt(request.getParameter("petid"));

		
			Orders orders = new Orders();
			OrdersDaoImpl ordersDao = new OrdersDaoImpl();

			OrderItems orderItems = new OrderItems();
			OrderItemsDaoImpl orderItemsDao = new OrderItemsDaoImpl();

			PetDaoImpl petDao = new PetDaoImpl();
			PetDetails pet = petDao.showCurrentPet(petid);

			CustomerDaoImpl customerDao = new CustomerDaoImpl();
			Customers petCustomerDetails = customerDao.customerDetails(pet.getCustomer().getCustomerId());

			// check customer wallet higher then or equal to pet price
			if (customerDetails.getWallet() >= (quantity * pet.getPetprice())) {
				if (pet.getAvilableQty() >= quantity) {
					orders.getCustomer().setCustomerId(customerDetails.getCustomerId());
					orders.setTotalprice((quantity * pet.getPetprice()));
					// insert values in orders
					ordersDao.insertOrder(orders);
					int orderId = ordersDao.getCurrentOrderId();
					orderItems.getOrders().setOrderId(orderId);
					orderItems.getPet().setPetId(pet.getPetId());
					orderItems.setQuantity(quantity);
					orderItems.setUnitPrice(pet.getPetprice());
					orderItems.setTotalPrice((quantity * pet.getPetprice()));
					// insert the values in order items
					orderItemsDao.insertOrderItems(orderItems);
					// update pet available quantity
					pet.setAvilableQty((pet.getAvilableQty() - quantity));
					petDao.updatePetAvailableQuantity(pet);
					// update buyer wallet
					customerDetails.setWallet(customerDetails.getWallet() - (quantity * pet.getPetprice()));
					customerDao.updateCustomerWallet(customerDetails);
					// update seller wallet
					petCustomerDetails.setWallet(petCustomerDetails.getWallet() + (quantity * pet.getPetprice()));
					customerDao.updateCustomerWallet(petCustomerDetails);
					write.print("Order placed sucussfully " + "\n Order Amount : Rs. " + (quantity * pet.getPetprice())
							+ "\n Current wallet Balance : Rs. " + customerDetails.getWallet());
				} else {
					write.print("Quantity not avilable");
				}
			} else {
					wallet=customerDetails.getWallet();
					totalPrice=quantity * pet.getPetprice();
					throw new LowWalletBalance();
			}
		} catch (NullPointerException | NumberFormatException | IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} catch (LowWalletBalance e) {
			write.print(e + "\n Current wallet balance : Rs. " + wallet
			+ "\n Product Amount : Rs. " + totalPrice);
         }
	}

}
