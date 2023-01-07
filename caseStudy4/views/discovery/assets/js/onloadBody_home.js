function onloadBody_home() {
    renderTop10Songs();
    renderNewSongs();
    renderTop10Playlist();
    renderNewPlaylist();}

function renderTop10Songs() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/songs/listTrending",
        success: function (data) {
            let content = ""
            for (let i = 0; i < data.length; i++) {
                if (i === 10) {
                    break;
                }
                content += getListSong(data[i]);
            }
            document.getElementById("top10Songs").innerHTML = content;
            $('.trending-song').slick({
                infinite: true,
                slidesToShow: 5,
                slidesToScroll: 5,
                prevArrow: ".trending-s-left",
                nextArrow: ".trending-s-right"
            });
        }
    })
}

function renderNewSongs() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/songs/newSongs",
        success: function (data) {
            let content = ""
            for (let i = 0; i < data.length; i++) {
                if (i === 10) {
                    break;
                }
                content += getListSong(data[i]);
            }
            document.getElementById("listNewSongs").innerHTML = content;
            $('.newest-song').slick({
                infinite: true,
                slidesToShow: 5,
                slidesToScroll: 5,
                prevArrow: ".newest-s-left",
                nextArrow: ".newest-s-right"
            });
        }
    })
}

function renderTop10Playlist() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/playlist/listTrending",
        success: function (data) {
            let content = ""
            for (let i = 0; i < data.length; i++) {
                if (i === 10) {
                    break;
                }
                content += getPlaylist(data[i]);
            }
            document.getElementById("top10Playlist").innerHTML = content;
            $('.top-playlist').slick({
                infinite: true,
                slidesToShow: 5,
                slidesToScroll: 5,
                prevArrow: ".top-p-left",
                nextArrow: ".top-p-right"
            });
        }
    })
}

function renderNewPlaylist() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/playlist/newPlaylist",
        success: function (data) {
            let content = ""
            for (let i = 0; i < data.length; i++) {
                if (i === 10) {
                    break;
                }
                content += getListSong(data[i]);
            }
            document.getElementById("listNewPlaylist").innerHTML = content;
            $('.new-playlist').slick({
                infinite: true,
                slidesToShow: 5,
                slidesToScroll: 5,
                prevArrow: ".new-p-left",
                nextArrow: ".new-p-right"
            });
        }
    })
}

function getListSong(songsList) {
    return `<div class="item-contain col col-3 p-3">
                        <div class="item">
                            <a href="${songsList.id}"><img class="slider-img" src="/views/upload_img/${songsList.avatar}" alt=""/></a>
                            <p class="m-0 item-title nameSongList name-song"><a href="${songsList.id}">${songsList.name}</a></p>
                            <p class="m-0 item-description font-12 grey-text nameSongList name--use"><a href="${songsList.users.id}">${songsList.users.name}</a></p>
                        </div>
                    </div>`
}

function getPlaylist(playlist) {
    return `<div class="item-contain col col-3 p-3">
                        <div class="item">
                            <a href="${playlist.id}"><img class="slider-img" src="/views/upload_img/${playlist.avatar}" alt=""></a>
                            <p class="m-0 item-title nameSongList name-song"><a href="${playlist.id}">${playlist.name}</a></p>
                            <p class="m-0 item-description font-12 grey-text nameSongList name--use"><a href="${playlist.users.id}">${playlist.users.name}</a></p>
                        </div>
                    </div>`
}