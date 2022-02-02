<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@include file="adminheader.jsp" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin profile</title>
<link rel="stylesheet" href="./assets/css/adminprofile.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js" ></script>
</head>
<body>
     
	<div id="data">
		<div id="image">
			<img src="./assets/images/profile picture/admin.jpg" alt="admin image">
			<p>
				<a href="Logout"><button type="button" id="logout">Logout</button></a>
			</p>
		</div>
		<div id="divcontent">
		    <p>Admin Id</p>
			<p>First Name</p>
			<p>Last Name</p>
			<p>User Name</p>
			<p>Email</p>
			<p>Mobile</p>
			<p>Register Date</p>
		</div>
		<div id="divdata">
		    <p>: ${Admin.getAdminId()}</p>
			<p>: ${Admin.getFirstName()}</p>
			<p>: ${Admin.getLastName()}</p>
			<p>: ${Admin.getUserName()}</p>
			<p>: ${Admin.getEmail()}</p>
			<p>: ${Admin.getNumber()}</p>
	<fmt:parseDate pattern="yyyy-MM-dd" value=" ${Admin.getRegisterDate()}" var="parsedStatusDate" />
		
			<p>: <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedStatusDate}" /></p>
		</div>
	</div>
</body>
</html>