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