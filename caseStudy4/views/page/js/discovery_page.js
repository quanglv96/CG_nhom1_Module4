function renderSong(song) {
    return  `<div class="item-contain col col-3 p-3">
                <div onclick="viewSongDetails(${song.id})" class="item-img">
                    <img class="slider-img" src="/assets/img/audio_img/${song.avatar}" alt="">
                    <p class="m-0 item-title text-start">${song.name}</p>
                    <p class="m-0 item-description font-12 grey-text text-start">${song.users.name}</p>
                </div>
            </div>`
}

function renderPlaylist(playlist) {
    return  `<div class="item-contain col col-3 p-3">
                <div class="item">
                    <img class="slider-img" src="/assets/img/${playlist.avatar}" alt="">
                    <p class="m-0 item-title">${playlist.name}</p>
                    <p class="m-0 item-description font-12 grey-text">${playlist.users.name}</p>
                </div>
            </div>`
}

function renderHTML(data, callback, selector) {
    let html = "";
    for (const d of data) {
        html += callback(d)
    }
    $(selector).html(html);
}

function createSlider(sliderSelector, prevSelector, nextSelector) {
    let slider = tns({
        container: sliderSelector,
        items: 5,
        slideBy: 5,
        prevButton: prevSelector,
        nextButton: nextSelector,
        nav: false
    });
}

function callAjax(url, sliderSelector, prevSelector, nextSelector, callback) {
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function(data) {
            renderHTML(data, callback, sliderSelector);
            createSlider(sliderSelector, prevSelector, nextSelector)
        },
    })
}

window.onload = function() {
    callAjax('http://localhost:8080/songs/listTrending', '.trending-song', '.trending-s-left', '.trending-s-right', renderSong);
    callAjax('http://localhost:8080/songs/newSongs', '.newest-song', '.newest-s-left', '.newest-s-right', renderSong);
    callAjax('http://localhost:8080/playlist/listTrending', '.top-playlist', '.top-p-left', '.top-p-right', renderPlaylist);
    callAjax('http://localhost:8080/playlist/newPlaylist', '.new-playlist', '.new-p-left', '.new-p-right', renderPlaylist)
}