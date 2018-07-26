function $(selector) {
    return document.querySelector(selector);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const loginBtn = $('#btn_login');
    if(loginBtn === null) return;
    loginBtn.addEventListener("click", loginUserHandler);
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
            if(response.status == 302) {
                alert("로그인 성공!");
                location.href = '/'
                return
            }

            if(response.status == 403) {
                alert("아이디나 비밀번호가 일치하지 않습니다!");
                return
            }
    })
}

function loginUserHandler(evt) {
    evt.preventDefault();
    const userId = $("input#member_id").value;
    const password = $("input#pwd").value;

    fetchManager({
        url: '/api/users/login',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            "userId" : userId,
            "password" : password
            })
    })
}