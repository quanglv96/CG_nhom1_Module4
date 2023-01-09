function preventSubmit(selector) {
    let form = document.querySelector(selector)
    form.onsubmit = function(e) {
        e.preventDefault();
    }
}

preventSubmit(".user-info-form");

function showForm(selector) {
    $(".tab-item").css("background-color", "#cccccc");
    $(selector + "-tab").css("background-color", "white");
    $(".info-form").removeClass("show").addClass("hide")
    $(selector + "-form").removeClass("hide").addClass("show");
    $(".form-heading").removeClass("show").addClass("hide")
    $(selector + "-heading").removeClass("hide").addClass("show");
}

validator({
    form: "#change-pass-form",
    errorSelector: ".form-message",
    rules: [
        validator.isRequired("#rpl-password"),
        validator.lengthField("#rpl-password", 6, 8),
        validator.isRequired("#new-password"),
        validator.lengthField("#new-password", 6, 8),
        validator.isDifferent("#new-password", function () {
            return document.querySelector("#change-pass-form #rpl-password").value;
        }),
        validator.isRequired("#confirm-new-password"),
        validator.isConfirmed("#confirm-new-password", function () {
            return document.querySelector("#change-pass-form #new-password").value;
        }),
    ]
})

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