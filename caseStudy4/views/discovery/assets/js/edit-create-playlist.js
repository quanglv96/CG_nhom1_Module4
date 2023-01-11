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
            alert("tạo thành công");
            search_song_playlist_byUser(user);
        },
        error: function () {
            alert("tên play list đã trùng");
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
            alert("tạo thành công");
            let user = JSON.parse(localStorage.getItem("userLogin"));
            search_song_playlist_byUser(user);
        },
        error: function () {
            alert("tên play list đã trùng");
        }
    });
}