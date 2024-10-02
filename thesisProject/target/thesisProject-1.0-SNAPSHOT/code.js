var x3mlfile= "";
var newChanges="";
var newChanges1="";
var newChanges2="";
var iamfirst=0;
var iamsecond=0;
let changesArr= [];
var namespace="";
var ontoName="";
var globalPref="";
var globalURI="";
var cls="";
var prt="";
const namespaceMap = new Map();
var totalChanges4Class=0;
var totalChanges4Class_Properties=0;
var totalChanges4Properties=0;
var modal = document.getElementById("myModal");
var modal1=document.getElementById("ontoModal");
var modal2=document.getElementById("viewModal");
var btn = document.getElementById("editclass");
var btn1= document.getElementById("editclassontology");
var btn2 = document.getElementById("viewwork");
var btn3 =document.getElementById("editclass1");
var btn4= document.getElementById("editclassontology1");
var btn5 =document.getElementById("viewwork1");
var cl= " ";
var pr= " ";
var all= " ";
var one,two,three,four;
        var span = document.getElementsByClassName("close")[0];
        var span1 = document.getElementsByClassName("close1")[0];
        var span2 =document.getElementsByClassName("close2")[0];

       
        btn.onclick = function() {
            one=true;
            two =false;
            modal.style.display = "block";
        }
        
        btn1.onclick = function() {
            three=true;
            four=false;
            modal1.style.display = "block";
        }
        btn2.onclick= function() {
            modal2.style.display = "block";
        }
        
        btn3.onclick = function() {
            one=false;
            two=true;
            modal.style.display = "block";
        }
        
        btn4.onclick = function() {
            three=false;
            four=true;
            modal1.style.display = "block";
        }
        btn5.onclick = function() {
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
    if(two){
        document.getElementById("changedContent1").innerHTML+=`<p id="classfrom1">&nbsp;&nbsp;Class from: ${event.target.value}</p>`;        
    }else if(one){
        document.getElementById("changedContent").innerHTML+=`<p id="classfrom">&nbsp;&nbsp;Class from: ${event.target.value}</p>`;
    }
});
}

const selectElementClassProperty = document.getElementById('propertiesOptions');
// Add an event listener for the change event
if(selectElementClassProperty){
selectElementClassProperty.addEventListener('change', function(event) {
    console.log(`Selected value for class and property: ${event.target.value}`);
    if(three){
        document.getElementById("changedContent").innerHTML+=`<p id="propertyfrom">&nbsp;&nbsp;Property from: ${event.target.value}</p>`;
    }else if(four){
        document.getElementById("changedContent1").innerHTML+=`<p id="propertyfrom1">&nbsp;&nbsp;Property from: ${event.target.value}</p>`;
    }

});
}

const selectElementClassProperty2 = document.getElementById('classPropertyOptions');
// Add an event listener for the change event
if(selectElementClassProperty2){
selectElementClassProperty2.addEventListener('change', function(event) {
    console.log(`Selected value for class and property: ${event.target.value}`);
    if(three){
        document.getElementById("changedContent").innerHTML+=`<p id="classpropertyfrom">&nbsp;&nbsp;Class from: ${event.target.value}</p>`;
    }else if(four){
        document.getElementById("changedContent1").innerHTML+=`<p id="classpropertyfrom1">&nbsp;&nbsp;Class from: ${event.target.value}</p>`;
    }
});
}        

function showOption1() {
    var div = document.getElementById('class');
    var div4 = document.getElementById('import');
    var div5 = document.getElementById('load');
    div.style.display = 'flex';
    div4.style.display = 'none';
    div5.style.display = 'none';
    document.getElementById("next1").style.display = "none";
    document.getElementById("before1").style.display = "flex";
}


function showOption3() {
    var div3 = document.getElementById('buttons');
    var div4 = document.getElementById('import');
    var div5 = document.getElementById('load');
    getAll(cl,pr,cls,prt,globalPref,globalURI);
    div3.style.display = 'flex';
    div4.style.display = 'none';
    div5.style.display = 'none';
}

function showOption4() {
    var div = document.getElementById('class');
    var div3 = document.getElementById('buttons');
    var div4 = document.getElementById('import');
    var div5 = document.getElementById('load');
    
    div.style.display = 'none';
    div3.style.display = 'none';
    div4.style.display = 'none';
    div5.style.display = 'flex';
}

function showOption5() {
    var div = document.getElementById('class');
    var div3 = document.getElementById('buttons');
    var div4 = document.getElementById('import');
    var div5 = document.getElementById('load');
    
    div.style.display = 'none';
    div3.style.display = 'none';
    div4.style.display = 'flex';
    div5.style.display = 'none';
}

var newChangedElement="";
var newChangedElement2="";

function addChange(){
    totalChanges4Class++;
    totalChanges4Properties=0;
    totalChanges4Class_Properties=0;
    var inputElement = "";
    var changesEl="";
    var selectElement="";
    if(totalChanges4Class<=1){
        changesEl=document.getElementById('changes');
        selectElement = document.getElementById('classOptions');
        inputElement = document.getElementById('classTO');
        console.log(totalChanges4Class);
    }else{
        console.log(totalChanges4Class);
        changesEl= document.getElementById(newChanges);
        selectElement = document.getElementById(newChangedElement);
        inputElement = document.getElementById(newChangedElement2);
    }
    const selectValue = selectElement.value;
    const value = inputElement.value;
    const changes={
        "classBefore":selectValue,
        "classAfter":value
    };
    changesArr.push(changes);
    
    changesEl.innerHTML+="<div class=\"border border-dark\"><p id=\"classBefore\">&nbsp;&nbsp;&nbsp;&nbsp;Class from: "+selectValue+"</p>"+
            "<p id=\"classAfter\">&nbsp;&nbsp;&nbsp;&nbsp;Class to: "+value+"</p>"
            +"<button id=\"classtobtn\"class=\"btn-light\" onclick=\"removeChanges(this)\"> Remove changes</button><br></div>";

    console.log(changesArr);
    classOnlyPost(changes);
}

var newChangedElement3="";
var newChangedElement4="";
var newChangedElement5="";
var newChangedElement6="";
var newChangedElement7="";
var newChangedElement8="";
var newChangedElement9="";

function addChange2(){

    totalChanges4Class_Properties++;
    totalChanges4Properties=0;
    totalChanges4Class=0;
    var inputElement = "";
    var inputElement1 = "";
    var selectElement ="";
    var selectElement1="";
    var selectElement2="";
    if(totalChanges4Class_Properties<=1){
         inputElement = document.getElementById('propertyTO');
         inputElement1 = document.getElementById('classPropertyTO');
         selectElement = document.getElementById('classPropertyOptions');
         selectElement1 = document.getElementById('propertiesOptions');
         selectElement2= document.getElementById('addClassPropertyTO');
         changesEl= document.getElementById('changes2');
    
    }else{
        inputElement = document.getElementById(newChangedElement6);
         inputElement1 = document.getElementById(newChangedElement4);
         selectElement = document.getElementById(newChangedElement3);
         selectElement1 = document.getElementById(newChangedElement5);
         selectElement2 = document.getElementById(newChangedElement9);
         changesEl= document.getElementById(newChanges2);
    }
    const selectValue = selectElement.value;
    const selectValue1 = selectElement1.value;
    const value = inputElement.value;
    const value1 = inputElement1.value;
    const selectValue2= selectElement2.value;
    const changes={
        "classBefore":selectValue,
        "classAfter":value1,
        "propertyBefore":selectValue1,
        "propertyAfter":value,
        "additionalClass":selectValue2
    };
    changesArr.push(changes);
    
    changesEl.innerHTML+="<div class=\"border border-dark\"><p id=\"classBefore\">&nbsp;&nbsp;&nbsp;&nbsp;Class from: "+selectValue+"</p>"+
            "<p id=\"classAfter\">&nbsp;&nbsp;&nbsp;&nbsp;Class to: "+value1+"</p>"+
            "<p id=\"propertyBefore\">&nbsp;&nbsp;&nbsp;&nbsp;Class from: "+selectValue1+"</p>"+
            "<p id=\"propertyAfter\">&nbsp;&nbsp;&nbsp;&nbsp;Class to: "+value+"</p>"+
            "<p id=\"additionalClass\">&nbsp;&nbsp;&nbsp;&nbsp;Additional Class : "+selectValue2+"</p>"
            +"<button id=\"classpropertiestobtn\"class=\"btn-light\" onclick=\"removeChanges(this)\"> Remove changes</button><br></div>";
    classPropertyPost(changes);
}

function addChange3(){
        totalChanges4Properties++;
        totalChanges4Class=0;
        totalChanges4Class_Properties=0;
    var inputElement = "";
    var changesEl="";
    var selectElement="";
    if(totalChanges4Properties<=1){
        changesEl=document.getElementById('changes1');
        selectElement = document.getElementById('PpropertiesOptions');
        inputElement = document.getElementById('PpropertyTO');
        console.log(totalChanges4Properties);
    }else{
        console.log(totalChanges4Properties);
        console.log(changesEl+"\n"+document.getElementById(newChanges1));
        selectElement = document.getElementById(newChangedElement7);
        inputElement = document.getElementById(newChangedElement8);
        changesEl= document.getElementById(newChanges1);

    }
    const selectValue = selectElement.value;
    const value = inputElement.value;
    const changes={
        "propertyBefore":selectValue,
        "propertyAfter":value
    };
    changesArr.push(changes);
    console.log("newChanges1:", newChanges1);
    console.log("newChangedElement7:", newChangedElement7);
    console.log("newChangedElement8:", newChangedElement8);
    console.log("changesEl:", changesEl);
    changesEl.innerHTML+="<div class=\"border border-dark\"><p id=\"propertyBefore\">&nbsp;&nbsp;&nbsp;&nbsp;Property from: "+selectValue+"</p>"+
            "<p id=\"propertyAfter\">&nbsp;&nbsp;&nbsp;&nbsp;Property to: "+value+"</p>"
            +"<button id=\"propertytobtn\"class=\"btn-light\" onclick=\"removeChanges(this)\"> Remove changes</button><br></div>";

    classPropertyPost(changes);

}

function getValueClass(){
    const jsonString= JSON.stringify(changesArr);
    const blob = new Blob([jsonString], { type: 'application/json' });

    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'changes.json';
    document.body.appendChild(a);
    
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
    const downloadedFileContent = jsonString;

    
    if(one){
        var inputElement ="";
        var selectElement="";
   if(totalChanges4Class<=1){
        selectElement = document.getElementById('classOptions');
        inputElement = document.getElementById('classTO');
        console.log(totalChanges4Class);
    }else{
        console.log(totalChanges4Class);
        selectElement = document.getElementById(newChangedElement);
        inputElement - document.getElementById(newChangedElement2);
    }
    const selectValue = selectElement.value;
    const value = inputElement.value;
    const changes={
        "classBefore":selectValue,
        "classAfter":value
    };
    const jsonString = JSON.stringify(changes);
    console.log(jsonString);
        document.getElementById("changedContent").innerHTML+="<p id=\"classto\">&nbsp;&nbsp;&nbsp;&nbsp;Class to: "+value+"</p>"+"<button id=\"classtobtn\"class=\"btn-light\" onclick=\"removeChanges()\"> Remove changes</button><br>";
    }else if(two){
        var inputElement = "";
       var selectElement="";
   if(totalChanges4Class<=1){
        selectElement = document.getElementById('classOptions');
        inputElement =document.getElementById('classTO');
        console.log(totalChanges4Class);
    }else{
        console.log(totalChanges4Class);
        selectElement = document.getElementById(newChangedElement);
        inputElement = document.getElementById(newChangedElement2);
    }
    const selectValue = selectElement.value;
    const value = inputElement.value;
    const changes={
        "classBefore":selectValue,
        "classAfter":value
    };
    const jsonString = JSON.stringify(changes);
    console.log(jsonString);
        document.getElementById("changedContent1").innerHTML+="<p id=\"classto1\">&nbsp;&nbsp;&nbsp;&nbsp;Class to: "+value+"</p>"+"<button id=\"classtobtn1\"class=\"btn-light\" onclick=\"removeChanges()\"> Remove changes</button><br>";
    }
    translationPost(x3mlfile,downloadedFileContent);
}


function getValueClassProperty(){
    const inputElement = document.getElementById('classPropertyTO');
    const value = inputElement.value;
    console.log('Input field value Class Of ClassProperty:', value);
    if(three){
        document.getElementById("changedContent").innerHTML+="<p id=\"classpropertyto\">&nbsp;&nbsp;&nbsp;&nbsp;Class to: "+value+"</p><br>";
    }else if(four){
        document.getElementById("changedContent1").innerHTML+="<p id=\"classpropertyto1\">&nbsp;&nbsp;&nbsp;&nbsp;Class to: "+value+"</p><br>";
    }
}
function getValueProperty(){

     const blob = new Blob([changesArr], { type: 'application/json' });

    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'changes.json';
    document.body.appendChild(a);

    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
    console.log('Input field value PropertyOnly:', value);
    if(three){
        document.getElementById("changedContent").innerHTML+="<p id=\"propertyto\">&nbsp;&nbsp;&nbsp;&nbsp;Property to: "+value+"</p>"+"<button id=\"propertytobtn\" class=\"btn-light\" onclick=\"removeChanges()\"> Remove changes</button><br>";
    }else if(four){
        document.getElementById("changedContent1").innerHTML+="<p id=\"propertyto1\">&nbsp;&nbsp;&nbsp;&nbsp;Property to: "+value+"</p>"+"<button id=\"propertytobtn1\" class=\"btn-light\" onclick=\"removeChanges()\"> Remove changes</button><br>";
    }
   
}

function removeChanges(button){
    const parentDiv = button.parentElement;
    console.log(parentDiv);
    var cb = parentDiv.querySelector('p[id^="classBefore"]');
    var ca = parentDiv.querySelector('p[id^="classAfter"]');
    var pb = parentDiv.querySelector('p[id^="propertyBefore"]');
    var pa = parentDiv.querySelector('p[id^="propertyAfter"]');
    var ac = parentDiv.querySelector('p[id^="additionalClass"]');
    if(cb){
        if(pb){
            cb = cb.textContent.trim().replace("Class from: ", "");
            ca = ca.textContent.trim().replace("Class to: ", "");
            pb = pb.textContent.trim().replace("Property from: ", "");
            pa = pa.textContent.trim().replace("Property to : ", "");
            ac = ac.textContent.trim().replace("Additional Class: ", "");
            const changes={
                "classBefore":cb,
                "classAfter":ca,
                "propertyBefore":pb,
                "propertyAfter":pa,
                "additionalClass":ac
            };
            console.log(changes);
            removePost(changes);
            
        }else{
            cb = cb.textContent.trim().replace("Class from: ", "");
            ca = ca.textContent.trim().replace("Class to: ", "");
            const changes={
                "classBefore":cb,
                "classAfter":ca
            };
            console.log(changes);
            removePost(changes);

        }
    }else{
            pb = pb.textContent.trim().replace("Property from: ", "");
            pa = pa.textContent.trim().replace("Property to : ", "");
        const changes={
                "propertyBefore":pb,
                "propertyAfter":pa
            };
        console.log(changes);
        removePost(changes);

    }
    parentDiv.remove();
   
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
    document.getElementById("button3").style.display="none";
    document.getElementById("button1").style.display="flex";
    document.getElementById("button2").style.display="none";

    if(document.getElementById("next1").style.display==="none"){
        changesArr.length=0;
        document.getElementById("next1").style.display="flex";
        document.getElementById("before1").style.display="none";
        document.getElementById("next2").style.display="none";
        document.getElementById("next3").style.display="none";
        document.getElementById("new1").style.display="none";
        document.getElementById("new2").style.display="none";
         document.getElementById("new").innerHTML="";
        
     
    } else {
        document.getElementById("before1").style.display="none";
        // Generate a unique id for each dynamically created select element
        const uniqueId = 'classOptions' + Math.random().toString(36).substr(2, 9);
        const uniqueId2 = 'classTO' + Math.random().toString(36).substr(2, 9);
        const uniqueChanges ='changes' + Math.random().toString(36).substr(2, 9);
        newChangedElement=uniqueId;
        newChangedElement2=uniqueId2;
        newChanges= uniqueChanges;
        const divContent = `<div id="next1" class="border border-dark" style="display:flex;">
                <div class="row">
                    <div class="col-sm-6">
                        <p>Choose class to change from: &nbsp; &nbsp; &nbsp;*</p>
                        <select id="${uniqueId}" name="classOptions">
                        </select>
                    <div id="${uniqueChanges}">                    
                    </div>

                    </div>
                    <div class="col-sm-6">
                        <p>Choose class to change to: &nbsp; &nbsp; &nbsp;*</p>
                        <input class="form-control" id="${uniqueId2}" type="text" list="lst-autocomplete" placeholder="E99_Identfies_As">
                        <button type="submit" class="btn btn-dark" onclick="addChange()">Set Changes</button>
                      
                        <div class="row" id="changedContent">

                        </div>
                        <datalist id="lst-autocomplete">
                        </datalist>
                    </div>

                </div>
            </div>`;  
        document.getElementById("new").style= "flex";

        document.getElementById("new").innerHTML += divContent;
        
        // Populate the new select element
        const newSelectElement = document.getElementById(uniqueId);
        const newSelectElement2 =document.getElementById(uniqueId2);
        populateClassOptions(newSelectElement); 
        populateAllClass(newSelectElement2);
    }
}

function populateClassOptions(selectElement) {
    console.log("populate class options with: " + cl);
    if(!iamfirst){
        cl=cl.split('\n');
        iamfirst++;
    }
    for (let i = 0; i < cl.length; i++) {
        selectElement.innerHTML += "<option value='" + cl[i] + "'>" + cl[i] + "</option>";
    }
}

function populateAllClass(selectElement){
    for( var i=0;i<all.length;i++){
           if(all[i][0]==="E"){
                selectElement.innerHTML+="<option value="+globalPref+":"+all[i]+">"+"</option>";
            }
        }
}

function nextstep2(){
    document.getElementById("button3").style.display="none";
    document.getElementById("button1").style.display="none";
    document.getElementById("button2").style.display="flex";

    if(document.getElementById("next2").style.display==="none"){
        changesArr.length=0;
        document.getElementById("next2").style.display="flex";
        document.getElementById("before1").style.display="none";
        document.getElementById("next1").style.display="none";
        document.getElementById("next3").style.display="none";
        document.getElementById("new").style.display="none";
        document.getElementById("new2").style.display="none";
        document.getElementById("new1").innerHTML="";
        
   
    }else{
        document.getElementById("before1").style.display="none";
        const uniqueId = 'classPropertyOptions' + Math.random().toString(36).substr(2,9);
        const uniqueId2 = 'classPropertyTO' + Math.random().toString(36).substr(2,9);
        const uniqueId3 = 'propertiesOptions' + Math.random().toString(36).substr(2,9);
        const uniqueId4 = 'propertyTO' + Math.random().toString(36).substr(2,9);
        const uniqueId5 ='addClassPropertyTO' + Math.random().toString(36).substr(2,9);
        const uniqueChanges ='changes2' + Math.random().toString(36).substr(2,9);
        newChangedElement3 =uniqueId;
        newChangedElement4 =uniqueId2;
        newChangedElement5 =uniqueId3;
        newChangedElement6 =uniqueId4;
        newChangedElement9 =uniqueId5;
        newChanges2=uniqueChanges;
        const divContent=`<div class=" border border-dark" id="next2" style="display:flex;">
             <div class="row ">
                <div class="col-sm-6 m-5">
                <p>Choose class to change from: &nbsp; &nbsp; &nbsp;*</p>
                <select id="${uniqueId}" name="classPropertyOptions">
                </select>
                </div>
            <div class="col-sm-6 m-5">
                <p>Choose class to change to: &nbsp; &nbsp; &nbsp;*</p>
                <input class="form-control" id="${uniqueId2}" type="text" list="lst-autocomplete1" placeholder="E99_Identfies_As">
                <datalist id="lst-autocomplete1">
                </datalist>

            </div> 
            </div>
         <div class="row">
            <div class="col-sm-6 m-5">
                <p>Choose property to change from: &nbsp; &nbsp; &nbsp;*</p>
                <select id="${uniqueId3}" name="propertiesOptions">
              </select>
            </div>
            <div class="col-sm-6 m-5">
                <p>Choose property to change to: &nbsp; &nbsp; &nbsp;*</p>
                <input class="form-control" id="${uniqueId4}" type="text" list="lst-autocomplete2" placeholder="P90_has_value">
                
                <datalist id="lst-autocomplete2">
                </datalist>
    
            </div>
             <br>
            <div class="col-sm-6 m-5">
                <p>Choose additional class to add(OPTIONAL): &nbsp; &nbsp; &nbsp;*</p>
                <input class="form-control" id="${uniqueId5}" type="text" list="lst-autocomplete1" placeholder="E99_Identfies_As">
                <datalist id="lst-autocomplete4">
                </datalist>
            </div>
             <div class="col-sm-6 m-5">
                 <button type="submit" class="btn btn-dark" onclick="addChange2()">Set Changes</button>
             </div>
            <div id="${uniqueChanges}">
                    
            </div>
            </div>
            </div>
                `;
        document.getElementById("new1").style= "flex";
        document.getElementById("new1").innerHTML+=divContent;
        const newSelectElement = document.getElementById(uniqueId);
        const newSelectElement1 = document.getElementById(uniqueId2);
        const newSelectElement2 = document.getElementById(uniqueId3);
        const newSelectElement3 = document.getElementById(uniqueId4);
        const newSelectElement4 = document.getElementById(uniqueId5);
        populateClassOptions(newSelectElement);
        console.log("return1");
        populateAllClass(newSelectElement1);
        console.log("return2");
        populatePropertiesOptions(newSelectElement2);
        console.log("return3");
        populateAllPropertiesOptions(newSelectElement3);
        console.log("return4");
        populateAllClass(newSelectElement4);
    }
}
function populatePropertiesOptions(selectElement) {
    console.log("populate properties options with :"+pr);
    if(!iamsecond){
        pr=pr.split('\n');
        iamsecond++;
    }
    for (let i = 0; i < pr.length; i++) {
            selectElement.innerHTML+="<option value="+pr[i]+">"+pr[i]+"</option>";  
    }
}

function populateAllPropertiesOptions(selectElement){
        for (let i = 0; i < all.length; i++) {
        if (all[i][0] === "P") {
            selectElement.innerHTML+="<option value="+globalPref+":"+all[i]+">"+"</option>";
        }
    }
}

function nextstep3(){
    document.getElementById("button3").style.display="flex";
    document.getElementById("button1").style.display="none";
    document.getElementById("button2").style.display="none";

    if(document.getElementById("next3").style.display==="none"){
        changesArr.length=0;
        document.getElementById("next3").style.display="flex";
        document.getElementById("before1").style.display="none";
        document.getElementById("next2").style.display="none";
        document.getElementById("next1").style.display="none";
        document.getElementById("new").style.display="none";
        document.getElementById("new1").style.display="none";
         document.getElementById("new2").innerHTML="";
        

       
    }else{
        document.getElementById("before1").style.display="none";
        const uniqueId = 'PpropertiesOptions' + Math.random().toString(36).substr(2, 9);
        const uniqueId2 = 'PpropertyTO' + Math.random().toString(36).substr(2, 9);
        const uniqueChanges ='changes1' + Math.random().toString(36).substr(2, 9);
        newChangedElement7=uniqueId;
        newChangedElement8=uniqueId2;
        newChanges1= uniqueChanges;
        const divContent=`<div class="border border-dark" id="next3" style="display:flex;">
          <div class="row ">
            <div class="col-sm-6">
                <p>Choose property to change from: &nbsp; &nbsp; &nbsp;*</p>
                <select id="${uniqueId}" name="propertiesOptions">
              </select>
            </div>
            <div class="col-sm-6">
                <p>Choose property to change to: &nbsp; &nbsp; &nbsp;*</p>
                <input class="form-control" id="${uniqueId2}" type="text" list="lst-autocomplete2" placeholder="P90_has_value">
                <button type="submit" class="btn btn-dark" onclick="addChange3()">Set Changes</button>
              
                <datalist id="lst-autocomplete3">
                </datalist>
    
            </div>
              <div id="${uniqueChanges}"class="col-sm-8">
                    
            </div>
            </div>
        </div>
`;
        
        document.getElementById("new2").style= "flex";
        document.getElementById("new2").innerHTML+=divContent;
        console.log("eftasa edw kai eimai "+document.getElementById(newChanges1));
        const newSelectElement = document.getElementById(uniqueId);
        const newSelectElement1 = document.getElementById(uniqueId2);
        populatePropertiesOptions(newSelectElement);
        console.log("return1");
        populateAllPropertiesOptions(newSelectElement1);
        console.log("return2");

    }
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
        getX3MLFile('uploadForm0','fileUpload0','addedContent0','uploadServlet');
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
           
            let apanthsh= xhr.responseText; 
            if(servletName=="uploadServlet" || servletName=="uploadServlet1" ){
                console.log("eimai o servlet:"+servletName);                
                console.log(apanthsh);
                x3mlfile=apanthsh;
                var classes = apanthsh.match(/<type>(.*?)<\/type>/g).map(match => match.replace(/<\/?type>/g, '').trim());
                var property = apanthsh.match(/<relationship>(.*?)<\/relationship>/g).map(match => match.replace(/<\/?relationship>/g, '').trim());
                var namespaces = apanthsh.match(/<namespace prefix=".*?" uri=".*?\/?>/g).map(match => match.trim());

                namespaces=apanthsh.split("[")[1].trim();
               
                const regex = /prefix="(\w+)".*?uri="([^"]+)"/g;
               

                let match;
                while ((match = regex.exec(namespaces)) !== null) {
                  const prefix = match[1];
                  const uri = match[2];
                  namespaceMap.set(prefix, uri); 
                }
                namespaceMap.forEach((uri, prefix) => {
                  console.log(`Prefix: ${prefix} -> URI: ${uri}`);
                });
                console.log("eimai klaseis"+classes);
                console.log("eimai property"+property);
                cl=cl+classes;
                pr=pr+property;
                cl=cl.replace(/,/g, '\n');
                pr=pr.replace(/,/g, '\n');
                console.log(cl);
                console.log(pr);

            }else{
                console.log("eimai o allos servlet:"+servletName);
                console.log(apanthsh);
                const firstLine = apanthsh.trim().split('\n')[0];
                const value = firstLine.split('\t')[1];
                console.log(value);
                globalURI=value;
                console.log(namespaceMap);

                   for (const [prefix, uri] of namespaceMap) {
                       console.log(uri);
                       console.log(globalURI);
                       if(uri===globalURI){
                            console.log("mphka");
                            globalPref=prefix;
                            cls=apanthsh.split("classes")[0];
                            prt=apanthsh.split("classes")[1];
                            cls=removeSubstr(cls,globalURI);
                            prt=removeSubstr(prt,globalURI);
                            break;
                       }else{
                            globalPref="ns1";
                            cls=apanthsh.split("classes")[0];
                            prt=apanthsh.split("classes")[1];
                            cls=removeSubstr(cls,globalURI);
                            prt=removeSubstr(prt,globalURI);

                        }
                    }                
      //          cls=apanthsh.split("classes")[0];
        //        prt=apanthsh.split("classes")[1];
                console.log("all classes are :" +cls);
                console.log("all properties are : "+prt);
                console.log(globalPref);
                let clpr=(removeSubstr(apanthsh,globalURI));
        //        cls=removeSubstr(cls,globalURI);
          //      prt=removeSubstr(prt,globalURI);
                console.log(clpr);
                console.log("I have the prefix-> " + globalPref);
                all=all+clpr;
            }
        } else {
            document.getElementById(contentId).innerHTML = "Request failed. Returned status of " + xhr.status;
        }
    };
  
    xhr.open("POST", servletName, true);
    xhr.send(formData);

}
function removeSubstr(str, substring) { 
  const regex = new RegExp(substring, 'g');
  return str.replace(regex, '\n');
}

function getAll(cl,pr,cls,prt,globalPref,globalURI){
    const simpleCl=cl.split('\n');
    const simplePr=pr.split('\n');    
    const allCl=cls.split('\t');
    const allPr=prt.split('\t');
    var allFileClasses = [];
    var allFileProperties = [];
    const trimmedClasses = simpleCl.map(line => line.trim()).filter(line => line.length > 0);
    const trimmedProperties = simplePr.map(line => line.trim()).filter(line => line.length > 0);
    const trimmedAllClasses=allCl.map(line => line.trim()).filter(line => line.length > 0);
    const trimmedAllProperties=allPr.map(line => line.trim()).filter(line => line.length > 0);

    for(var j=0; j<trimmedClasses.length;j++){
        document.getElementById("classOptions").innerHTML+="<option value="+trimmedClasses[j]+">"+trimmedClasses[j]+"</option>";
        document.getElementById("classPropertyOptions").innerHTML += "<option value=" + trimmedClasses[j] + ">"+trimmedClasses[j] + "</option>";
        allFileClasses.push(trimmedClasses[j]);
    }
    for(var i=0; i<trimmedAllClasses.length;i++){
        document.getElementById("lst-autocomplete").innerHTML += "<option value=" +globalPref+":"+trimmedAllClasses[i] + "></option>";
        document.getElementById("lst-autocomplete1").innerHTML += "<option value=" +globalPref+":"+ trimmedAllClasses[i] + "></option>";   
    }
    
    for(var i=0; i< trimmedAllProperties.length;i++){
        document.getElementById("lst-autocomplete2").innerHTML += "<option value=" + globalPref+":"+ trimmedAllProperties[i] + "></option>";
        document.getElementById("lst-autocomplete3").innerHTML += "<option value=" + globalPref+":"+ trimmedAllProperties[i] + "></option>";
    }
    
    for(var j=0; j<trimmedProperties.length;j++){
        document.getElementById("propertiesOptions").innerHTML += "<option value=" +trimmedProperties[j] + ">" +trimmedProperties[j] + "</option>";
        document.getElementById("PpropertiesOptions").innerHTML += "<option value=" + trimmedProperties[j] + ">" +trimmedProperties[j] + "</option>";
        allFileProperties.push(trimmedProperties[j]);
    }
    if (typeof allFileClasses === 'string') {
        cl = allFileClasses.split(',');
    } else if (Array.isArray(allFileClasses)) {
        cl = allFileClasses;
    } else {
        console.error('Unexpected type for allFileClasses:', typeof allFileClasses);
    }
    if (typeof allFileProperties === 'string') {
        pr = allFileProperties.split(',');
    } else if (Array.isArray(allFileProperties)) {
        pr = allFileProperties;
    } else {
        console.error('Unexpected type for allFileProperties:', typeof allFileProperties);
    }

console.log("Type of cl:", typeof cl);
console.log(cl);
console.log("Type of pr:", typeof pr);
console.log(pr);

for (let i = 0; i < cl.length; i++) {
    console.log(cl[i]);  
}
for (let i = 0; i < pr.length; i++) {
    console.log(pr[i]);  
}
 
}

function classOnlyPost(jsonString){
    var data = JSON.stringify(jsonString);
    var xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.log("OK");
        }else if(xhttp.status === 403){
            return 1;
        } 
    };
    xhttp.open("POST","classOnlyPost");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(data);   
}

function classPropertyPost(jsonString){
    var data = JSON.stringify(jsonString);
    var xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.log("OK");
        }else if(xhttp.status === 403){
            return 1;
        } 
    };
    xhttp.open("POST","classPropertyPost");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(data);   
}

function removePost(jsonString){
    var data = JSON.stringify(jsonString);
    console.log(data);
    console.log(typeof(data));
    var xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.log("OK");
        }else if(xhttp.status === 403){
            return 1;
        } 
    };
    xhttp.open("POST","removePost");
    xhttp.setRequestHeader("Content-type","application/json");
    xhttp.send(data);  
}

function translationPost(x3mlfile, newBlob) {
    const index=x3mlfile.indexOf("</x3ml>");
    x3mlfile = x3mlfile.substring(0, index + "</x3ml>".length);
    const data = {
        x3mlfile: x3mlfile, 
        newBlob: newBlob   
    };

    console.log(typeof(x3mlfile));
    console.log(typeof(newBlob));
    console.log(x3mlfile);
    console.log(newBlob);
    
    // Create a new XMLHttpRequest
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "translationPost", true);
    xhttp.setRequestHeader("Content-type", "application/json");

    // Set the response type to 'blob'
    xhttp.responseType = "blob"; 

    xhttp.onload = function() {
        if (xhttp.status === 200) {
            console.log("Translation successful!");

            // Create a link element to trigger the download
            const url = window.URL.createObjectURL(xhttp.response);
            const a = document.createElement("a");
            a.style.display = "none";
            a.href = url;
            a.download = "newX3ML_out.x3ml";  // The default file name for download
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
        } else {
            console.log("Error occurred: " + xhttp.status);
        }
    };

    xhttp.onerror = function() {
        console.error("Request failed");
    };

    // Send the JSON data as a string
    xhttp.send(JSON.stringify(data));
}

