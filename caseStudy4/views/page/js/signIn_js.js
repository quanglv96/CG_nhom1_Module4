function register(){
    let re_password=document.getElementById("re-password").value;
    let password=document.getElementById("c-password").value;
    if(re_password!==password){
        return alert("New password and re-password are not the same");
    }
    let name=document.getElementById("c-name").value;
    let username=document.getElementById("c-username").value;
    let phone=document.getElementById("phone").value;
let user={
    "name":name,
    "username":username,
    "password": password,
    "phone":phone
}

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(user),
        //tên API
        url: "http://localhost:8080/users/register",
        success: function (data) {
            alert("ok")
        },
        error: function (error) {
            alert(error.responseText);
            openModal('.login-modal');
        }
    });
}