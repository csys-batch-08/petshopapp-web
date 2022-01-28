
//Update Wallet to ajax
function UpdateWallet(){
    var wallet=document.getElementById("updatewallet1").value;
    var url="UpdateWallet?wallet="+wallet;  
    if(window.XMLHttpRequest){  
        request=new XMLHttpRequest();  
        }  
        else if(window.ActiveXObject){  
        request=new ActiveXObject("Microsoft.XMLHTTP");  
        }  
    try  
    {  
    request.onreadystatechange=getInfo;  
    request.open("GET",url,true);  
    request.send();  
    }  
    catch(e)  
    {  
    alert("Unable to connect to server");  
    }    
} 
   
// Update image to ajax
function UpdateImage(){
    var image=document.getElementById("image").value;
    console.log(image);
    const name = image.substring(12, image.length);
    var url="updateProfileImage?image="+name;  
    if(window.XMLHttpRequest){  
        request=new XMLHttpRequest();  
        }  
        else if(window.ActiveXObject){  
        request=new ActiveXObject("Microsoft.XMLHTTP");  
        }  
    try  
    {  
    request.onreadystatechange=getInfo;  
    request.open("GET",url,true);  
    request.send();  
    }  
    catch(e)  
    {  
    alert("Unable to connect to server");  
    }    
}

// Show Password
function getInfo(){  
    if(request.readyState==4){  
    var val=request.responseText;
       alert(val.trim()); 
      location.reload();
    }  
    }  

function showPassword() {
    var show = document.getElementById("password");
    if (show.type === "password") {
      show.type = "text";
    } else {
      show.type = "password";
    }
  }


  