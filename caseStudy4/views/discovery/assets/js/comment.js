function sendComment_song(){
    let inputComment_song =document.getElementById("input-comment").value;
    let user=JSON.parse(localStorage.getItem("userLogin"));
    let idSong=localStorage.getItem("idSong");
    let comment={
        "content": inputComment_song,
        "users":user,
        "songs": {
            "id": idSong
        }
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(comment),
        //tên API
        url: "http://localhost:8080/comment",
        success: function () {
            location.reload();
        }
    });

}