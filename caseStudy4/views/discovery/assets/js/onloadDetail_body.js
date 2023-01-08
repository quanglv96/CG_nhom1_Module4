window.onload = function () {
    onloadCheckLogin();
    let idSong = localStorage.getItem("idSong");
    getSongByID(idSong);
    getCommentByIdSong(idSong);

}
function getSongByID(idSong){
    let userLogin = JSON.parse(localStorage.getItem("userLogin"));
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/songs/" + idSong,
        success: function (data) {
            if(userLogin!==null){
                document.getElementById("user-avatar").setAttribute("src", `../../upload_img/`+userLogin.avatar)
            }
            document.getElementById("name-Songs").innerText = data.name;
            document.getElementById("name-user").innerText = data.users.name;
            document.getElementById("name-user").setAttribute("onclick",`redirectSearchUser(${data.users.id})`);
            document.getElementById("btnLike").setAttribute("onclick",`clickLike_Song(${data.id})`);
            $('#click-like').hide();
            document.getElementById("time-upload").innerText = data.date;
            document.getElementById("audio-wave").innerHTML = `<audio href="views/upload_mp3/${data.audio}></audio>`;
            document.getElementById("avatar-song").setAttribute("src", `../../upload_img/${data.avatar}`)
            document.getElementById("like-song").innerText = data.likes;
            document.getElementById("view-song").innerText = data.views;
            //comment
            document.getElementById("comment-song").innerText = data.views;
            document.getElementById("avatar-User-Song").setAttribute("src", `../../upload_img/${data.users.avatar}`)
            document.getElementById("name-user2").innerText = data.users.name;
            document.getElementById("comment-2").innerText = data.views;

        }
    })
}
function getCommentByIdSong(idSong){
    $.ajax({
        type: "GET",
        data:JSON.stringify(idSong),
        url: "http://localhost:8080/comment/song/"+idSong,
        success: function (data) {
            let content="";
            for (let i = 0; i < data.length; i++) {
                let comment=data[i];
                content+=`<div class="comment-item d-flex align-items-center justify-content-between mb-3">
                                <div class="c-item-left d-flex align-items-center">
                                    <div class="c-avt me-2">
                                        <img class="c-img" src="../../upload_img/${comment.users.avatar}" alt="">
                                    </div>
                                    <div class="c-desc">
                                        <p class="m-0 c-uname grey-text font-12">${comment.users.name}</p>
                                        <p class="m-0 c-content font-12">${comment.content}</p>
                                    </div>
                                </div>
                                <div class="c-item-right font-12">
                                    <span class="c-time">${comment.date}</span>
                                </div>
                            </div>`
            }
            document.getElementById("list-comment").innerHTML=content;
        }
    })
}
function redirectSearchUser(idUser){
    localStorage.removeItem("textSearch");
    localStorage.setItem("searchIdUser", idUser);
    window.location = "../../discovery/layout/layout.html";
}