<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin profile</title>
<link rel="stylesheet" href="css/adminprofile.css"></link>
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
        
    
    <table>
        <tbody>
            <tr>
               <td rowspan="5"><img src="https://media1.popsugar-assets.com/files/thumbor/gMCOEkYB_3qTEDUrUbxtJkuusAA/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2017/07/12/947/n/1922153/c6f2e6f1596698606ad0d6.04019961_edit_img_image_15110365_1499895175/i/Chris-Hemsworth-Grooming-Interview.jpg" alt=""></td>
               <td>FirstName</td>
               <td> : ${Admin.getFirstName()}</td>
           </tr>
           <tr>   
               <td>LastName</td>
               <td> : ${Admin.getLastName()}</td>
           </tr>
            <tr>
                <td>UserName</td>
                <td> : ${Admin.getUserName()}</td>
            </tr>
              <tr>
                <td>Email</td>
                <td> : ${Admin.getEmail()}</td>
              </tr>
              <tr>
                <td>Number</td>
                <td> : ${Admin.getNumber()}</td>
              </tr>     
              <tr>
			<td><a href="Logout"><button type="button" id="logout">Logout</button></a></td>
			</tr>         
        </tbody>
    </table>

</body>
</html>