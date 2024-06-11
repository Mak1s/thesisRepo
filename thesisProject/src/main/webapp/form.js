
function navigateTo(URL){
    document.getElementById('myForm').action = URL;
    document.getElementById('myForm').submit();
    console.log(document.getElementById('myForm'));
}