<%@page import="java.io.PrintWriter"%>
<%@page import="com.petshopapp.model.*"%>
<%@page import="com.petshopapp.daoimpl.*"%>
<%

String image=request.getParameter("image");
Customers customer=(Customers)session.getAttribute("customer");
customer.setImage(image);
CustomerDAO customerDao=new CustomerDAO();
customerDao.updateCustomerProfileImage(customer);
PrintWriter write=response.getWriter();
write.print("Profile picture updated");
%>