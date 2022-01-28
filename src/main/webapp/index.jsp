<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" href="css/index.css"></link>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js" crossorigin="anonymous"></script>
</head>
<body>
	<div id="background">
	
		<!-- Web site Name and Logo-->
		
		<h1><i class="fas fa-paw" style="color: white;"></i> Pet Shop</h1>
		
		<!--Login form-->
				
		<form action="login" class="form" method="post">
		
		<p id="head">Login</p>
			
  <!--UserName And Password-->
			<label for="username" id="username"><i class="fas fa-id-card" style="color: black;"></i> Username:</label> <br> <input
				type="text" name="usernameinput" id="usernameinput"
				placeholder="Enter the username" pattern="[a-zA-Z0-9]+{8,20}"
				title="username must be in 8 character" required> <br>
			
			<label for="password"><i class="fas fa-lock" style="color: black;"></i> Password:</label> <br> <input
				type="password" name="passwordinput" id="passwordinput"
				placeholder="Enter the password"
				pattern="[a-zA-Z0-9!@#$%^&*()]+{8,20}"
				title="password must be in 8 character" required> <br>
			
   <!--Login Button-->
			<button type="submit" id="login">Login</button>
			<p>
				Don't have account ?<a href="register.jsp"> Register here</a>
			</p>
		</form>
	</div>
		 
</body>
</html>
