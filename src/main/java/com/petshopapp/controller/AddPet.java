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

@WebServlet("/AddPet")
public class AddPet extends HttpServlet{
	
      @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    	    HttpSession session=request.getSession();
    		Customers customerDetails=(Customers) session.getAttribute("customer");
    		String petType = request.getParameter("animaltype").toLowerCase();
    		String petName = request.getParameter("animalname").toLowerCase();
    		String petGender = request.getParameter("animalgender").toLowerCase();
    		String petDob = request.getParameter("dob");
    		SimpleDateFormat formet = new SimpleDateFormat("yyyy-mm-dd");    		
    		String petColor = request.getParameter("color").toLowerCase();	
    		String petImage = request.getParameter("imagelink");
    		String petDescription = request.getParameter("description");
    		PrintWriter out=response.getWriter();
    		double petPrice = Double.parseDouble(request.getParameter("price"));
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
	            		+ "window.location = 'additem.jsp';</script>");
    		}
    		else{
    			out.print("	<script type=\"text/javascript\">alert('Invalid quantity');"
	            		+ "window.location = 'additem.jsp';</script>");
    		}
    }

}
