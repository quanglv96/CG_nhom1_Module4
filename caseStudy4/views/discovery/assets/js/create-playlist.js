function createPlaylist(){
let formData=new FormData($('#form-create-playlist')[0]);
// let tag =document.getElementById("tags").value;
// let listTags=tag.split("#")
    // formData.set("listTags",JSON.stringify(listTags));
    formData.append("userLogin",JSON.parse(localStorage.getItem("userLogin")).id);
    $.ajax({
        type: "POST",
        data: formData,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        //tên API
        url: "http://localhost:8080/playlist",
        success: function () {
            alert("tạo thành công");
        },
        error: function () {
            alert("tên play list đã trùng");
        }
    });
}