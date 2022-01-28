<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
 <link rel="stylesheet" href="css/home.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- Header -->
	<header>
		<!-- Navigation bar -->

		<div class="navigation">

			<!-- Web site name and logo -->
			<h1>
				<i class="fas fa-paw" style="color: white;"></i> Pet Shop
			</h1>
				<!-- Search bar -->
		<form action="Search">
			<input type="search" name="search" id="searchinput"
				placeholder="Enter pet category or name">
			<button type="submit" id="search">search</button>
		</form>
			<nav>
				<!-- Menu bar -->
				<ul id="menu">
					<li><a href="myprofile.jsp">My Profile</a></li>
					<li><a href="mycart.jsp">My cart</a></li>
					<li><a href="myorders.jsp">My orders</a></li>
					<li><a href="additem.jsp">Add item</a></li>
					<li><a href="mypets.jsp">My pets</a></li>
					<li><a href="home.jsp">Home</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<!-- Pet list -->
	<div class="content">
		<h2 class="petlist">Pet Lists</h2>
		<table>
			<tbody>
				<tr>
					<jsp:useBean id="PetDao" class="com.petshopapp.daoimpl.PetDAO"/>                                
   					<c:set var="count" value="1" />
					  <c:forEach items="${PetDao.searchPetDetails(param.search,customer.getCustomerId())}" var="pet">
						<td>
							<table id="pets">
								<tbody>
									<!-- Pet Image -->
									<tr>
										<td><img src="./Pets/${pet.petImage}" alt="petimage"></td>

										<!-- Pet description -->
										<td id="petdetails">
											<p>Pet Type</p>
											<p>Pet Name</p>
											<p>Pet Color</p>
											<p>Unit price</p>
											<p>Available</p>
											<p style="visibility: hidden;">empty</p>
										</td>

										<!-- Pet description data -->
										<td>
											<p>: ${pet.petType}</p>
											<p>: ${pet.petName}</p>
											<p>: ${pet.petColor}</p>
											<p>: Rs. ${pet.petprice}</p>
											<p>: ${pet.avilableQty} Quantity</p>
											<p>
												<a href="petdescription.jsp?petid=${pet.petId}"><button>view</button></a>
											</p>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<c:choose>
						<c:when test="${count==2}">
						<c:set var="count" value="1" />
				</tr>
				<tr>
					</c:when>
					<c:otherwise>
						<c:set var="count" value="2" />
					</c:otherwise>
					</c:choose>
					</c:forEach>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>
</html>