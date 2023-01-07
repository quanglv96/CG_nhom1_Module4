$(document).ready(function () {
    $("#formCreateAccount").validate({
        rules: {
            "name":{
                required:true
            },
            "username": {
                required: true,
            },
            "password": {
                required: true,
                minlength: 6,
                maxlength: 8
            },
            "re-Password": {
                required: true,
                equalTo: "password"
            },
            "phone": {
                required: true,
                number:true,
                length: 10,
            },
            "email": {
                required: true,
                email: true,
            }
        },
        messages: {
            "name":{
                required:"Name cannot be left blank"
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
                number:"phải là chữ",
                length: "10 characters phone number"
            },
            "email": {
                required: "Name cannot be left blank",
                email: "Email not authenticated"
            }
        },
        submitHandler: function () {

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
                    modalSignIn.style.display = "none";
                    alert("Đăng kí tài khoản thành công, vui lòng đăng nhập");

                },
                error:function (){

                }
            });
            event.preventDefault();
        }
    });

});
