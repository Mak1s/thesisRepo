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
