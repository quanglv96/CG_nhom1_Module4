function redirectSong(id){
    localStorage.setItem("idSong",id);
    localStorage.setItem('userObject', JSON.stringify({
        "id": 2,
        "username": "truong123",
        "password": "12345678",
        "name": "Trường",
        "address": "HN",
        "email": "truong@gmail.com",
        "phone": "0311111111",
        "avatar": "img_3.png",
        "role": {
            "id": 1,
            "name": "USER"
        },
        "image": null
    }));
    window.location = "/caseStudy4/views/discovery/layout/details_body.html";
}