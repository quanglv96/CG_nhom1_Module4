function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let login = {
        "username": username,
        "password": password
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(login),
        //tên API
        url: "http://localhost:8080/users/login",
        success: function (user) {
            alert("oke");
            localStorage.setItem("userLogin",JSON.stringify(user))
        },
        error: function (error) {
            modalLogIn.style.display = "block";
            alert(error.responseText);

        }
    });
}