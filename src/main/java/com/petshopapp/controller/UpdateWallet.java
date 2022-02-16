package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDaoImpl;
import com.petshopapp.exception.InvalidWalletAmount;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;

@WebServlet("/UpdateWallet")
public class UpdateWallet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	/**
	 * This method is used to update customer wallet
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter write = null;
		try {
			write = response.getWriter();
			// Get customer details
			HttpSession session = request.getSession();
			Customers customer = (Customers) session.getAttribute("customer");
			double wallet = Integer.parseInt(request.getParameter("wallet"));
			// check wallet amount grater then or equal 1000
			if (wallet < 1000) {
				throw new InvalidWalletAmount();
			}
			// customer wallet updation
			customer.setWallet(customer.getWallet() + wallet);
			CustomerDaoImpl customerDao = new CustomerDaoImpl();
			customerDao.updateCustomerWallet(customer);
			// response message
			write.print("Amount Added");

		} catch (IOException | NullPointerException | NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} catch (InvalidWalletAmount e) {
			write.print(e);
		}
	}
}
