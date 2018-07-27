
function initEvents(){
    const loginBtn = $("#submitLogin");
    loginBtn.addEventListener("click", handleLogin);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function handleLogin(evt){
    evt.preventDefault();

    const email = $("#member_id").value;
    const password = $("#pwd").value;

    fetchManager({
        url: "/users/login",
        method: "post",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        body: JSON.stringify({
            "email": email,
            "password": password
        }),
        success: loginSuccess,
        error: loginFailed
    })
}

function loginSuccess(response){
    console.log(response);
    if(response.status == 200) {
        location.href = '/'
    }
    // TODO error 처리
}

function loginFailed(error){
    alert(error);
}