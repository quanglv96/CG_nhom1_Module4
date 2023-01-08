function clickLike_Song(id){
    reloadLike(id,1)
    $('#click-like').show();
    $('#none-like').hide();
    document.getElementById("btnLike").setAttribute("onclick",`disLike_Song(${id})`);

}
function disLike_Song(id){
    reloadLike(id,-1);
    $('#click-like').hide();
    $('#none-like').show();
    document.getElementById("btnLike").setAttribute("onclick",`clickLike_Song(${id})`);

}
function reloadLike(idSong,num) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        //tên API
        url: `http://localhost:8080/songs/setLike/${idSong}?config=${num}`,
        success: function (data) {
            document.getElementById("like-song").innerText = data.likes;
        }
    });
}