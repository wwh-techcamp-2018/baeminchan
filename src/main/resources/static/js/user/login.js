document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const loginBtn = $('#btn_login');
    if(loginBtn === null) return;
    loginBtn.addEventListener("click", loginUserHandler);
}

function onSuccess(response) {
    alert("로그인 성공!");
    location.href = "/";
}

function alertError(response) {
    alert(response.message);
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
        }),
        callback: onSuccess,
        errCallback: alertError
    })
}