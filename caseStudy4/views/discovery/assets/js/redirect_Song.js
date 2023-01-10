function redirectSong(id){
    localStorage.setItem("idSong",id);
    window.location = "../page/song_detail_page.html";
}
function redirectPlayList(id){
    localStorage.setItem("idSong",id);
    window.location = "../page/playlist_detail_page.html";
}