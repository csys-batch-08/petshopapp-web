<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Pets</title>
<link rel="stylesheet" href="MyPets.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>
<style type="text/css">

/*Page margin setup */
* {
	margin: 0;
	padding: 0;
	color: white;
}
/*Background styles */
body {
	background-image: linear-gradient(rgba(0, 0, 0, .4) 50%,
		rgb(0, 0, 0, .4) 50%), url("./Images/background6.jpg");
    background-repeat: no-repeat;
	background-position:center;
	background-size: cover;
	background-attachment: fixed;
	text-transform: capitalize;
	font-weight:bold;
	color: white;
}

/* Navigation styles */
.navigation {
	padding-top: 15px;
	font-family: sans-serif;
}

h1 {
	display: inline;
	width: 300px;
	position: absolute;
	top: 10px;
	left: 10px;
	font-size: 25px;
}

#search:hover {
	background-color: white;
	border-color: white;
	color: black;
}

#menu li {
	display: inline;
}

#menu a {
	float: right;
	padding-right: 20px;
	margin-top: 2px;
	font-size: 20px;
	font-weight: bold;
	text-decoration: none;
	transition: 0.5s;
	font-weight: bold;
}

#menu a:hover {
	color: black;
}


/* My pet list styles */
#content {
	margin-top: 40px;
}

#content h2 {
	margin-top: 30px;
	margin-left: 20px;
	width: 150px;
	font-size: 30px;
	width: 150px;
}

.petlist {
	margin-top: 40px;
	padding-top: 20px;
	margin-left: 50px;
	transition: 0.5s;
}

h2:hover {
	color: black;
}

#pets {
	margin-left: 20px;
	margin-top: 20px;
	line-height: 1.5;
	font-size: 20px;
	background-color: transparent;
	border-radius: 10px;
}

#pets img {
	width: 320px;
	height: 250px;
	border-radius: 10px;
}

#pets p {
	margin-left: 10px;
	color: white;
}

#pets p a {
	margin-left: -10px;
}

#pets button {
	margin-left: 10px;
	margin-top: 5px;
	width: 120px;
	height: 30px;
	background-color: rgb(16, 177, 16);
	border-radius: 10px;
	border: none;
	color: white;
	font-size: 17px;
	font-weight: bold;
	box-shadow: 0 0 5px black;
}

#pets button:hover {
	background-color: white;
	color: black;
}
</style>

</head>
<body>
<!-- Header -->
<header>
	<div class="navigation">
	<!-- Logo -->
		<h1>
			<i class="fas fa-paw" style="color: white;"></i> Pet Shop
		</h1>
	<!-- Navigation -->
		<nav>
		<ul id="menu">
			<li><a href="myprofile.jsp">My Profile</a></li>
			<li><a href="mycart.jsp">My cart</a></li>
			<li><a href="myorders.jsp">My orders</a></li>
			<li><a href="AddItem.jsp">Add item</a></li>
			<li><a href="MyPets.jsp">My pets</a></li>
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
					<c:forEach items="${sessionScope.myPetList}" var="pet">
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
										<p><a href="EditPet.jsp?petid=${pet.petId}"><button
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
	<script type="text/javascript">
	
<!--Delete my pet ajax -->

		function deletePet(petId) {
			var confirmAction = confirm("Are you sure you want delete this item");
			if (confirmAction) {
				console.log("called buy");
				var url = "DeletePet.jsp?petId=" + petId;
				if (window.XMLHttpRequest) {
					request = new XMLHttpRequest();
				} else if (window.ActiveXObject) {
					request = new ActiveXObject("Microsoft.XMLHTTP");
				}
				try {
					request.onreadystatechange = getInfo;
					request.open("GET", url, true);
					request.send();
				} catch (e) {
					alert("Unable to connect to server");
				}
			} else {
				alert("Action canceled");
			}
		}
				
<!--Delete my pet ajax response -->
		function getInfo() {
			if (request.readyState == 4) {
				var val = request.responseText;
				alert(val);
				location.reload();
				//document.getElementById('messageall').innerHTML = val;
			}
		}
	</script>
</body>
</html>