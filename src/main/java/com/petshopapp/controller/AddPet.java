package com.petshopapp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/AddPet")
public class AddPet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	/**
	 * This method is used to get the pet details from customer add insert into pet
	 * details table in the database
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Session for customer details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		SimpleDateFormat formet = new SimpleDateFormat("yyyy-mm-dd");
		// Objects for storing and updating data
		PetDetails petDetails = new PetDetails();
		PetDAO petDao = new PetDAO();		
		try {
			petDetails.setPetQty(Integer.parseInt(request.getParameter("quantity")));
			if(petDetails.getPetQty()>0) {
			petDetails.setPetType(request.getParameter("animaltype").toLowerCase());
			petDetails.setPetName(request.getParameter("animalname").toLowerCase());
			petDetails.setPetGender(request.getParameter("animalgender").toLowerCase());
			String petDob = request.getParameter("dob");
			Date date = formet.parse(petDob);
			petDetails.setPetDob(date);
			petDetails.setPetColor(request.getParameter("color").toLowerCase());
			petDetails.setPetprice(Double.parseDouble(request.getParameter("price")));
			petDetails.setPetImage(request.getParameter("imagelink"));
			petDetails.setDescription(request.getParameter("description"));
			petDetails.setAvilableQty(Integer.parseInt(request.getParameter("quantity")));
			petDetails.getCustomer().setCustomerId(customerDetails.getCustomerId());
			petDao.insertPetDetails(petDetails);
			request.setAttribute("message", "Pet items add successfully");
		} else {
			request.setAttribute("message", "Invalid added item quantity");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("redirect.jsp");
		requestDispatcher.forward(request, response);
	}
	catch(ServletException | NullPointerException | NumberFormatException | IOException | ParseException e)
	{
		Logger.printStackTrace(e);
		Logger.runTimeException(e.getMessage());
	}
}
}
