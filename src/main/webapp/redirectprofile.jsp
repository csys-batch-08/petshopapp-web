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
		switch (fruits) {
		case "Please Add address brefore add pet":
			document.location.href = 'MyProfile';
			break;
		case "Pet Details updated":
			document.location.href = 'MyPets';
			break;
		case "Invalid pet quantity":
			document.location.href = 'EditPet';
			break;
		
			
		}
	</script>
</body>
</html>