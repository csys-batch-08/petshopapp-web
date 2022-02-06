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
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/AnimalUpdateForm")
public class AnimalUpdateForm extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Session for to get customer details and pet details
		HttpSession session = request.getSession();
		Customers customerDetails = (Customers) session.getAttribute("customer");

		// Pet DAO object for update the pet values
		PetDAO petDao = new PetDAO();

		// PetDetails object for to store the values
		PetDetails petDetails = new PetDetails();

		// User given quantity
		petDetails.setPetQty(Integer.parseInt(request.getParameter("quantity")));

		// Ensure user Given quantity greater then 0
		if (petDetails.getPetQty() > 0) {

			// Update the pet values
			petDetails.setPetId(Integer.parseInt(request.getParameter("petid")));
			petDetails.setPetType(request.getParameter("animaltype").toLowerCase());
			petDetails.setPetName(request.getParameter("animalname").toLowerCase());
			petDetails.setPetGender(request.getParameter("animalgender").toLowerCase());
			SimpleDateFormat formet = new SimpleDateFormat("yyyy-mm-dd");
			Date date;
			try {
				date = formet.parse(request.getParameter("dob"));
				petDetails.setPetDob(date);
			} catch (ParseException e) {

				e.printStackTrace();
			}

			petDetails.setPetColor(request.getParameter("color").toLowerCase());
			petDetails.setPetprice(Double.parseDouble(request.getParameter("price")));
			petDetails.setPetImage(request.getParameter("imagelink"));
			petDetails.setDescription(request.getParameter("description"));
			petDetails.setAvilableQty(Integer.parseInt(request.getParameter("quantity")));
			petDetails.getCustomer().setCustomerId(customerDetails.getCustomerId());
			petDao.updatePetDetails(petDetails);
			request.setAttribute("message", "Pet details updated");
		}
		// Invalid Quantity message
		else {
			request.setAttribute("message", "Invalid pet quantity");
		}

		// Redirect to message page
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("redirect.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

}
