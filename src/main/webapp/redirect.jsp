<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<span id="message" style="display: none;">${message}</span>
	<script type="text/javascript">
		var message = document.getElementById("message").innerHTML;
		alert(message);
		
		switch (message	) {
		case "Please Add address brefore add pet":
			document.location.href = 'MyProfile';
			break;
		case "Invalid added item quantity":
			document.location.href = 'AddItem';
			break;
		case "Pet Details updated":
			document.location.href = 'MyPets';
			break;
		case "Invalid pet quantity":
			document.location.href = 'EditPet';
			break;
		case "Address updated":
			document.location.href = 'MyProfile';
			break;
		case "Address or city can't be none":
			document.location.href = 'MyProfile';
			break;
		case "invalid username or password":
			document.location.href = 'index.jsp';
			break;
		case "registration completed successfully login now":
			document.location.href = 'index.jsp';
			break;
		case "Something went to wrong please try again":
			document.location.href = 'register.jsp';
			break;
		case "Profile updated successfully":
			document.location.href = 'MyProfile';
			break;
					}
	</script>
</body>
</html>