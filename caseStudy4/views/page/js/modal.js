// Open - close modal
window.onclick = function(event) {
    if (!event.target.closest(".login-modal-dialogue,.login-btn")) {
        $(".login-modal").hide();
    }
    if (!event.target.closest(".song-modal-dialogue,.song-btn")) {
        $(".song-modal").hide();
    }
    if (!event.target.closest(".playlist-modal-dialogue,.playlist-btn")) {
        $(".playlist-modal").hide();
    }
    if (!event.target.closest(".user-menu,.user-toggle")) {
        $(".user-menu").hide();
    }
}

let songForm = document.querySelector(".song-form")
songForm.onsubmit = function(e) {
    e.preventDefault();
}

let playlistForm = document.querySelector(".playlist-form")
playlistForm.onsubmit = function(e) {
    e.preventDefault();
}

function openModal(selector) {
    $(selector).show();
}

function closeModal() {
    $(".modal").hide();
}

function openUpload(selector) {
    $(selector).click();
}

function replaceImg(myImg, selector) {
    let file = myImg.files[0]
    let path = (window.URL || window.webkitURL).createObjectURL(file);
    $(selector).attr("src", path);
}

function getFileName(myFile) {
    let file = myFile.files[0];
    let fileName = file.name;
    $(".mp3-name").text(fileName);
}

// Login modal
function switchTab(other, active) {
    if ($(".modal-tab-" + other).hasClass("hide")) {
        $(".modal-tab-" + other).removeClass("hide").addClass("show")
        $(".modal-tab-" + active).removeClass("show").addClass("hide")
        $("." + other).css("background-color", "white")
        $("." + active).css("background-color", "#cccccc")
    }
}

validator({
    form: "#register-form",
    errorSelector: ".form-message",
    rules: [
        validator.isRequired("#c-username"),
        validator.isRequired("#c-password"),
        validator.lengthField("#c-password", 6, 8),
        validator.isRequired("#re-password"),
        validator.isConfirmed("#re-password", function () {
            return document.querySelector("#register-form #c-password").value;
        }),
        validator.isRequired("#phone-number"),
        validator.lengthField("#phone-number", 10, 10)
    ]
})

validator({
    form: "#login-form",
    errorSelector: ".form-message",
    rules: [
        validator.isRequired("#username"),
        validator.isRequired("#password"),
        validator.lengthField("#password", 6, 8)
    ],
})