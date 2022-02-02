package com.petshopapp.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Logout")
public class Logout extends HttpServlet{
	

      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) {
      		doGet(request, response);
      		
      }
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	   
    	   request.getSession().invalidate();
     	  try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
       		
       }
      
    
}
