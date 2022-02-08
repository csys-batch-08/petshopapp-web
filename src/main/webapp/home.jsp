<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="keywords" content="Petshop,pets,animals">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<link rel="stylesheet" href="./assets/css/header.css"></link>
<link rel="stylesheet" href="./assets/css/home.css"></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
</head>
<body>
	<h2>Pet Lists</h2>
	<c:forEach items="${PetList}" var="pet">
		<div id="data">
			<div id="image">
				<img src="assets/images/pets/${pet.petImage}" alt="petimage">
			</div>
			<div id="divcontent">
				<p>Pet Type</p>
				<p>Pet Name</p>
				<p>Pet Color</p>
				<p>Unit price</p>
				<p>Available</p>
				<p>
					<a href="PetDescription?petid=${pet.petId}"><button>view</button></a>
				</p>
			</div>
			<div id="divdata">
				<p>: ${pet.petType}</p>
				<p>: ${pet.petName}</p>
				<p>: ${pet.petColor}</p>
				<p>: Rs. ${pet.petprice}0</p>
				<p>: ${pet.avilableQty} Quantity</p>
				<p style="visibility: hidden;">empty</p>
			</div>
		</div>
	</c:forEach>
</body>
</html>
