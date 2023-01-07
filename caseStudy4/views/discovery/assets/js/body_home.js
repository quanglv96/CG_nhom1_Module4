
$(function() {
    $('.trending-song').slick({
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 5,
        // centerMode: true,
        prevArrow: ".trending-s-left",
        nextArrow: ".trending-s-right"
    });
})

$(function() {
    $('.newest-song').slick({
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 5,
        // centerMode: true,
        prevArrow: ".newest-s-left",
        nextArrow: ".newest-s-right"
    });
})

$(function() {
    $('.top-playlist').slick({
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 5,
        // centerMode: true,
        prevArrow: ".top-p-left",
        nextArrow: ".top-p-right"
    });
})

$(function() {
    $('.new-playlist').slick({
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 5,
        // centerMode: true,
        prevArrow: ".new-p-left",
        nextArrow: ".new-p-right"
    });
})