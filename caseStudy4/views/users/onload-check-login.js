
function onloadCheckLogin(){
    let trueLogin = document.getElementById("trueLogin");
    let falseLogin = document.getElementById("falseLogin");
    let user=JSON.parse(localStorage.getItem("userLogin"));
    if(user===null){
        if(trueLogin!==null){
            trueLogin.setAttribute("style",'display:none !important')
        }
        if(falseLogin!==null){
            falseLogin.setAttribute("style",'display:block !important')
        }
    }else {
        if(trueLogin!==null){
            trueLogin.setAttribute("style",'display:block')
            if(document.getElementById("trueLogin-username")!=null){
                document.getElementById("trueLogin-username").innerText=user.name
                document.getElementById("trueLogin-avatar").setAttribute("src",`../../upload_img/${user.avatar}`);
            }
        }
        if(falseLogin!==null){
            falseLogin.setAttribute("style",'display:none !important')
        }
    }

}
function logOut(){
    localStorage.removeItem("userLogin")
    window.location = "../../home/layout/home.html";
}