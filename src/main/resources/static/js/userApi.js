function $(selector) {
    return document.querySelector(selector);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents(".join_form_box .btn_area .btn",registerUserHandler);
    initEvents("#login_btn",loginHandler);
})

function initEvents(btnSelector,handler) {
    const initBtn = $(btnSelector);
    if(initBtn === null) return;
    initBtn.addEventListener("click", handler);
}

function fetchManager({ url, method, body, headers,callback}) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        callback(response);
    }).catch((e) => {
        alert("예상치 못한 오류가 발생했습니다.");
        window.location.href = "http://localhost:8080/";
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
    
    fetchManager({
        url: '/api/users',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({email,password,passwordConfirm,name,phoneNo}),
        callback: joinCallback
    })
}

function loginHandler(evt) {
    evt.preventDefault();
    const email = $("#member_id").value;
    const password = $("#pwd").value;
    
    $("#pwd").value = "";

    fetchManager({
        url: '/api/users/login',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({email,password}),
        callback: loginCallback
    })
}

function joinCallback(response){
    if(response.status === 201){
        window.location.replace("http://localhost:8080/")
        return
    }
    response.json().then((result) => {
        if(result.message === "valid_error"){
            alert(result.data.validationErrorList[0].errorMessage);
            return;
        }
        alert("이미 존재하는 이메일입니다.");
    })
} 

function loginCallback(response){
    if(response.status === 200){
        window.location.href = "http://localhost:8080/"
        return
    }
    alert("로그인에 실패하였습니다.");
}

function goNext(evt){

}