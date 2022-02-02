package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/AddPet")
public class AddPet extends HttpServlet{
	

      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) {
      		doGet(request, response);
      		
      }
      
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) {
   	   
    	// Session for customer details
    	HttpSession session=request.getSession();
   		Customers customerDetails=(Customers) session.getAttribute("customer");
   		
   		// Getting all parameter from user add Pet form
   		String petType = request.getParameter("animaltype").toLowerCase();
   		String petName = request.getParameter("animalname").toLowerCase();
   		String petGender = request.getParameter("animalgender").toLowerCase();
   		String petDob = request.getParameter("dob");
   		SimpleDateFormat formet = new SimpleDateFormat("yyyy-mm-dd");    		
   		String petColor = request.getParameter("color").toLowerCase();	
   		String petImage = request.getParameter("imagelink");
   		String petDescription = request.getParameter("description");
   		double petPrice = Double.parseDouble(request.getParameter("price"));
   		int petQty = Integer.parseInt(request.getParameter("quantity"));
   		
   		//Check quantity is valid or not, Quantity must be grater than 0
   		if(petQty>0){
   		
        // Objects for storing and updating data
   		PetDetails petDetails = new PetDetails();
   		PetDAO petDao=new PetDAO();
   		
   		// store data in pet object
   		petDetails.setPetType(petType);
   		petDetails.setPetName(petName);
   		petDetails.setPetGender(petGender);
   		Date date;
			try {
				date = formet.parse(petDob);
			petDetails.setPetDob(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
   		petDetails.setPetColor(petColor);
   		petDetails.setPetprice(petPrice);
   		petDetails.setPetImage(petImage);
   		petDetails.setDescription(petDescription);
   		petDetails.setPetQty(petQty);
   		petDetails.setAvilableQty(petQty);
   		petDetails.getCustomer().setCustomerId(customerDetails.getCustomerId());
   		
   		// Call insert pet details method for insert data into database
   		petDao.insertPetDetails(petDetails);	
   		
   		//message about successful insert about adding new pet
   		request.setAttribute("message","Pet items add successfully");
   		}
   		else{
   			
   		//message about invalid quantity
   			request.setAttribute("message","Invalid added item quantity");
   		}
   		
   		//Send request for message page
   		RequestDispatcher requestDispatcher=request.getRequestDispatcher("redirect.jsp");
   		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
       		
       }

}
