<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="adminheader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="Petshop,pets,animals">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User List</title>
<link rel="stylesheet" href="./assets/css/userlist.css"></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>		
</head>
<body>
	<div>
		<h2 style="margin-top: 40px;">Customers List</h2>
	</div>
	<table class="table table-bordered" >
		<caption></caption>
		<thead>
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Gender</th>
				<th>User Name</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Register Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${customerList}" var="customer">
			<fmt:parseDate pattern="yyyy-MM-dd" value="${customer.getRegDate()}" var="parsedStatusDate" />
				<tr>
					<td class="text-capitalize">${customer.getCustomerId()}</td>
					<td class="text-capitalize">${customer.getFirstName()}</td>
					<td class="text-capitalize">${customer.getLastName()}</td>
					<td class="text-capitalize">${customer.getGender()}</td>
					<td>${customer.getUserName()}</td>
					<td>${customer.getEmail()}</td>
					<td>${customer.getNumber()}</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy" value="${customer.getRegDate()}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>