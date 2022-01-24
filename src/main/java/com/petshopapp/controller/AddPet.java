package com.petshopapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petshopapp.daoimpl.CustomerDAO;
import com.petshopapp.daoimpl.PetDAO;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

@WebServlet("/AddPet")
public class AddPet extends HttpServlet{
	
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		HttpSession session=request.getSession();
    		PrintWriter out=response.getWriter();
    		Customers customerDetails=(Customers) session.getAttribute("customer");
    		String petType = request.getParameter("animaltype").toLowerCase();
    		String petName = request.getParameter("animalname").toLowerCase();
    		String petGender = request.getParameter("animalgender").toLowerCase();
    		String petDob = request.getParameter("dob");
    		SimpleDateFormat formet = new SimpleDateFormat("yyyy-mm-dd");    		
    		String petColor = request.getParameter("color").toLowerCase();
    		double petPrice = Double.parseDouble(request.getParameter("price"));
    		String petImage = request.getParameter("imagelink");
    		String petDescription = request.getParameter("description");
    		int petQty = Integer.parseInt(request.getParameter("quantity"));
    		if(petQty>0){
    		PetDetails petDetails = new PetDetails();
    		PetDAO petDao=new PetDAO();
    		petDetails.setPetType(petType);
    		petDetails.setPetName(petName);
    		petDetails.setPetGender(petGender);
    		Date date;
			try {
				date = formet.parse(petDob);
			petDetails.setPetDob(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		petDetails.setPetColor(petColor);
    		petDetails.setPetprice(petPrice);
    		petDetails.setPetImage(petImage);
    		petDetails.setDescription(petDescription);
    		petDetails.setPetQty(petQty);
    		petDetails.setAvilableQty(petQty);
    		petDetails.getCustomer().setCustomerId(customerDetails.getCustomerId());
    		petDao.insertPetDetails(petDetails);	
            
    		out.print("	<script type=\"text/javascript\">alert('Pet items add successfully');"
	            		+ "window.location = 'AddItem.jsp';</script>");
    		}
    		else{
    			out.print("	<script type=\"text/javascript\">alert('Invalid quantity');"
	            		+ "window.location = 'AddItem.jsp';</script>");
    		}
    }
  
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doGet(req, resp);
    }
}
