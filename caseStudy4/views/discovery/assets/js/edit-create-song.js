function createSongs(){
    let user = JSON.parse(localStorage.getItem("userLogin"));
    let formData=new FormData($('#form-Song')[0]);
    $.ajax({
        type: "POST",
        data: formData,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        //tên API
        url: "http://localhost:8080/songs?idUser="+user.id,
        success: function () {
            alert(" Cập nhập thành công");
            search_song_playlist_byUser(user);
        },
        error: function () {
            alert("lỗi");
        }
    });
}
function saveSong(id) {
    let formData = new FormData($('#form-Song')[0])
    $.ajax({
        type: "POST",
        data: formData,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        //tên API
        url: "http://localhost:8080/songs/"+id,
        success: function () {
            alert("tạo thành công");
        },
        error: function () {
            alert("tên play list đã trùng");
        }
    });
}