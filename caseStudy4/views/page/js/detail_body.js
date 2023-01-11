let user = localStorage.getItem('userLogin');

function renderHTML(data, selector, callback) {
    let html = "";
    for (const d of data) {
        html += callback(d);
    }
    $(selector).html(html);
}

function drawRandomPlaylist(data) {
    return  `<div onclick="viewPlaylistDetails(${data.id})" class="random-item d-flex mb-1 pointer">
                <div class="random-img">
                    <img class="fit-img" src="../upload_img/${data.avatar}" alt="">
                </div>
                <div class="random-song-desc ms-2">
                    <div class="grey-text line-16 font-12 fit-head">${data.users.name}</div>
                    <div class="line-16 font-12">${data.name}</div>
                    <div class="react-box">
                        <span class="font-12 grey-text pe-2">
                            <i class="fa-solid fa-heart"></i> ${data.likes}
                        </span>
                        <span class="font-12 grey-text pe-2">
                            <i class="fa-solid fa-play"></i> ${data.views}
                        </span>
                    </div>
                </div>
            </div>`
}

function drawSongInPlaylist(song, index) {
    return  `<div class="song-list py-2 border-bottom d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <div class="p-song-img">
                        <img class="fit-img" src="../upload_img/${song.avatar}" alt="">
                    </div>
                    <div class="p-count grey-text">${index + 1}</div>
                    <div class="grey-text"><span>${song.users.name}</span> - <span class="text-dark">${song.name}</span></div>
                </div>
                <div>
                    <span class="font-12 grey-text pe-2">
                        <i class="fa-solid fa-play"></i> ${song.views}
                    </span>
                </div>
            </div>`
}

function createWaves(selector, url, playBtnSelector) {
    let wavesurfer = [];
    wavesurfer[selector] = WaveSurfer.create({
        container: selector,
        waveColor: '#333333',
        progressColor: 'rgb(210, 112, 0)',
        barWidth: 1,
        height: 110,
        hideScrollbar: true,
        cursorColor: "white"
    });
    wavesurfer[selector].load(url);

    let playBtn = $(playBtnSelector)[0];
    let isPlay = true;
    playBtn.onclick = function() {
        if (isPlay) {
            $(playBtnSelector).html('<i class="fa-solid fa-pause"></i>')
            isPlay = false;
        } else {
            $(playBtnSelector).html('<i class="fa-solid fa-play"></i>')
            isPlay = true;
        }
        wavesurfer[selector].playPause();
    }
    return wavesurfer[selector];
}

function callAjax(id) {
    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/playlist/getView/' + id,
        dataType: "json",
        success: function(data) {
            fillPlaylistData(data);
        },
    })
}

function fillPlaylistData(data) {
    $('.user-upload').html(data[0].users.name);
    $('.item-name').html(data[0].name);
    $('.date-upload').html(data[0].lastUpdate);
    let tags = data[0].tagsList;
    let tagContain = '';
    // for (const tag of tags) {
    //     tagContain += `#${tag.name}`
    // }
    $('.item-tag').html('')
    $('.item-tag').html(tagContain);
    $('.song-num').html(data[0].songList.length)
    $('.playlist-img').attr('src',`../upload_img/${data[0].avatar}`)
}