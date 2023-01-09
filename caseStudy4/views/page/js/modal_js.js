var modalSignIn = document.getElementById("modalCreateAccount");
var modalLogIn=document.getElementById("modalLogin");

// Get the button that opens the modal
var turnOnModalSignIn = document.getElementById("onFormSignIn");
var turnOnModalLogIn = document.getElementById("onFormLogIn");

// Get the <span> element that closes the modal
var closeModalLogin = document.getElementById("closeModalLogin");
var closeModalSignIn = document.getElementById("closeModalSignIn");

// When the user clicks on the button, open the modal
turnOnModalSignIn.onclick = function () {
    modalSignIn.style.display = "block";
}
turnOnModalLogIn.onclick = function () {
    modalLogIn.style.display = "block";
}
// When the user clicks on <span> (x), close the modal
closeModalLogin.onclick = function () {
    modalLogIn.style.display = "none";
}
closeModalSignIn.onclick = function () {
    modalSignIn.style.display = "none";
}


