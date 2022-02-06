/**
 * 
 */
$("login").onsubmit(function() {
	var username = $('#usernameinput').val();
	var password = $('#passwordinput').val();
	$.ajax({
		url: "login?usernameinput="+username+"&passwordinput="+password, success: function(result) {
			if(result.includes('Invalid username or password'))
			var x = document.getElementById("snackbar");
			x.className = "show";
			setTimeout(function() { x.className = x.className.replace("show", ""); }, 3000);
		}
	});
});