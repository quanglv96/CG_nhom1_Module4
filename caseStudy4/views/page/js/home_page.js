let slideIndex = 0;
slideShow();

function slideShow() {
    let slides = document.getElementsByClassName("banner-item");
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) {slideIndex = 1}
    slides[slideIndex-1].style.display = "block";
    setTimeout(slideShow, 2000); // Change image every 2 seconds
}