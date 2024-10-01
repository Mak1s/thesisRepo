document.addEventListener('DOMContentLoaded', function() {
    const signupForm = document.getElementById('signup');

    signupForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the default form submission
        // You can add form validation here if needed

        // Redirect to index.html after form submission
        window.location.href = 'index.html';
    });
});

function register(){
    window.location.href="register.html";
}

function navigateTo(URL){
    document.getElementById('myForm').action = URL;
    document.getElementById('myForm').submit();
    console.log(document.getElementById('myForm'));
}

function createTableFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (var x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "</table>";
   return html;

}


function getUser() {
    let myForm = document.getElementById('signup');
    let formData = new FormData(myForm);
    const data ={};
    
    formData.forEach((value, key) => (data[key] = value));
    var jsonData=JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    console.log(xhr);
 xhr.onload = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        console.log("1");
        console.log(xhr.responseText); // Log the server response
        console.log("2");
        console.log(xhr);
         try {
            const responseData = JSON.parse(xhr.responseText);
            $('#ajaxContent').html("Successful Registration. Now please log in!<br> Your Data");
            $('#ajaxContent').append(createTableFromJSON(responseData));
            console.log(responseData);
        } catch (e) {
            console.error("Failed to parse JSON response:", e);
            console.error("Response text:", xhr.responseText);
            document.getElementById("ajaxContent").innerHTML = "Failed to parse JSON response: " + e.message;
        }
    } else if (xhr.status !== 200) {
        document.getElementById("ajaxContent").innerHTML = "Request failed. Returned status of " + xhr.status + "<br>";
    }
    };
        console.log("json data->");
        console.log(jsonData);
        xhr.open("POST", "GetUser");
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.send(jsonData);
        
    }
    
    function login_post(){
    
    var data = $("#myForm").serialize();
  //  $("#myForm")[0].reset();
    console.log(data);
    var xhttp = new XMLHttpRequest();
    console.log(xhttp);
    xhttp.onload = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            document.getElementById("msg").style.color="rgb(0, 0, 153)";            
            console.log("OK 2");
            document.getElementById("msg").innerText="Connected";
             document.getElementById("msg").innerText = xhttp.responseText;
            window.location.href='./index.html';
        }else if(xhttp.status === 401){
            console.log("OK 3");
           document.getElementById("msg").style.color="red";
            document.getElementById("msg").innerText="Wrong Credentials";
        } 
    };
        
   

    xhttp.open("POST","servlet_login");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(data);
    
}