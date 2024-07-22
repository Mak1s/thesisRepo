var modal = document.getElementById("myModal");
var modal1=document.getElementById("ontoModal");
var modal2=document.getElementById("viewModal");
var btn = document.getElementById("editclass");
var btn1= document.getElementById("editclassontology");
var btn2 = document.getElementById("viewwork");

        var span = document.getElementsByClassName("close")[0];
        var span1 = document.getElementsByClassName("close1")[0];
        var span2 =document.getElementsByClassName("close2")[0];

       
        btn.onclick = function() {
            modal.style.display = "block";
        }
        
        btn1.onclick = function() {
            modal1.style.display = "block";
        }
        btn2.onclick= function() {
            modal2.style.display = "block";
        }
        
        span.onclick = function() {
            modal.style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        
        span1.onclick = function() {
            modal1.style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target == modal1) {
                modal1.style.display = "none";
            }
        }

        span2.onclick = function() {
            modal2.style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target == modal2) {
                modal2.style.display = "none";
            }
        }


      
 function updateUrlPath(newPath) {
    // Define the base path of your application
    const basePath = '/thesisProject/';

    // Construct the new URL based on the base path and the provided newPath
    const newUrl = basePath + newPath;

    // Update the URL using history.pushState()
    history.pushState({}, '', newUrl);
}
function updateUrlOnClick() {
            // Define the base path of your application
            const basePath = '/thesisProject/';

            // Append a query parameter to indicate the click action
            const newPath = basePath + '?newproject';

            // Navigate to the new URL using window.location.href
            window.location.href = newPath;
            
        }

if(document.getElementById('newproject')){
    document.getElementById('newproject').addEventListener('click', function() {
        updateUrlPath('?newproject');
        
    });
}

if(document.getElementById('editclass')){
    document.getElementById('editclass').addEventListener('click', function() {
        
      //   updateUrlOnClick(); 
      //   console.log("g");
    });
} 

const selectElementClass = document.getElementById('classOptions');
// Add an event listener for the change event
if(selectElementClass){
selectElementClass.addEventListener('change', function(event) {
    console.log(`Selected value for class only: ${event.target.value}`);
});
}

const selectElementClassProperty = document.getElementById('propertiesOptions');
// Add an event listener for the change event
if(selectElementClassProperty){
selectElementClassProperty.addEventListener('change', function(event) {
    console.log(`Selected value for class and property: ${event.target.value}`);
});
}

const selectElementClassProperty2 = document.getElementById('classPropertyOptions');
// Add an event listener for the change event
if(selectElementClassProperty2){
selectElementClassProperty2.addEventListener('change', function(event) {
    console.log(`Selected value for class and property: ${event.target.value}`);
});
}        
// Function to show option 1
function showOption1() {
    var div = document.getElementById('class');
    var div2 = document.getElementById('classProperty');
    
    div.style.display = 'flex';
    div2.style.display = 'none';
    document.getElementById("next1").style.display = "none";
    document.getElementById("before1").style.display = "flex";
}

// Function to show option 2
function showOption2() {
    var div = document.getElementById('class');
    var div2 = document.getElementById('classProperty');
    
    div.style.display = 'none';
    div2.style.display = 'flex';
    document.getElementById("next2").style.display = "none";
    document.getElementById("before2").style.display = "flex";
}

// Function to show option 3
function showOption3() {
    var div3 = document.getElementById('buttons');
    var div4 = document.getElementById('import');
    var div5 = document.getElementById('load');
    
    div3.style.display = 'flex';
    div4.style.display = 'none';
    div5.style.display = 'none';
}

// Function to show option 4
function showOption4() {


    var div = document.getElementById('class');
    var div2 = document.getElementById('classProperty');
    var div3 = document.getElementById('buttons');
    var div4 = document.getElementById('import');
    var div5 = document.getElementById('load');
    
    div.style.display = 'none';
    div2.style.display = 'none';
    div3.style.display = 'none';
    div4.style.display = 'none';
    div5.style.display = 'flex';
}

// Function to show option 5
function showOption5() {
    var div = document.getElementById('class');
    var div2 = document.getElementById('classProperty');
    var div3 = document.getElementById('buttons');
    var div4 = document.getElementById('import');
    var div5 = document.getElementById('load');
    
    div.style.display = 'none';
    div2.style.display = 'none';
    div3.style.display = 'none';
    div4.style.display = 'flex';
    div5.style.display = 'none';
}

// Original function removed, as each option is handled by its respective function

function getValueClass(){
    const inputElement = document.getElementById('classTO');
    const value = inputElement.value;
    console.log('Input field value ClassOnly:', value);
}
function getValueClassProperty(){
    const inputElement = document.getElementById('classPropertyTO');
    const value = inputElement.value;
    console.log('Input field value Class Of ClassProperty:', value);
}
function getValueProperty(){
    const inputElement = document.getElementById('propertyTO');
    const value = inputElement.value;
    console.log('Input field value PropertyOnly:', value);
}

function logout() {
    window.location.href = 'login.html'; 
}

function profile(){
    window.location.href = 'profile.html'; 
}

function index(){
    window.location.href="index.html";
}

function nextstep1(){
    document.getElementById("next1").style.display="flex";
    document.getElementById("before1").style.display="none";
   
}

function nextstep2(){
    document.getElementById("next2").style.display="flex";
    document.getElementById("before2").style.display="none";
}

function edit(){
    document.getElementById("view").style.display="none";
    document.getElementById("edit").style.display="flex";
}
function back(){
    document.getElementById("view").style.display="block";
    document.getElementById("edit").style.display="none";
}
function formChooser(option){
    if(option ==1){
        getX3MLFile('uploadForm','fileUpload','addedContent','uploadServlet');
    }else if(option ==2){
        getX3MLFile('uploadForm1','fileUpload1','addedContent1','uploadServletRDF');
    }else if(option ==3){
        getX3MLFile('uploadForm2','fileUpload2','addedContent2','uploadServlet1');        
    }else if(option == 4){
        getX3MLFile('uploadForm3','fileUpload3','addedContent3','uploadServletRDF1');
    }
}

function getX3MLFile(formId,fileId,contentId,servletName) {
    let myForm = document.getElementById(formId);
    let formData = new FormData(myForm);

    const fileInput = document.getElementById(fileId);
    const file = fileInput.files[0];

    if (!file) {
        document.getElementById(contentId).innerHTML = "Error: No file selected.";
        return;
    }

    var xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById(contentId).innerHTML = "File uploaded successfully!";
            console.log(xhr.responseText);
            
        } else {
            document.getElementById(contentId).innerHTML = "Request failed. Returned status of " + xhr.status;
        }
    };

    xhr.open("POST", servletName, true);
    xhr.send(formData);
}
