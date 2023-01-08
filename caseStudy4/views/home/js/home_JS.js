window.onload = function () {
    displaySongTrend();
}

function displaySongTrend() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/songs/listTrending",
        success: function (data) {
            let content =""
            for (let i = 0; i < data.length; i++) {
                if (i === 10) {
                    break;
                }
                content += getListSong(data[i]);
            }
            document.getElementById('listTrending').innerHTML = content;
        }
    })
}

function getListSong(songsList) {
    return `<div class="item">
        <img onclick="redirectSong(${songsList.id})" width="150px" height="150px" src="/views/upload_img/${songsList.avatar}"><br>
        <p class="nameSongList name-song" ><a onclick="redirectSong(${songsList.id})" >${songsList.name}</a><br></p>
        <p class="nameSongList name--user" onclick="redirectResultSearchUser(${songsList.users.id})" ><a>${songsList.users.name}</a></p>
    </div>`
}

function fieldTextSearch(){
    let textSearchHome = document.getElementById("input_search").value;
    document.getElementById("btnSearch").setAttribute("value",textSearchHome)
}
function redirectResultSearch(textSearchHome) {
    localStorage.setItem("textSearch", textSearchHome);
    localStorage.removeItem("searchIdUser");
    window.location = "../../discovery/layout/layout.html";
}
function redirectResultSearchUser(idUser) {
    localStorage.removeItem("textSearch");
    localStorage.setItem("searchIdUser", idUser);
    window.location = "../../discovery/layout/layout.html";
}




