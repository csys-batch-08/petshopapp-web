<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register User</title>
 <link rel="stylesheet" href="css/register.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>

<style type="text/css">

</style>
</head>
<body>

	<div id="background">
	
<!-- Web site Name and Logo-->
		
		<h1>
			<i class="fas fa-paw" style="color: white;"></i> Pet Shop
		</h1>

		<form action="register" name="registerform" class="registerform" method="post">
			<div id="image">
				<img
					src="./Images/background9.jpeg"
					alt="back">
			</div>
	
<!--register Table-->	
			<table>
			<caption></caption>
				<thead>				
					<tr>
						<th id="registername" colspan="2">Register here</th>
						<th></th>
					</tr>
					<tr>
						<td><label for="firstname">FirstName <span>*</span></label></td>
						<td><input type="text" name="firstname" id="firstnameinput"
							pattern="[a-zA-Z]{3,20}" title="minimum 3 characters and accept only alphabets"
							placeholder="Firstname" required>
						<p id="firstnamecomment"></p></td>
					</tr>
					<tr>
						<td><label for="lastname">LastName <span>*</span></label></td>
						<td><input type="text" name="lastname" id="lastnameinput"
							pattern="[a-zA-Z]{3,20}" title="minimum 3 characters accept only alphabets"
							placeholder="Lastname" required></td>
					</tr>
					<tr>
						<td><label for="Gendr">Gender <span>*</span></label></td>
						<td><select name="gender" id="genderlist" required="required">
								<option value="Male">male</option>
								<option value="Female">female</option>
								<option value="others">others</option>
						</select></td>
					</tr>
					<tr>
						<td><label for="email">Email <span>*</span></label></td>
						<td><input type="email" name="email" id="emailinput"
							onchange="validateEmail()"
							pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
							placeholder="Email" required></td>
					</tr>
					<tr>
						<td><label for="username">UserName <span>*</span></label></td>
						<td><input type="text" name="username"
							onchange="validateUsername()" id="usernameinput"
							placeholder="username" pattern="[a-zA-Z0-9]{8,20}"
							title="minimum 8 character required and no special character" required></td>
					</tr>
					<tr>
						<!--^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%?&])[A-Za-z\d@$!%*?&]{8,15}-->
						<td><label for="password">Password <span>*</span></label></td>
						<td><input type="password" name="password" id="passwordinput"
							placeholder="password" pattern="[a-zA-Z0-9!@#$%^&*()_+]{8,20}"
							required></td>
					</tr>
					<tr>
						<td><label for="repassword">Re-Password <span>*</span></label></td>
						<td><input type="password" onchange="validatePassword()"
							name="Re-password" id="repasswordinput"
							placeholder="Re enter password" pattern="[a-zA-Z0-9!@#$%^&*()_+]{8,20}" required>
						<p id="repasswordcomment"></p></td>

					</tr>
					<tr>
						<td><label for="mobile">Phone <span>*</span></label></td>
						<td><input type="number" name="mobile" id="mobileinput"
							pattern="[6-9]{1}[0-9]{9}" placeholder="MobileNumber" title="Start with 6789 and 10 character" required></td>
					</tr>
					<tr>
						<td><button type="submit" id="register">Register</button></td>
						<td></td>
					</tr>
				</thead>
			</table>
		</form>
	</div>
		<script type="text/javascript" src="js/register.js"></script>
</body>
</html>