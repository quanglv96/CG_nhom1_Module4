$().ready(function () {
    $('#formCreateAccount').validate({
        rules: {
            "newUsername": {
                required: true
            },
            "username": {
                required: true,
            },
            "newPassword": {
                required: true,
                minlength: 6,
                maxlength: 8
            },
            "newRe-Password": {
                required: true,
                equalTo: "#newPassword"
            },
            "phone": {
                required: true,
                number: true,
                minlength: 10,
                maxlength: 10
            },
            "email": {
                required: true,
                email: true,
            }
        },
        messages: {
            "name": {
                required: "Name cannot be left blank"
            },
            "username": {
                required: "username cannot be left blank"
            },
            "password": {
                required: "Name cannot be left blank",
                minlength: "lengths from 6 to 8",
                maxlength: "lengths from 6 to 8"
            },
            "re-Password": {
                required: "Name cannot be left blank",
                equalTo: "Re-password is not the same as Password"
            },
            "phone": {
                required: "Name cannot be left blank",
                number: "phải là số",
                minlength: "10 characters phone number",
                maxlength: "10 characters phone number"
            },
            "email": {
                required: "Name cannot be left blank",
                email: "Email not authenticated"
            }
        },
        submitHandler: function () {
            createAccount();
        }

    });
    event.preventDefault();
});


function createAccount() {
    let formData = new FormData($('#formCreateAccount')[0]);
    $.ajax({
        type: "POST",
        data: formData,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        //tên API
        url: "http://localhost:8080/users",
        success: function () {
            alert("Đăng kí tài khoản thành công, vui lòng đăng nhập");
            modalSignIn.style.display = "none";
            modalLogIn.style.display = "block";
        },
        error: function () {
            alert("Tên đăng nhập đã trùng, vui lòng sử dụng tên đăng nhập khác");
        }
    });
    event.preventDefault();
}
