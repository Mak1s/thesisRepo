const selectElementClass = document.getElementById('classOptions');
// Add an event listener for the change event
selectElementClass.addEventListener('change', function(event) {
    console.log(`Selected value for class only: ${event.target.value}`);
});

const selectElementClassProperty = document.getElementById('propertiesOptions');
// Add an event listener for the change event
selectElementClassProperty.addEventListener('change', function(event) {
    console.log(`Selected value for class and property: ${event.target.value}`);
});

const selectElementClassProperty2 = document.getElementById('classPropertyOptions');
// Add an event listener for the change event
selectElementClassProperty2.addEventListener('change', function(event) {
    console.log(`Selected value for class and property: ${event.target.value}`);
});

function togleVisibility(option) {
    var div = document.getElementById('class');
    var div2= document.getElementById('classProperty');
    var div3= document.getElementById('buttons');
    if (option==1){
        div.style.display = 'flex'; 
        div2.style.display = 'none';
    } else if (option==2){
                div.style.display = 'none'; 
                div2.style.display = 'flex'; 
 
    }else if(option==3){
        div3.style.display = 'flex'; 
    }else if(option==4){
        div.style.display = 'none';
        div2.style.display = 'none';
        div3.style.display = 'none';
    }

}
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

function getX3MLFile(){
    let myForm = document.getElementById('uploadForm');
    let formData = new FormData(myForm);
    const fileInput = document.getElementById('fileUpload');
    const file = fileInput.files[0];
    var jsonData=JSON.stringify(file);
    if(!file){
        document.getElementById('addedContent').innerHTML="Error";
        return;
    }
    var xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const responseData = JSON.parse(jsonData);
            $('#addedContent').html("File uploaded successfully!<br> File Data");
            $('#addedContent').append(createTableFromJSON(responseData));
            console.log(responseData);
        } else if (xhr.status !== 200) {
            document.getElementById("addedContent").innerHTML = "Request failed. Returned status of " + xhr.status + "<br>";
        }
    };

    // Create a new FormData object to send the file 
    /*let combinedFormData = new FormData();
    if (file) {
        combinedFormData.append('fileUpload', file); // Add the file
    }
*/    console.log(jsonData);

    xhr.open("GET", "UploadFile");
    xhr.send(jsonData);
}