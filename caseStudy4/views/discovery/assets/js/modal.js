// function openUpload() {
//     $(".input-file").click();
// }
//
// function replaceImg(myImg) {
//     file = myImg.files[0]
//     let path = (window.URL || window.webkitURL).createObjectURL(file);
//     $(".modal-img").attr("src", path);
// }
//
// function openUploadMp3(callback) {
//     $(".input-mp3-file").click();
// }
//
// function getFileName(myFile) {
//     let file = myFile.files[0];
//     let fileName = file.name;
//     $(".mp3-name").text(fileName);
// }
//
// function openModal() {
//     $(".modal").show();
// }
//
// function closeModal() {
//     $(".modal").hide();
// }
//
// window.onclick = function(event) {
//     if (!event.target.closest(".modal-dialogue,.modal-open")) {
//         $(".modal").hide();
//     }
// }

// Toggle login modal
function openLoginModal() {
    $(".login-modal").show();
}

window.onclick = function(event) {
    if (!event.target.closest(".login-modal-dialogue,.login-btn")) {
        $(".login-modal").hide();
    }
}

function switchTab(other, active) {
    if ($(".modal-tab-" + other).hasClass("hide")) {
        $(".modal-tab-" + other).removeClass("hide")
        $(".modal-tab-" + other).addClass("show")
        $(".modal-tab-" + active).removeClass("show")
        $(".modal-tab-" + active).addClass("hide")
        $("." + other).css("background-color", "white")
        $("." + active).css("background-color", "#cccccc")
    }
}