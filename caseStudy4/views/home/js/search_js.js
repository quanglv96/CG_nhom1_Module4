function tabSearch(choice){
    let textSearchLayout = document.getElementById("input_search").value;
    $(".body-content").empty();
    $(".body-content").append(`<h2>Search results for keyword:"` + textSearchLayout + `"<h2>`);
    let listSong=JSON.parse(localStorage.getItem("listSong"))
    let listPlaylist=JSON.parse(localStorage.getItem("listPlaylist"))
    let user=JSON.parse(localStorage.getItem("listUser"))
    let listUser;
    let lengthSong=listSong.length
    let lengthPlaylist=listPlaylist.length
    let lengthUser=user.length
    if (lengthUser===undefined){
        lengthUser=1;
        listUser=[JSON.parse(localStorage.getItem("listUser"))];
    }else {
        listUser=user;
    }
    switch (choice) {
        case 1:
            $(".body-content").append(`<h4 style="color: #848687">Found ` + lengthSong + " tracks</h4>");
            getSongSearch(listSong);
            break;
        case 2:
            $(".body-content").append(`<h4 style="color: #848687">Found ` + lengthPlaylist + " playlists</h4>");
            getPlaylistSearch(listPlaylist)
            break;
        case 3:
            $(".body-content").append(`<h4 style="color: #848687">Found `+ lengthUser + " people</h4>");
            getUserSearch(listUser)
            break;
        case 0:
            $(".body-content").append(`<h4 style="color: #848687">Found ` +lengthSong + " tracks, " + lengthPlaylist + " playlists, " + lengthUser + " people</h4>");
            getSongSearch(listSong);
            getPlaylistSearch(listPlaylist)
            getUserSearch(listUser)
            break;
    }
}
function submitSearch() {
    let textSearchLayout = document.getElementById("input_search").value;
    if (textSearchLayout === "") {
        textSearchLayout = localStorage.getItem("textSearchHome")
        localStorage.removeItem("textSearchHome");
        document.getElementById("input_search").value = textSearchLayout;
    }
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/search?search=" + textSearchLayout,
        success: function (data) {
            localStorage.setItem("listSong", JSON.stringify(data[0]));
            localStorage.setItem("listPlaylist", JSON.stringify(data[1]));
            localStorage.setItem("listUser", JSON.stringify(data[2]));
            tabSearch(0);
        }
    });
    event.preventDefault();
}
function getSongSearch(listSong) {
    for (let i = 0; i < listSong.length; i++) {
        let songs = listSong[i];
        let tags = songs.tagsList[0];
        let listTag = "";
        if (tags != null) {
            listTag = `<p className="tag-ref m-0 grey-text small-text" style="cursor: pointer" onclick="searchTag(${tags.id})">#${tags.name}</p>`
        }
        let content = `<div class="content-item-song row mt-4">
            <div class="item-image col-3">
            <div class="image"><img onclick="redirectSong(${songs.id})" style="cursor:pointer" class="song-avt" src="/views/upload_img/${songs.avatar}"></div>
            </div>
            <div class="item-desc col-9">
                <div class="desc-box d-flex align-items-center">
                    <div class="play-btn-${songs.id} play-btn d-flex justify-content-center align-items-center me-2">
                        <i class="fa-solid fa-play"></i>
                    </div>
                    <div class="first-desc-row d-inline-block">
                        <a style="color: #3c3b3b;text-decoration: none;cursor:pointer" class="user-upload m-0 grey-text small-text" onclick="searchSongByUser(${songs.users.id})">${songs.users.name}</a><br>
                        <a onclick="redirectSong(${songs.id})" class="item-name m-0" style="color: black;text-decoration: none;cursor:pointer">${songs.name}</a>
                    </div>
                    <div class="second-desc-row d-inline-block right-text">
                        <p class="date-upload m-0 grey-text small-text">${songs.date}</p>` + listTag + `
                        
                    </div>
                </div>
                <div class="play-box">
                    <div id="wave-${songs.id}">
                    
                    </div>
                    <div class="action-box d-flex justify-content-between mt-2">
                        <div class="like-box">
                                        <span
                                            class="like-count small-text grey-text border py-1 px-2 me-1 border-radius-1">
                                            <i class="fa-solid fa-heart"></i>${songs.likes}
                                        </span>
                            <span class="like-count small-text grey-text border py-1 px-2 border-radius-1">
                                            <i class="fa-solid fa-square-plus"></i> Add to playlist
                                        </span>
                        </div>
                        <div class="like-box">
                                        <span
                                            class="play-count small-text grey-text border py-1 px-2 me-1 border-radius-1">
                                            <i class="fa-solid fa-play"></i>${songs.likes}
                                        </span>
                            <span class="comment-count small-text grey-text border py-1 px-2 border-radius-1">
                                            <i class="fa-solid fa-comment"></i>${songs.views}
                                        </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>`;
        $(".body-content").append(content);
        createWave(songs.id, `/views/upload_mp3/${songs.audio}`);
    }
}

function getPlaylistSearch(playList) {
    for (let i = 0; i < playList.length; i++) {
        let list = playList[i];
        let content = `<div class="content-item-song row mt-4">
                    <div class="item-image col-3">
                        <div class="image"><img class="playlist-avt" src="/views/upload_img/${list.avatar}"></div>
                    </div>
                    <div class="item-desc col-9 d-flex align-items-center">
                        <div class="d-block">
                            <div>
                                <p class="m-0 big-text">Playlist</p>
                                <p class="m-0 grey-text">${list.name}</p>
                                <p class="m-0 grey-text">${list.description}</p>
                            </div>
                            <div class="action-box d-flex justify-content-between mt-2">
                                <div class="like-box">
                                        <span class="like-count small-text grey-text border py-1 px-2 me-1 border-radius-1 pointer">
                                            <i class="fa-solid fa-heart"></i>${list.likes}
                                        </span>

                                </div>
                                <div class="like-box">
                                        <span class="play-count small-text grey-text border py-1 px-2 me-1 border-radius-1 pointer">
                                            <i class="fa-solid fa-play"></i> ${list.likes}
                                        </span>
                                    <span class="comment-count small-text grey-text border py-1 px-2 border-radius-1 pointer">
                                            <i class="fa-solid fa-comment"></i> ${list.views}
                                        </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`;
        $(".body-content").append(content);
    }
}

function getUserSearch(listUser) {
    for (let i = 0; i < listUser.length; i++) {
        let user = listUser[i];
        let content = `<div class="content-item-user row mt-4">
                    <div class="item-image col-3">
                    <div class="image"><img class="user-avt" src="/views/upload_img/${user.avatar}"></div>
                    </div>
                    <div class="item-desc col-9 user-desc">
                        <div class="d-flex align-items-center">
                            <div>
                                <p class="m-0 big-text">${user.username}</p>
                                <a style="color: black;text-decoration: none" onclick="searchSongByUser(${user.id})" class="m-0 grey-text">${user.name}</a>
                                <p class="m-0 grey-text">${user.address}</p>
                            </div>
                        </div>
                    </div>
                </div>`
        $(".body-content").append(content);
    }
}

function searchSongByUser(idUser) {
    localStorage.removeItem("searchIdUser")
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/search/songsByUser?idUser=" + idUser,
        success: function (data) {
            document.getElementById("input_search").value = data[0].name;
            $(".body-content").empty();
            localStorage.setItem("listSong", JSON.stringify(data[1]));
            localStorage.setItem("listPlaylist", JSON.stringify(data[2]));
            localStorage.setItem("listUser", JSON.stringify(data[0]));
            tabSearch(0);
        }
    });
}