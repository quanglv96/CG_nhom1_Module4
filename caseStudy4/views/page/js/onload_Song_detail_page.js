function getSongByID(idSong) {
    let userLogin = JSON.parse(localStorage.getItem("userLogin"));
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/songs/" + idSong,
        success: function (data) {
            if (userLogin !== null) {
                document.getElementById("user-avatar").setAttribute("src", `../upload_img/` + userLogin.avatar)
            }
            document.getElementById("name-Songs").innerText = data.name;
            document.getElementById("name-user").innerText = data.users.name;
            document.getElementById("name-user").setAttribute("onclick", `redirectSearchUser(${data.users.id})`);
            document.getElementById("btnLike").setAttribute("onclick", `clickLike_Song(${data.id})`);
            $('#click-like').hide();
            document.getElementById("time-upload").innerText = data.date;
            document.getElementById("avatar-song").setAttribute("src", `../upload_img/${data.avatar}`)
            document.getElementById("like-song").innerText = data.likes;
            document.getElementById("view-song").innerText = data.views;
            //comment
            document.getElementById("comment-song").innerText = data.views;
            document.getElementById("avatar-User-Song").setAttribute("src", `../upload_img/${data.users.avatar}`)
            document.getElementById("name-user2").innerText = data.users.name;
            if(document.getElementById("comment-2") != null) {
                document.getElementById("comment-2").innerText = data.views;
            }

            createDetailWaveForm('#audio-wave', '.play-button', `../upload_mp3/${data.audio}`)
        }
    })
}

function getCommentByIdSong(idSong) {
    $.ajax({
        type: "GET",
        data: JSON.stringify(idSong),
        url: "http://localhost:8080/comment/song/" + idSong,
        success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                let comment = data[i];
                let time = moment(comment.date).fromNow();
                content += `<div class="comment-item d-flex align-items-center justify-content-between mb-3">
                                <div class="c-item-left d-flex align-items-center">
                                    <div class="c-avt me-2">
                                        <img class="c-img" src="../upload_img/${comment.users.avatar}" alt="">
                                    </div>
                                    <div class="c-desc">
                                        <p class="m-0 c-uname grey-text font-12">${comment.users.name}</p>
                                        <p class="m-0 c-content font-12">${comment.content}</p>
                                    </div>
                                </div>
                                <div class="c-item-right font-12">        
                                    <span class="c-time">${time}</span>
                                </div>
                            </div>`
            }
            document.getElementById("list-comment").innerHTML = content;
        }
    })
}

function randomSongs(idSong) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/songs",
        success: function (data) {
            let content="";

            for (let i = 0; i < 4; i++) {
                let index = Math.floor(Math.random() * data.length)
                if(data[index].id!==idSong){
                    content+=getSongRandom(data[index])
                }else {
                    i=i-1;
                }
            }
            document.getElementById("randomSongs").innerHTML=content;
        }


    });
}

function getSongRandom(song) {
return `<div class="random-item d-flex mb-1 pointer" onclick="redirectSong(${song.id})">
                            <div class="random-img">
                                <img class="fit-img" src="../upload_img/${song.avatar}" alt="">
                            </div>
                            <div class="random-song-desc ms-2">
                                <div class="grey-text line-16 font-12 fit-head">${song.users.username}</div>
                                <div class="line-16 font-12">${song.name}</div>
                                <div class="react-box">
                                        <span class="font-12 grey-text pe-2">
                                            <i class="fa-solid fa-heart"></i> ${song.likes}
                                        </span>
                                    <span class="font-12 grey-text pe-2">
                                            <i class="fa-solid fa-play"></i> ${song.views}
                                        </span>
                                    <span class="font-12 grey-text">
                                            <i class="fa-solid fa-comment"></i> ${song.views}
                                        </span>
                                </div>
                            </div>
                        </div>`
}

function redirectSearchUser(idUser) {
    localStorage.removeItem("textSearch");
    localStorage.setItem("searchIdUser", idUser);
    window.location = "../../discovery/layout/layout.html";
}
var wavesurfer;
function createDetailWaveForm(selector, btnSelector, url) {
    wavesurfer = WaveSurfer.create({
        container: selector,
        waveColor: 'white',
        progressColor: 'black',
        barWidth: 1,
        height: 115,
        hideScrollbar: true,
        hideCursor: true,
    });
    wavesurfer.load(url);

    let playBtn = $(btnSelector)[0];
    let isPlay = true;
    playBtn.onclick = function() {
        if (isPlay) {
            $(btnSelector).html('<i class="font-24 fa-solid fa-pause"></i>')
            isPlay = false;
        } else {
            $(btnSelector).html('<i class="font-24 fa-solid fa-play"></i>')
            isPlay = true;
        }
        wavesurfer.playPause();
    }
}