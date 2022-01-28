<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Pets</title>
<link rel="stylesheet" href="css/mypets.css"></link>
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
	
<!-- My pet list -->
	<div id="content">
		<h2>My Pet List</h2>
		<table>
			<tbody>
				<tr>
					<c:set var="count" value="1" />
					<jsp:useBean id="PetDao" class="com.petshopapp.daoimpl.PetDAO"/> 
					<c:forEach items="${PetDao.showMypetdetails(customer.getCustomerId())}" var="pet">
					<td>
						<table id="pets">
							<tbody>
								<tr>
								<!-- Pet image  -->
									<td><img src="./Pets/${pet.petImage}"
										alt="petimage"></td>
								<!-- Pet description -->
									<td class="petdetails">
										<p>Name</p>
										<p>Color</p>
										<p>price</p>
										<p>Total Qty</p>
										<p>Sold Qty</p>
										<p>Aavilable Qty</p>
										<p>Status</p> 
										<c:if test = "${pet.status == 'Not approved'}">                                                                                                
										<p><a href="editpet.jsp?petid=${pet.petId}"><button
													type="button">Edit</button></a></p> 													
												</c:if> 
									</td>
                       <!-- Pet description data -->
									<td class="petdetails">
										<p> : ${pet.petName}</p>
										<p>	: ${pet.petColor}</p>
										<p>	: Rs. ${pet.petprice}</p>
										<p>	: ${pet.petQty}</p> 
					
										<p>	: ${pet.petQty-pet.avilableQty}</p>
										<p>	: ${pet.avilableQty}</p>
										<p>	: ${pet.status}</p> 
							<c:if test = "${pet.status == 'Not approved'}"> 
										<p>	<a href=""><button type="button" onclick="deletePet('${pet.petId}')">Delete</button></a>
										</p>
									</c:if>
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
	<script type="text/javascript" src="js/mypets.js"></script>
</body>
</html>