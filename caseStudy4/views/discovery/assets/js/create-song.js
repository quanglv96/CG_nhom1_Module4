function createSongs(){
let formData=new FormData($('#song-form')[0]);
    let tag =document.getElementById("tags").value;
// let listTags=tag.split("#")
    // formData.set("listTags",JSON.stringify(listTags));
    let singer =document.getElementById("singer").value;
    // let listSinger=singer.split(", ")
    // formData.set("listSinger",JSON.stringify(listSinger));
    formData.append("userLogin",JSON.parse(localStorage.getItem("userLogin")).id);
    $.ajax({
        type: "POST",
        data: formData,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        //tên API
        url: "http://localhost:8080/songs",
        success: function () {
            alert("tạo thành công");
        },
        error: function () {
            alert("tên play list đã trùng");
        }
    });
}