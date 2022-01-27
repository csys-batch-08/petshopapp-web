<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Details</title>
<link rel="stylesheet" href="PetDescription.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/aeca6704b2.js" crossorigin="anonymous"></script>
    <style type="text/css">
* {
	margin: 0;
	padding: 0;
	color: white;
}

body {
	background-image: linear-gradient(rgba(0, 0, 0, .5) 50%,
		rgb(0, 0, 0, .5) 50%), url("./Images/background.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	background-attachment: fixed;
	text-transform: capitalize;
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

#searchinput {
	padding-left: 10px;
	position: absolute;
	font-size: 17px;
	top: 13px;
	left: 230px;
	width: 300px;
	height: 30px;
	border: 1px solid;
	border-color: tomato;
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
	background-color: transparent;
	border-right: none;
}

#searchinput::placeholder {
	color: white;
}

#search {
	position: absolute;
	left: 530px;
	width: 100px;
	height: 30px;
	margin-top: -2px;
	border: 1px solid;
	border-color: tomato;
	font-size: 17px;
	font-weight: bold;
	border-top-right-radius: 10px;
	border-left: none;
	background-color: tomato;
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
	font-size: 20px; font-weight : bold;
	text-decoration: none;
	transition: 0.5s;
	font-weight: bold;
}

#menu a:hover {
	color: black;
}

h2 {
	margin-top: 50px;
	margin-left: 30px;
	width: 150px;
	font-size: 30px;
}

table img{
	width: 500px;
	height: 400px;
}
table td p{
	line-height: 2;
	margin-left: 15px;
	font-size: 20px;
}

#description{
	width: 450px;
	position: absolute;
	font-size:20px;
	text-align:justify;
	top: 135px;
	left: 870px;
	line-height: 2;
}

#description button{
	width: 130px;
	height: 30px;
	font-size: 17px;
	font-weight: bold;
	color: white;
	background-color:  rgb(16, 177, 16);
	border: none;
	border-radius: 5px; 
	box-shadow: 0 0 5px black;
}

#quantity{
	width: 20px;
	height: 25px;
	border:none;
	border-radius: 5px;
	background-color: transparent;
	color: white;
	text-align: center;
    position: relative;
    top:-3px;
    left: 3px;
    outline: none;
}
/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}

/* Firefox */
input[type=number] {
	-moz-appearance: textfield;
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
	<h2>Pet Details</h2>
	 <jsp:useBean id="PetDao" class="com.petshopapp.daoimpl.PetDAO"/> 
	 <c:set var="petDescription" value="${PetDao.showCurrentPet(param.petid)}" scope="session"></c:set>
	
		<table>
			<tbody>
				<tr>
					<td><img src="./Pets/${petDescription.petImage}" alt=""></td>
					<td>
					    <p>
							<b>Id</b>
						</p>
					    <p>
							<b>Type</b>
						</p>
						<p>
							<b>Name</b>
						</p>
						<p>
							<b>Gender</b>
						</p>
						<p>
							<b>Date Of Birth</b>
						</p>
						<p>
							<b>Color</b>
						</p>
						<p>
							<b>Unit Price</b>
						</p>
						<p>
							<b>Available Qty</b>
						</p>
						<p>
							<b>Supplier Name</b>
						</p></td>
				 <fmt:parseDate pattern="yyyy-MM-dd" value="${petDescription.petDob}" var="parsedStatusDate" />
					<td><p>: ${petDescription.petId}</p>
						<p>: ${petDescription.petType}</p>
						<p>: ${petDescription.petName}</p>
						<p>: ${petDescription.petGender}</p>
				        <p>: <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedStatusDate}"/></p>
						<p>: ${petDescription.petColor}</p>
						<p>: ${petDescription.petprice}</p>
						<p>: ${petDescription.avilableQty}</p>
						<p>: ${petDescription.getCustomer().getFirstName()}</p>
					</td>

				</tr>
			</tbody>
		</table>

		<div id="description">
			<p style="	text-transform: none;">
				<b>Description: </b><br>${petDescription.description}
			<p>
			<p style="font-size: 27px;"><label>Quantity :</label>
				<span><i class="fas fa-minus-square" onclick="decrease()"></i><input type="number" id="quantity"
					max="${petDescription.avilableQty}" min="0" value="0" name="quantity">
	         	<i class="fas fa-plus-square"  onclick="increase(${petDescription.avilableQty})"></i></span>
			</p>
			<p>
				<button type="button" onclick="addToCart()"><i class="fas fa-cart-plus"></i> Cart</button>
				<button type="button" onclick="buyNow('${customer.address}')">Buy Now</button>
			</p>
			<p name="message" id="message">
			<p>
		</div>
		<tr>
			<script>
    
    
    function addToCart(){     
        let qty=document.getElementById("quantity").value;
        console.log(qty);
        if(qty>0){
        document.getElementById("message").innerHTML=" ";
    	var url="AddToCart.jsp?quantity="+qty;  
    	
    	if(window.XMLHttpRequest){  
    		request=new XMLHttpRequest();  
    		}  
    		else if(window.ActiveXObject){  
    		request=new ActiveXObject("Microsoft.XMLHTTP");  
    		}  
    	try  
    	{  
    	request.onreadystatechange=getInfo;  
    	request.open("GET",url,true);  
    	request.send();  
    	}  
    	catch(e)  
    	{  
    	alert("Unable to connect to server");  
    	}
        }
        else{
        	alert("invalid qty");
        }
    } 
     
    // buy Now
    function buyNow(address){  
					if (address == 'none') {
						var confirmAction = confirm("Please add address before buy");
						if (confirmAction) {
							  window.location = 'myprofile.jsp';
						}
						else{
							
						}
					} 
					else {
						var confirmAction = confirm("Are you sure you want buy this item");
						if (confirmAction) {
							console.log("called but");
							let qty = document.getElementById("quantity").value;
							console.log(qty);
							if (qty > 0) {
								document.getElementById("message").innerHTML = " ";
								var url = "BuyNow.jsp?quantity=" + qty;
								if (window.XMLHttpRequest) {
									request = new XMLHttpRequest();
								} else if (window.ActiveXObject) {
									request = new ActiveXObject(
											"Microsoft.XMLHTTP");
								}
								try {
									request.onreadystatechange = getInfo;
									request.open("GET", url, true);
									request.send();
								} catch (e) {
									alert("Unable to connect to server");
								}
							
						} 
							else {
							alert("Invalid quantity");
						}
						}
						else {
							  alert("Action canceled");
					}
				}
    }
    
    function getInfo(){  
    	if(request.readyState==4){  
    	var val=request.response;
    	   alert(val.trim()) ;
    	   if(val.includes("Low wallet balance")){
				  window.location = 'myprofile.jsp';
			}
    	}  
    	}	
				
				function increase(availableQuantity){
					var value= document.getElementById("quantity").value;
					if(value < availableQuantity){
						value++;
						document.getElementById("quantity").value=value;
					}
				}
               function  decrease(){
            	   var value= document.getElementById("quantity").value;
            	   if(value>0){
						value--;
						document.getElementById("quantity").value=value;
					}
				}
			</script>
</body>
</html>