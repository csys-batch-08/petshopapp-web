package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
public class BuyNow extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// session to get customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");

		// print writer for ajax response
		PrintWriter write = null;
		try {
			write = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// customer required quantity
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int petid = Integer.parseInt(request.getParameter("petid"));

		// order and orderdao objects used to store and update order details
		Orders orders = new Orders();
		OrdersDAO ordersDao = new OrdersDAO();

		// orderitems and orderitemsdao objects used to store and update order items
		// details
		OrderItems orderItems = new OrderItems();
		OrderItemsDAO orderItemsDao = new OrderItemsDAO();

		// petdao object used to get pet details
		PetDAO petDao = new PetDAO();
		PetDetails pet = petDao.showCurrentPet(petid);

		// customerdao used to update wallet of customer and get seller customer details
		CustomerDAO customerDao = new CustomerDAO();
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
		}

		else {

			try {
				throw new LowWalletBalance();
			} catch (LowWalletBalance e) {
				write.print(e + "\n Current wallet balance : Rs. " + customerDetails.getWallet()
						+ "\n Product Amount : Rs. " + (quantity * pet.getPetprice()));
			}

		}

	}

}
