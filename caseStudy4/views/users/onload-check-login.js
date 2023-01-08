function onloadCheckLogin(){
    let trueLogin = document.getElementById("trueLogin");
    let falseLogin = document.getElementById("falseLogin");
    if(localStorage.getItem("userLogin")===null){
        if(trueLogin!==null){
            trueLogin.setAttribute("style",'display:none !important')
        }
        if(falseLogin!==null){
            falseLogin.setAttribute("style",'display:block !important')
        }
    }else {
        if(trueLogin!==null){
            trueLogin.setAttribute("style",'display:block')
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