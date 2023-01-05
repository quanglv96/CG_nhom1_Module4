$(function() {
    $(".user-toggle").click(function() {
        $(".user-menu").show();
    })
})

window.onclick = function(event) {
    if (!event.target.closest(".user-menu,.user-toggle")) {
        $(".user-menu").hide();
    }
}