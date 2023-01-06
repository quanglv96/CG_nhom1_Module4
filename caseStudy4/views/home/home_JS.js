window.onload = function () {
    displaySongTrend();
}

function displaySongTrend() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/songs/listTrending",
        success: function (data) {
            let content = '<div class="wrapper">'
            for (let i = 0; i < data.length; i++) {
                if (i === 10) {
                    break;
                }
                content += getListSong(data[i]);

            }
            content += "</div>";
            document.getElementById('listTrending').innerHTML = content;
        }
    })
}

function getListSong(songsList) {
    return `<div class="item">
        <a href="${songsList.id}"><img width="150px" height="150px" src="/views/upload_img/${songsList.avatar}"></a><br>
        <p class="nameSongList name-song" ><a href="${songsList.id}">${songsList.name}</a><br></p>
        <p class="nameSongList name--user" ><a href="${songsList.id}">${songsList.users.name}</a></p>
    </div>`
}


function submitSearch(choice) {
    let text = document.getElementById("input_search").value;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/search?search=" + text,
        success: function (data) {
            $(".body-content").empty();
            switch (choice) {
                case 1:
                    getSongSearch(data[0])
                    break;
                case 2:
                    getPlaylistSearch(data[1])
                    break;
                case 3:
                    getUserSearch(data[2])
                    break;
                default:
                    getSongSearch(data[0])
                    getPlaylistSearch(data[1]);
                    getUserSearch(data[2]);
                    break;
            }
            document.getElementById("resultSearch").innerHTML = content;
        }
    });
    event.preventDefault();
}

function getSongSearch(listSong) {
    for (let i = 0; i < listSong.length; i++) {
        let songs = listSong[i];
        let content = `<div class="content-item-song row mt-4">
            <div class="item-image col-3">
            <div class="image"><img class="song-avt" src="/views/upload_img/${songs.avatar}"></div>
            </div>
            <div class="item-desc col-9">
                <div class="desc-box d-flex align-items-center">
                    <div class="play-btn-${songs.id} play-btn d-flex justify-content-center align-items-center me-2">
                        <i class="fa-solid fa-play"></i>
                    </div>
                    <div class="first-desc-row d-inline-block">
                        <p class="user-upload m-0 grey-text small-text">${songs.users.name}</p>
                        <p class="item-name m-0">${songs.name}</p>
                    </div>
                    <div class="second-desc-row d-inline-block right-text">
                        <p class="date-upload m-0 grey-text small-text">${songs.date}</p>
                        <p class="tag-ref m-0 px-2 small-text">#${songs.tagsList[0].name}</p>
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
                                <p class="m-0 grey-text">${user.name}</p>
                                <p class="m-0 grey-text">${user.address}</p>
                            </div>
                        </div>
                    </div>
                </div>`
        $(".body-content").append(content);
    }
}
