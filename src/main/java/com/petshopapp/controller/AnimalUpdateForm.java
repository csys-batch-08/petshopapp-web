package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class AnimalUpdateForm extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	   HttpSession session=request.getSession();
           PrintWriter write=response.getWriter();
           int petId=Integer.parseInt(request.getParameter("petid"));
   		PetDetails petDetails = new PetDetails();
   		PetDAO petDao=new PetDAO();
   		petDetails.setPetId(petId);	
   		Customers customerDetails=(Customers) session.getAttribute("customer");
   		String petType = request.getParameter("animaltype").toLowerCase();
   		String petName = request.getParameter("animalname").toLowerCase();
   		String petGender = request.getParameter("animalgender").toLowerCase();
   		String petDob = request.getParameter("dob");
   		SimpleDateFormat formet = new SimpleDateFormat("yyyy-mm-dd");
   		Date date;
		try {
			date = formet.parse(petDob);
			petDetails.setPetDob(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
   		String petColor = request.getParameter("color").toLowerCase();
   		double petPrice = Double.parseDouble(request.getParameter("price"));
   		String petImage = request.getParameter("imagelink");
   		String petDescription = request.getParameter("description");
   		int petQty = Integer.parseInt(request.getParameter("quantity"));
   		if(petQty>0){
   		
   		petDetails.setPetType(petType);
   		petDetails.setPetName(petName);
   		petDetails.setPetGender(petGender);
   	
   		petDetails.setPetColor(petColor);
   		petDetails.setPetprice(petPrice);
   		petDetails.setPetImage(petImage);
   		petDetails.setDescription(petDescription);
   		petDetails.setPetQty(petQty);
   		petDetails.setAvilableQty(petQty);
   		petDetails.getCustomer().setCustomerId(customerDetails.getCustomerId());
   		petDao.updatePetDetails(petDetails);	
   		write.print("<script type=\"text/javascript\">alert('Pet Details updated');window.location = 'mypets.jsp';</script>");
   		}
   		
   		else{
   			write.print("<script type=\"text/javascript\">alert('Invalid quantity');window.location = 'editpet.jsp';</script>");
   			write.println("Invalid quantity");
   		}
           
    }
      
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
    	doGet(req, resp);
    }
}
