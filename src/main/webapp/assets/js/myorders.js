function cancelOrder(orderId) {
    console.log(orderId);
    var confirmAction = confirm("Are you sure you want cancel this item");
    if (confirmAction) {
        var url = "CancelOrder?orderId=" + orderId;
        console.log(url);
        if (window.XMLHttpRequest) {
           request = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }
        try {
            request.onreadystatechange = getInfo;
            request.open("GET", url, true);
            request.send();
        } catch (e) {
            alert("Unable to connect to server");
        }
    } else {
        alert("Action canceled");
    }
}

function getInfo() {
    if (request.readyState == 4) {
        var val = request.responseText;
        alert(val);
        location.reload();
    }
}