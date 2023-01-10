function onload_user_info() {
    let user = JSON.parse(localStorage.getItem("userLogin"));
    onloadCheckLogin();
    field_info_user(user);
    field_form_info(user);
    search_song_playlist_byUser(user)
}

function field_info_user(user) {
    document.getElementById("avt-user").setAttribute("src", `../upload_img/${user.avatar}`)
    document.getElementById("name-user").innerText = user.name;
    document.getElementById("username-user").innerText = user.username;
    document.getElementById("name-user").innerText = user.name;
    document.getElementById("name-user").innerText = user.name;
    document.getElementById("name-user").innerText = user.name;
}

function field_form_info(user) {
    document.getElementById("avt-info").setAttribute("src", `../upload_img/${user.avatar}`)
    document.getElementById("username").setAttribute("value", user.username)
    document.getElementById("name").setAttribute("value", user.name)
    document.getElementById("address").setAttribute("value", user.address)
    document.getElementById("email").setAttribute("value", user.email)
    document.getElementById("phone").setAttribute("value", user.phone)
}

function changePassword() {
    let current_password = document.getElementById("current-password").value;
    let new_password = document.getElementById("new-password").value;
    let re_new_password = document.getElementById("re-new-password").value;
    let user = JSON.parse(localStorage.getItem("userLogin"));
    if (current_password !== user.password) {
        return alert("wrong login password")
    }
    if (current_password === new_password) {
        return alert("New password must not be the same as the old password")
    }
    if (new_password !== re_new_password) {
        return alert("New password and re-password are not the same")
    }
    let users = {
        "id": user.id,
        "password": new_password
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(users),
        url: "http://localhost:8080/users/changePassword/",
        success: function () {
            alert("Đổi thành công, vui lòng đăng nhập lại");
            logOut()
        },
        error: function () {
            alert("lỗi")
        }
    });
}

function saveChangeInfo() {
    let formData = new FormData($('#formChange')[0])
    let user = JSON.parse(localStorage.getItem("userLogin"));
    $.ajax({
        type: "POST",
        data: formData,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        //tên API
        url: "http://localhost:8080/users/" + user.id,
        success: function (data) {
            localStorage.setItem("userLogin", JSON.stringify(data));
            // let user = JSON.parse(localStorage.getItem("userLogin"));
            location.reload();
        },
        error: function () {
            alert("lỗi")
        }
    });
}

function search_song_playlist_byUser(user) {
    $.ajax({
        type: "GET",
        //tên API
        url: "http://localhost:8080/search/songsByUser?idUser=" + user.id,
        success: function (data) {
            let contentSong = getSong(data[1]);
            let contentPlaylist = getPlayList(data[2]);
            document.getElementById("listSong-user").innerHTML = contentSong;
            document.getElementById("playlist-user").innerHTML = contentPlaylist;
        },
        error: function () {
            alert("lỗi")
        }
    });
}

function getSong(listSong) {
    let content = "";
    for (let i = 0; i < listSong.length; i++) {
        let songs = listSong[i];
        let listSinger = songs.singerList;
        let content_Singer = ""
        for (let j = 0; j < listSinger.length; j++) {
            let indexSinger = listSinger[j];
            content_Singer += indexSinger.name;
            if (j !== listSinger.length - 1) {
                content_Singer += ", ";
            }
        }
        let listTag = songs.tagsList;
        let content_Tag = ""
        for (let j = 0; j < listTag.length; j++) {
            let indexTag = listTag[j]
            content_Tag += `<span class="u-tag px-2 font-12">#${indexTag.name}</span>`
        }
        content += `<tr>
                                    <td>
                                        <div>${i + 1}</div>
                                    </td>
                                    <td>
                                        <div><a onclick="redirectSong(${songs.id})">${songs.name}</a></div>
                                    </td>
                                    <td>
                                        <div>${content_Singer}</div>
                                    </td>
                                    <td>
                                        <div>
                                            ${content_Tag}
                                        </div>
                                    </td>
                                    <td>
                                        <div class="pointer song-btn" onclick="fieldEditSong(${songs.id})">
                                            <i class="fa-solid fa-pen-to-square" ></i>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="pointer">
                                            <i class="fa-solid fa-trash" onclick="return confirmDelete('song',${songs.id})"></i>
                                        </div>
                                    </td>
                                </tr>`;
    }
    return content;
}

function getPlayList(playlist) {
    let content = "";
    for (let i = 0; i < playlist.length; i++) {
        let index = playlist[i]
        let listTag = index.tagsList;
        let contentTag = "";
        for (let j = 0; j < listTag.length; j++) {
            contentTag += `<span class= "u-tag px-2 font-12"> #${listTag[i].name}</span>`
        }
        content += `<tr>
                                    <td>
                                        <div>${i + 1}</div>
                                    </td>
                                    <td>
                                        <div>${index.name}</div>
                                    </td>
                                    <td>
                                        <div>${index.description}</div>
                                    </td>
                                    <td>
                                        <div>${index.dateCreate}</div>
                                    </td>
                                    <td>
                                        <div>${index.lastUpdate}</div>
                                    </td>
                                    <td>
                                        <div>
                                           ${contentTag}
                                        </div>
                                    </td>
                                    <td>
                                        <div class="pointer playlist-btn" onclick="fieldDataPlaylist(${index.id})" >                              
                                           <i class="fa-solid fa-pen-to-square modal-open" ></i>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="pointer">
                                            <i class="fa-solid fa-trash" onclick="return confirmDelete('playlist',${index.id})"></i>
                                        </div>
                                    </td>
                                </tr>`
    }
    return content;
}

function fieldEditSong(idSong) {
    $.ajax({
        type: "GET",
        //tên API
        url: "http://localhost:8080/songs/" + idSong,
        success: function (data) {
            document.getElementById("title").innerText = "EDIT SONG"
            document.getElementById("avt-s-init").setAttribute("src", `../upload_img/${data.avatar}`);
            document.getElementById("btn-upMp3").setAttribute("style", "display:none!important")
            document.getElementById("name-mp3").value = data.audio;
            document.getElementById("ip-nameSong").value = data.name;
            document.getElementById("composer").value = data.composer;
            document.getElementById("submit-form-song").setAttribute("onclick", `saveSong(${data.id})`)
            document.getElementById("submit-form-song").innerText = "Update"
            let listTag = data.tagsList;
            let contentTag = "";
            for (let j = 0; j < listTag.length; j++) {
                contentTag += `#${listTag[j].name}`
            }
            document.getElementById("s-list-tag").value = contentTag;
            let listSinger = data.singerList;
            let content_Singer = ""
            for (let j = 0; j < listSinger.length; j++) {
                content_Singer += listSinger[j].name;
                if (j !== listSinger.length - 1) {
                    content_Singer += ", ";
                }
            }
            document.getElementById("s-list-singer").value = content_Singer;
        },
        error: function () {
            alert("lỗi")
        }
    });
    openModal('.song-modal')
}
function fieldCreateSong(){
    document.getElementById("title").innerText = "ADD NEW SONG"
    document.getElementById("avt-s-init").setAttribute("src", `../upload_img/update-your-songs-avatar .png`);
    document.getElementById("btn-upMp3").setAttribute("style", "display:block")
    document.getElementById("name-mp3").value ="";
    document.getElementById("ip-nameSong").value ="";
    document.getElementById("composer").value = "";
    document.getElementById("submit-form-song").setAttribute("onclick", `createSongs()`)
    document.getElementById("submit-form-song").innerText = "ADD"
    openModal('.song-modal');
}
function fieldDataPlaylist(idPlaylist) {
    openModal('.playlist-modal');
}

function deleteSong(idSong) {
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/songs/"+idSong,
        success: function () {
            alert("Xóa thành công")
            let user = JSON.parse(localStorage.getItem("userLogin"));
            search_song_playlist_byUser(user);
        }
    })
}

function deletePlaylist(idPlaylist) {
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/playlist/"+idPlaylist,
        success: function () {
            alert("Xóa thành công")
            let user = JSON.parse(localStorage.getItem("userLogin"));
            search_song_playlist_byUser(user);
        }
    })
}

function confirmDelete(cate, id) {
    if (confirm("Are you sure you want to delete?")) {
        if (cate === 'song') {
            deleteSong(id)
        } else {
            deletePlaylist(id)
        }
    }
}