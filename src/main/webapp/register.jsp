<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="Petshop,pets,animals">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register User</title>
<link rel="stylesheet" href="./assets/css/register.css"></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
</head>
<body>
      <p id="snackbar">Username not available</p>
	<div id="background">
		<h1>
			<i class="fas fa-paw" style="color: white;"></i> Pet Shop  
		</h1>      
		<form action="register" name="registerform" class="registerform"
			method="post">
			<div id="image">
				<img src="./assets/images/background/background9.jpeg"
					alt="background image">
			</div>
			<table>
				<caption></caption>
				<thead>
					<tr>
						<th id="registername" colspan="2">Register here</th>
						<th></th>
					</tr>
					<tr>
						<td><label for="firstname">First Name <span>*</span></label></td>
						<td><input type="text" name="firstname" id="firstnameinput"
							pattern="[a-zA-Z]{3,20}"
							title="minimum 3 characters and accept only alphabets"
							placeholder="Firstname" required></td>
					</tr>
					<tr>
						<td><label for="lastname">Last Name <span>*</span></label></td>
						<td><input type="text" name="lastname" id="lastnameinput"
							pattern="[a-zA-Z]{3,20}"
							title="Minimum 3 characters accept only alphabets"
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
							placeholder="Email" required title="Examble abcd@gmail.com"></td>
					</tr>
					<tr>
						<td><label for="username">User Name <span>*</span></label></td>
						<td><input type="text" name="username"
							onchange="validateUsername()" id="usernameinput"
							placeholder="username" pattern="[a-zA-Z0-9]{8,20}"
							title="minimum 8 character required and no special character"
							required></td>
					</tr>
					<tr>
						<td><label for="password">Password <span>*</span></label></td>
						<td><input type="password" name="password" id="passwordinput"
							placeholder="password" pattern="[a-zA-Z0-9!@#$%^&*()_+]{8,20}"
							required></td>
					</tr>
					<tr>
						<td><label for="repassword">Re-Password <span>*</span></label></td>
						<td><input type="password" onchange="validatePassword()"
							name="Re-password" id="repasswordinput"
							placeholder="Re enter password"
							pattern="[a-zA-Z0-9!@#$%^&*()_+]{8,20}" required>
							<p id="repasswordcomment"></p></td>

					</tr>
					<tr>
						<td><label for="mobile">Phone <span>*</span></label></td>
						<td><input type="number" name="mobile" id="mobileinput"
							pattern="[6-9]{1}[0-9]{9}" placeholder="MobileNumber"
							title="Start with 6789 and 10 character" required></td>
					</tr>
					<tr>
						<td><button type="submit" id="register">Register</button></td>
						<td></td>
					</tr>
				</thead>
			</table>
		</form>
	</div>
	<script type="text/javascript" src="./assets/js/register.js"></script>
</body>
</html>