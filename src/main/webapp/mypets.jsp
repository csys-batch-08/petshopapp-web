<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="keywords" content="Petshop,pets,animals">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Pets</title>
<link rel="stylesheet" href="./assets/css/header.css"></link>
<link rel="stylesheet" href="./assets/css/mypets.css"></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>
	<h2>My Pet List</h2>
	<c:forEach items="${myPetList}" var="pet">
		<div id="data">
			<div id="image">
				<img src="./assets/images/pets/${pet.petImage}" alt="petimage">
			</div>
			<div id="divcontent">
				<p>Name</p>
				<p>Color</p>
				<p>price</p>
				<p>Total</p>
				<p>Sold</p>
				<p>Status</p>
				<c:if test="${pet.status == 'Not approved'}">
					<p style="padding: 0;">
						<a href="EditPet?petid=${pet.petId}"><button type="button">Edit</button></a>
					</p>
				</c:if>
			</div>
			<div id="divdata">
				<p>: ${pet.petName}</p>
				<p>: ${pet.petColor}</p>
				<p>: Rs. ${pet.petprice}0</p>
				<p>: ${pet.petQty} Quantity</p>
				<p>: ${pet.petQty-pet.avilableQty} Quantity</p>
				<p>: ${pet.status}</p>
				<c:if test="${pet.status == 'Not approved'}">
					<p>
						<a href=""><button type="button"
								onclick="deletePet('${pet.petId}')">Delete</button></a>
					</p>
				</c:if>
			</div>
		</div>
	</c:forEach>
	<script type="text/javascript" src="./assets/js/mypets.js"></script>
</body>
</html>