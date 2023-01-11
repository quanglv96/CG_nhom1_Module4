function redirectSong(id){
    localStorage.setItem("idSong",id);
    window.location = "/caseStudy4/views/page/song_detail_page.html";
}
function redirectPlayList(id){
    localStorage.setItem("idSong",id);
    window.location = "/caseStudy4/views/page/playlist_detail_page.html";
}