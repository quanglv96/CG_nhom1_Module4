
function onloadCheckLogin(){
    let trueLogin = document.getElementById("trueLogin");
   let  trueLogin_2=document.getElementById("trueLogin-2");
    let falseLogin = document.getElementById("falseLogin");
    let user=JSON.parse(localStorage.getItem("userLogin"));
    if(user===null){
        if(trueLogin!==null){
            trueLogin.setAttribute("style",'display:none !important')
            if(trueLogin_2!==null){
                trueLogin_2.setAttribute("style",'display:none ')
            }
        }
        if(falseLogin!==null){
            falseLogin.setAttribute("style",'display:block !important')
        }
    }else {

        if(trueLogin!==null){
            trueLogin.setAttribute("style",'display:flex !important')
            if(trueLogin_2!==null){
                trueLogin_2.setAttribute("style",'display:block')
            }
            if(document.getElementById("trueLogin-username")!=null){
                document.getElementById("trueLogin-username").innerText=user.name
                document.getElementById("trueLogin-avatar").setAttribute("src",`/views/upload_img/${user.avatar}`);
            }
        }
        if(falseLogin!==null){
            falseLogin.setAttribute("style",'display:none !important')
        }
    }

}
function logOut(){
    localStorage.removeItem("userLogin")
    window.location = "../page/home_page.html";
}