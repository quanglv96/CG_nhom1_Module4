var modalSignIn = document.getElementById("formSignIn");
var modalCreateAccount=document.getElementById("formCreateAccount");

// Get the button that opens the modal
var turnOnModalSignIn = document.getElementById("onFormSignIn");
var turnOnModalCreateAccount = document.getElementById("onFormCreateAccount");

// Get the <span> element that closes the modal
var closeModal = document.getElementById("closeModal");

// When the user clicks on the button, open the modal
turnOnModalSignIn.onclick = function () {
    modalSignIn.style.display = "block";
}
turnOnModalCreateAccount.onclick = function () {
    modalCreateAccount.style.display = "block";
}
// When the user clicks on <span> (x), close the modal
closeModal.onclick = function () {
    modalSignIn.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target !== modalSignIn) {
        modalSignIn.style.display = "none";
    }
}