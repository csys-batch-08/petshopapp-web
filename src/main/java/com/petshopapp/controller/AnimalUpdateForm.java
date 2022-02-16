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

import com.petshopapp.daoimpl.PetDaoImpl;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/AnimalUpdateForm")
public class AnimalUpdateForm extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}  
	/**
	 * This method is used to update particular pet details
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");
		//To store and update
		PetDaoImpl petDao = new PetDaoImpl();
		PetDetails petDetails = new PetDetails();	
		try {
			petDetails.setPetQty(Integer.parseInt(request.getParameter("quantity")));
		// If quantity grater then 0, update pet details.
		if (petDetails.getPetQty() > 0) {
			// Update the pet values
			petDetails.setPetId(Integer.parseInt(request.getParameter("petid")));
			petDetails.setPetType(request.getParameter("animaltype").toLowerCase());
			petDetails.setPetName(request.getParameter("animalname").toLowerCase());
			petDetails.setPetGender(request.getParameter("animalgender").toLowerCase());
			SimpleDateFormat formet = new SimpleDateFormat("yyyy-mm-dd");
			Date date = formet.parse(request.getParameter("dob"));
		    petDetails.setPetDob(date);
			petDetails.setPetColor(request.getParameter("color").toLowerCase());
			petDetails.setPetprice(Double.parseDouble(request.getParameter("price")));
			petDetails.setPetImage(request.getParameter("imagelink"));
			petDetails.setDescription(request.getParameter("description"));
			petDetails.setAvilableQty(Integer.parseInt(request.getParameter("quantity")));
			petDetails.getCustomer().setCustomerId(customerDetails.getCustomerId());
			petDao.updatePetDetails(petDetails);
			request.setAttribute("message", "Pet details updated");
		}	
		else {
			request.setAttribute("message", "Invalid pet quantity");
		}
		// Redirect to message page
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("redirect.jsp");
	    requestDispatcher.forward(request, response);
		} catch (ServletException | IOException | ParseException|NullPointerException|
				NumberFormatException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}

	}

}
