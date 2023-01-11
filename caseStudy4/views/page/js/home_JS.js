function displaySongTrend() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/songs/listTrending",
        success: function (data) {
            let content = ""
            for (let i = 0; i < data.length; i++) {
                if (i === 10) {
                    break;
                }
                content += getListSong(data[i]);
            }
            document.getElementById('listTrending').innerHTML = content;
        }
    })
}

function getListSong(songsList) {
    return `<div class="item" style="padding: 17px;">
        <img onclick="redirectSong(${songsList.id})" class="item-img" src="/views/upload_img/${songsList.avatar}"><br>
        <p class="m-0 item-title" ><a onclick="redirectSong(${songsList.id})" >${songsList.name}</a><br></p>
        <p class="m-0 item-description font-12 grey-text" onclick="redirectResultSearchUser(${songsList.users.id})" ><a>${songsList.users.name}</a></p>
    </div>`
}


function redirectResultSearch() {
    let textSearchHome = document.getElementById("input_search").value;
    localStorage.setItem("textSearch", textSearchHome);
    localStorage.removeItem("searchIdUser");
    window.location = "../discovery/layout/layout.html";
}

function redirectResultSearchUser(idUser) {
    localStorage.removeItem("textSearch");
    localStorage.setItem("searchIdUser", idUser);
    window.location = "../discovery/layout/layout.html";
}

let slideIndex = 0;
slideShow();

function slideShow() {
    let slides = document.getElementsByClassName("banner-item");
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1
    }
    slides[slideIndex - 1].style.display = "block";
    setTimeout(slideShow, 3000); // Change image every 2 seconds
}



