function $(selector) {
    return document.querySelector(selector);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
    loginInitEvents();
})

function initEvents() {
    const joinBtn = $(".join_form_box .btn_area .btn");
    if(joinBtn === null) return;
    joinBtn.addEventListener("click", registerUserHandler);
}

function loginInitEvents(){
    const loginBtn = $("#login_btn");
    console.log("222222"+loginBtn);
    if(loginBtn === null) return;
    loginBtn.addEventListener("click", loginHandler);
}



function registerFetchManager({ url, method, body, headers}) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        if(response.status === 201){
            window.location.replace("http://localhost:8080/")
            return
        }
        return response.json()
    }).then((result) => {
        alert(result.data.validationErrorList[0].errorMessage);
    })
}

function loginFetchManager({ url, method, body, headers}) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        if(response.status === 200){
            console.log(response);
            window.location.replace("http://localhost:8080/");
            return
        }
        return response.json()
    }).then((result) => {
        console.log(result);
        alert(result.message);
    })
}

function registerUserHandler(evt) {
    evt.preventDefault();
    const email = $("#email_id").value+"@"+$("#email_domain").value;
    const password = $("#pw1").value;
    const passwordConfirm = $("#pw2").value;
    const name = $("#name").value;
    const phoneNo = $("#cell1").value+$("#cell2").value+$("#cell3").value;
    
    $("#pw1").value = "";
    $("#pw2").value = "";
    

    registerFetchManager({
        url: '/api/users',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({email,password,passwordConfirm,name,phoneNo})
    })
}

function loginHandler(evt) {
    evt.preventDefault();
    const email = $("#member_id").value;
    const password = $("#pwd").value;
    
    $("#pwd").value = "";

    

    loginFetchManager({
        url: '/api/users/login',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({email,password})
    })
}

function goNext(evt){

}