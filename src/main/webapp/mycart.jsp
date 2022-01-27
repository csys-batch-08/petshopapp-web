<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Cart</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	color: white;
}

body {
	background-image: linear-gradient(rgba(0, 0, 0, .4) 50%,
		rgb(0, 0, 0, .4) 50%), url("./Images/myordersbackground.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	text-transform: capitalize;
	color: white;
}

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

h2 {
	margin-top: 60px;
	margin-left: 40px;
	width: 150px;
	font-size: 30px;
	width: 150px;
}

h2:hover {
	color: black;
}

table {
	font-size: 20px;
	margin-top: 20px;
}

table p {
	margin-left: 20px;
}

table img {
	width: 400px;
	height: 350px;
	margin-left: 30px;
}

table tr, th, td {
	line-height: 2;
}

#buynow {
	width: 120px;
	height: 30px;
	font-size: 15px;
	font-weight: bold;
	color: white;
	border: none;
	border-radius: 5px;
	background-color: rgb(16, 177, 16);
	box-shadow: 0 0 5px black;
}

#view {
	width: 120px;
	height: 30px;
	font-size: 15px;
	font-weight: bold;
	color: white;
	border: none;
	border-radius: 5px;
	background-color: rgb(16, 177, 16);
	box-shadow: 0 0 5px black;
}

#remove {
	width: 120px;
	height: 30px;
	font-size: 15px;
	font-weight: bold;
	color: white;
	border: none;
	border-radius: 5px;
	background-color: tomato;
	box-shadow: 0 0 5px black;
}

#buyall {
	margin-left: 450px;
	margin-top: 30px;
	width: 120px;
	height: 30px;
	font-size: 15px;
	font-weight: bold;
	color: white;
	border: none;
	border-radius: 5px;
	background-color: rgb(16, 177, 16);
	box-shadow: 0 0 5px black;
}

#removeall {
	margin-left: 20px;
	margin-top: 30px;
	width: 120px;
	height: 30px;
	font-size: 15px;
	font-weight: bold;
	color: white;
	border: none;
	border-radius: 5px;
	background-color: tomato;
	box-shadow: 0 0 5px black;
}

#empty {
	position: absolute;
	margin-top: 40px;
	margin-left: 40px;
	font-size: 30px;
}

#move p {
	margin-left: -200px;
}
</style>
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
			<li><a href="MyProfile.jsp">My Profile</a></li>
			<li><a href="MyCart.jsp">My cart</a></li>
			<li><a href="MyOrders.jsp">My orders</a></li>
			<li><a href="AddItem.jsp">Add item</a></li>
			<li><a href="MyPets.jsp">My pets</a></li>
			<li><a href="Home.jsp">Home</a></li>
		</ul>
	</nav>
	</div>
</header>
	<h2>My Cart</h2>

	<table>
	<c:set var="totalAmount" value="0" scope="session"></c:set>
		<c:set var="count" value="0"></c:set>
	<jsp:useBean id="cartItemsDAO" class="com.petshopapp.daoimpl.CartItemsDAO"/> 
	 <c:set var="cartList" value="${cartItemsDAO.showAllCartItems(customer)}" scope="session"></c:set>
	
	<c:forEach items="${cartList}" var="cart">
	<c:set var="totalAmount" value="${totalAmount + cart.getTotalPrice()}" scope="session"></c:set>	
			<c:set var="count" value="${count + 1}"></c:set>
		<tr>
			<td><img src="./Pets/${cart.getPet().getPetImage()}"
				alt="petimage"></td>

			<td>
				<p>Pet id</p>
				<p>Pet Type</p>
				<p>Pet Name</p>
				<p>Available Quantity</p>
				<p>Cart Quantity</p>
				<p>Unit_price</p>
				<p>Total price</p>

				<p>
					<a
						href="PetDescription.jsp?petid=${cart.getPet().getPetId()}"><button
							id="view">View</button></a>
					<button type="button" id="buynow"
						onclick="buy('${cart.getItemId()}','${cart.getPet().getAvilableQty()}','${cart.getQuantity()}','${customer.getAddress()}')">Buy Now</button>
					<button type="button"
						onclick="removeCart('${cart.getItemId()}')" id="remove">
						<i class="fas fa-trash-alt"></i> Remove
					</button>
					
			<td id="move"><p>
					<span id="itemid" style="display: none">${cart.getItemId()}</span>
				</p>
				<p>
					: ${cart.getPet().getPetId()}
					</p>
				<p>
					: ${cart.getPet().getPetType()}
					</p>
				<p>
					: ${cart.getPet().getPetName()}
					</p>
				<p>
					: ${cart.getPet().getAvilableQty()}
					</p>			
				<p>
					: <span id="quantity">${cart.getQuantity()}</span>
				</p>
				<p>
					: ${cart.getUnitPrice()}
					</p>
				<p>

					: ${cart.getTotalPrice()} 
					</p>
				<p style="visibility: hidden;">empty</p></td>

		</tr>
		
		</c:forEach>

	
	<c:if test ="${count > 1 }">
	<tr>
	<td colspan="3">
	<button id="buyall" onclick="buyAll('${customer.getAddress()}')">Buyall</button>
	<button id="removeall" onclick="removeAll()">
		<i class="fas fa-trash-alt"></i> Remove All
	</button>
	</td>
	</tr>
	 </c:if>
	 <c:if test ="${count == 0 }">
	 <h1 id="empty" style="margin-top: 140px">Your cart is empty</h1>	   
     </c:if>	
	</table>
	<script type="text/javascript">
		
		//buy all
		function buyAll(address) {
					
			if(address!='none'){				
		    var confirmAction = confirm("Are you sure you want buy  all this item");
		       if (confirmAction) {
			   console.log("called");
		       var url = "BuyAll.jsp";
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
		    }
		   else{
			   alert("Action canceled");
		   }
		   }
		   else{

				var confirms = confirm("Please add address before buy");
				if (confirms) {
					  window.location = 'myprofile.jsp';
				}
				else{
					
				}
			}
		}

		//buy
		function buy(itemId,availableQuantity,cartQuantity,price,address) {

			if(availableQuantity>=cartQuantity){
			if (address != 'none') {
				var confirmAction = confirm("Are you sure you want buy this item");
				if (confirmAction) {

					console.log("called buy");
					console.log(itemId);
					var url = "CartBuy.jsp?itemId=" + itemId;
					console.log(url);
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

			} else {
				
				var confirms = confirm("Please add address before buy");
				if (confirms) {
					  window.location = 'myprofile.jsp';
				}
				else{
					
				}
			}
			}
			else{
				alert("Sorry pet item not available now");
			}
		}

		// remove cart
		function removeCart(itemId) {
			var confirmAction = confirm("Are you sure you want remove this item");
			if (confirmAction) {

				console.log("called buy");
				console.log(itemId);
				var url = "RemoveCartItem.jsp?itemId=" + itemId;
				console.log(url);
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

		//remove all
		function removeAll() {
			var confirmAction = confirm("Are you sure you want remove all this item");
			if (confirmAction) {
				var url = "RemoveAllCartItems.jsp";
				console.log(url);
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

		function getInfo() {
			if (request.readyState == 4) {
				var val = request.responseText;
				alert(val.trim()); 
				if(val.includes('Low wallet')){
					  window.location = 'myprofile.jsp';
				}
				else{
				location.reload();
				}
			}
		}
	</script>
</body>
</html>