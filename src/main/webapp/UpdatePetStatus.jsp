<%@page import="java.io.PrintWriter"%>
<%@page import="com.petshopapp.daoimpl.PetDAO"%>
<%@page import="com.petshopapp.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
int petId = Integer.parseInt(request.getParameter("petId"));
String status = request.getParameter("status");
Admin admin = (Admin) session.getAttribute("Admin");
PetDAO petDao = new PetDAO();
petDao.updatePetStatus(petId, status, admin.getAdminId());
PrintWriter write = response.getWriter();
write.print("Status Updated");
%>
