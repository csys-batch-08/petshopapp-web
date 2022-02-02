todaydate();
		function todaydate() {
			var today = new Date();
			var dd = String(today.getDate()).padStart(2, '0');
			var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
			var yyyy = today.getFullYear();
			var yyyy1 = today.getFullYear() - 10;

			let maxdate = yyyy + '-' + mm + '-' + dd;
			let mindate = yyyy1 + '-' + mm + '-' + dd;
			document.getElementById("dob").setAttribute("max", maxdate);
			document.getElementById("dob").setAttribute("min", mindate);
		}