function createPlaylist(){
let formData=new FormData($('#form-playlist')[0]);
    let user = JSON.parse(localStorage.getItem("userLogin"));
    $.ajax({
        type: "POST",
        data: formData,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        //tên API
        url: "http://localhost:8080/playlist?idUser="+user.id,
        success: function () {
            alert("Successful");
            closeModal()
            search_song_playlist_byUser(user);
        },
        error: function () {
            alert("lỗi");
        }
    });
}
function savePlaylist(id) {
    let formData = new FormData($('#form-playlist')[0])
    $.ajax({
        type: "POST",
        data: formData,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        //tên API
        url: "http://localhost:8080/playlist/"+id,
        success: function () {
            alert("Successful");
            let user = JSON.parse(localStorage.getItem("userLogin"));
            search_song_playlist_byUser(user);
            closeModal()
        },
        error: function () {
            alert("lỗi");
        }
    });
}