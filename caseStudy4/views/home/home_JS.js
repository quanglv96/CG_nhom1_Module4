window.onload = function () {
    displaySongTrend();
}

function displaySongTrend() {
    $.ajax({
        type:"GET",
        url: "http://localhost:8080/songs/listTrending",
        success: function (data) {
            let content = '<div class="wrapper">'
            for (let i = 0; i < data.length; i++) {
                if(i===10){
                    break;
                }
                content += getListSong(data[i]);

            }
            content += "</div>";
            document.getElementById('listTrending').innerHTML = content;
        }
    })
}
function getListSong(songsList){
return `<div class="item">
        <a href="${songsList.id}"><img width="150px" height="150px" src="../../src/main/resources/upload_img/${songsList.avatar}"></a><br>
        <p class="nameSongList name-song" ><a href="${songsList.id}">${songsList.name}</a><br></p>
        <p class="nameSongList name--user" ><a href="${songsList.id}">${songsList.users.name}</a></p>
    </div>`
}
function signIn(){
    ///
}
function createAccount(){

}