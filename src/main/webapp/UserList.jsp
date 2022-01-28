<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
<link rel="stylesheet" href="css/userlist.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
*{
color: white;}</style>
</head>
<body>
<header>
        <div class="backgroundpage">
        <div class="head">
        <div class="navigation">
        <h1 style="font-size: 25px;"><i class="fas fa-paw" style="color: white;"></i> PET SHOP</h1>
        <nav>
        <ul  id="menu">
        <li><a href="adminprofile.jsp">My Profile</a></li>
        <li><a href="userlist.jsp">User List</a></li>
        <li><a href="adminhome.jsp">Home</a></li>
        </ul>
        </div>
        </nav>
</header>
        


	<div>
		<h2 style="margin-top: 40px;">Customers List</h2>
	</div>
	<table class="table table-bordered" style="width: 500px;">
		<thead>

			<tr>
				<th>Id</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Gender</th>
				<th>User Name</th>
				<th>Email</th>
				<th>Number</th>
			</tr>
		</thead>
		<tbody>
		<jsp:useBean id="CustomerDao" class="com.petshopapp.daoimpl.CustomerDAO"/> 
		<c:forEach items="${CustomerDao.customersList()}" var="customer">		
			<tr>
				<td class="text-capitalize">${customer.getCustomerId()}</td>
				<td class="text-capitalize" >${customer.getFirstName()}</td>
				<td class="text-capitalize" >${customer.getLastName()}</td>
				<td class="text-capitalize">${customer.getGender()}</td>
				<td>${customer.getUserName()}</td>
				<td>${customer.getEmail()}</td>
				<td>${customer.getNumber()}</td>
			</tr>
				</c:forEach>   
		</tbody>
	</table>
</body>
</html>