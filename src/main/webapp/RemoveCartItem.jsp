<%@page import="java.io.PrintWriter"%>
<%@page import="com.petshopapp.daoimpl.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.petshopapp.daoimpl.*"%>
<%@page import="java.util.*"%>
<%@page import="com.petshopapp.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%

    	int itemId=Integer.parseInt(request.getParameter("itemId"));		
        CartItemsDAO cartItemsDao=new CartItemsDAO();
        cartItemsDao.deleteCartItem(itemId);
        PrintWriter write=response.getWriter();
        write.print("Cart Item Removed");
%>
