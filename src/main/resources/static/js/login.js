function $(selector) {
    return document.querySelector(selector);
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((response) => {
        return response
    }).then((result) => {
        callback(result)
    })
}

function initEvents(){
    const loginBtn = $("#submitLogin");
    loginBtn.addEventListener("click",loginHandler);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function loginHandler(evt){
    evt.preventDefault();

    const email = $("#member_id").value;
    const password = $("#pwd").value;

    fetchManager({
        url: "/users/login",
        method: "post",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        body: JSON.stringify({
            "email": email,
            "password": password,
        }),
        callback: loginResult
    })
}

function loginResult(response){
    console.log(response);
    if(response.status == 200) {
        location.href = '/'
    }
    // TODO error 처리
}