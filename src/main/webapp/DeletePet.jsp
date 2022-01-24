<%@page import="java.io.PrintWriter"%>
<%@page import="com.petshopapp.daoimpl.PetDAO"%>
<%@page import="java.util.*"%>
<%@page import="com.petshopapp.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
int petId=Integer.parseInt(request.getParameter("petId"));
PetDetails pet=new PetDetails();
PetDAO petdao=new PetDAO(); 
pet.setPetId(petId);
petdao.delete(pet);
PrintWriter write=response.getWriter();
write.print("Pet item deleted successfully");%>