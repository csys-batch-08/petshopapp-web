<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="headerall.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>My Pets</title>
<link rel="stylesheet" href="css/mypets.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"></script>
</head>
<body>

	<!-- My pet list -->
	<h2>My Pet List</h2>
	<c:forEach items="${myPetList}" var="pet">
		<div id="data">
			<div id="image">
				<img src="./Pets/${pet.petImage}" alt="petimage">
			</div>
			<div id="divcontent">
				<p>Name</p>
				<p>Color</p>
				<p>price</p>
				<p>Total Qty</p>
				<p>Sold Qty</p>
				<p>Aavilable Qty</p>
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
				<p>: Rs. ${pet.petprice}</p>
				<p>: ${pet.petQty}</p>
				<p>: ${pet.petQty-pet.avilableQty}</p>
				<p>: ${pet.avilableQty}</p>
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
	<script type="text/javascript" src="js/mypets.js"></script>
</body>
</html>