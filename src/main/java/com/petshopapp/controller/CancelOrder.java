package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDaoImpl;
import com.petshopapp.daoimpl.OrderItemsDaoImpl;
import com.petshopapp.daoimpl.OrdersDaoImpl;
import com.petshopapp.daoimpl.PetDaoImpl;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.model.Orders;
import com.petshopapp.model.PetDetails;

@WebServlet("/CancelOrder")
public class CancelOrder extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	/**
	 * This method is used to cancel particular order and update customer wallet and pet available quantity
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// session used for customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");

		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		Customers customer = new Customers();

		// petdetails and petdao for update pet available qantity
		PetDetails pet = new PetDetails();
		PetDaoImpl petDao = new PetDaoImpl();

		// order item and order objects are used cancel orders and order items
		OrderItemsDaoImpl orderItemDao = new OrderItemsDaoImpl();

		Orders order = new Orders();
		OrdersDaoImpl orderDao = new OrdersDaoImpl();

		try {
			// print writer for ajax response
			PrintWriter write = response.getWriter();
			// order id for cancel id
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			order.setOrderId(orderId);
			List<OrderItems> orderItemList = orderItemDao.getCurrentOrderItemDetails(orderId);
			int amount = 0;
			for (OrderItems orderItem : orderItemList) {
				// get order item pet details
				pet = petDao.showCurrentPet(orderItem.getPet().getPetId());
				// update pet quantity
				pet.setAvilableQty((pet.getAvilableQty() + orderItem.getQuantity()));
				petDao.updatePetAvailableQuantity(pet);
				// refund amount
				amount += orderItem.getTotalPrice();
				// update buyer customer wallet
				customerDetails.setWallet(customerDetails.getWallet() + (orderItem.getTotalPrice()));
				customerDao.updateCustomerWallet(customerDetails);
				// update seller wallet
				customer = customerDao.customerDetails(pet.getCustomer().getCustomerId());
				customer.setWallet(customer.getWallet() - orderItem.getTotalPrice());
				customerDao.updateCustomerWallet(customer);
			}
			orderDao.updateOrderStatus(order);
			// ajax response message
			write.print("order cancelld " + "\n credit amount :" + amount + "\n Total Wallet balance :"
					+ customerDetails.getWallet());
		} catch (NullPointerException | NumberFormatException | IOException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}
}
