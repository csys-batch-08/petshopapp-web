// Update Pet Status
function UpdateStatus(petId, status) {
	$.ajax({
		url: "UpdatePetStatus?petId=" + petId + "&status=" + status, success: function(result) {
			alert(result);
			location.reload();
		}
	});
}
