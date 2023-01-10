function renderSong(song) {
    return  `<div class="item-contain col col-3 p-3">
                <div  class="item-img">
                    <img onclick="redirectSong(${song.id})" style="cursor: pointer;"class="slider-img" src="/views/upload_img/${song.avatar}" alt="">
                    <p class="m-0 item-title text-start" onclick="redirectSong(${song.id})">${song.name}</p>
                    <p class="m-0 item-description font-12 grey-text text-start" onclick="redirectResultSearchUser(${song.users.id})">${song.users.name}</p>
                </div>
            </div>`
}

function renderPlaylist(playlist) {
    return  `<div class="item-contain col col-3 p-3">
                <div class="item">
                    <img onclick="redirectPlayList(${playlist.id})"  class="slider-img" src="/views/upload_img/${playlist.avatar}" alt="">
                    <p class="m-0 item-title" onclick="redirectPlayList(${playlist.id})">${playlist.name}</p>
                    <p class="m-0 item-description font-12 grey-text" onclick="redirectResultSearchUser(${playlist.users.id})">${playlist.users.name}</p>
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
