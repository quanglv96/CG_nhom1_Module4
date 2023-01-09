function redirectSong(id){
    localStorage.setItem("idSong",id);
    window.location = "http://localhost:63342/caseStudy4/views/discovery/layout/details_body.html";
}