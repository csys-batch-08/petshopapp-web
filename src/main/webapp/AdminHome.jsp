
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
    <link rel="stylesheet" href="css/adminhome.css"></link>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/aeca6704b2.js" crossorigin="anonymous"></script>

</head>
<body>
<header>
        <div class="backgroundpage">
        <div class="head">
        <div class="navigation">
        <h1><i class="fas fa-paw" style="color: white;"></i> PET SHOP</h1>
        <nav>
        <ul  id="menu">
        <li><a href="adminprofile.jsp">My Profile</a></li>
        <li><a href="userlist.jsp">User List</a></li>
        <li><a href="adminhome.jsp">Home</a></li>
        </ul>
        </div>
        </nav>
</header>
        
        <h2 class="petlist">Not Approved Pet List</h2>
        
        <div class="not approved list">
        <table>
            <tbody>
                <tr>
                <jsp:useBean id="PetDao" class="com.petshopapp.daoimpl.PetDAO"/>               
                	<c:set var="count" value="1" />  
                 <c:forEach items="${PetDao.showNotApprovedPetList()}" var="pet">
                    <td id="${count}">
                        <table id="pets">
                            <tbody>
                                <tr>
                                    <td><img src="./Pets/${pet.getPetImage()}" alt="pet image"></td>    
                                    <td class="petdetails">
                                        <pre>Type       : ${pet.getPetType()}</pre>
                                        <pre>Name     : ${pet.getPetName()}</pre>
                                        <pre>Color      : ${pet.getPetColor()}</pre>
                                        <pre>price       : Rs. ${pet.getPetprice()}</pre>
                                        <pre>Quantity : ${pet.getAvilableQty()}</pre>
                                        <pre>Status    : ${pet.getStatus()}</pre>
                                        <button type="button" onclick="UpdateStatus('${pet.getPetId()}','approved')">accept</button>
                                        <button type="button" onclick="UpdateStatus('${pet.getPetId()}','declined')">decline</button>
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
    </div>
   </div>
      
   <script type="text/javascript"- src="js/adminhome.js"> </script>
</body>
</html></html>