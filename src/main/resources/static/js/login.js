document.addEventListener("DOMContentLoaded", () => {
    initLoginEvents();
})

function initLoginEvents() {
    const loginBtn = $(".btn_login");
    loginBtn.addEventListener("click", loginHandler);
}

function loginHandler(evt) {
    evt.preventDefault();
    const memberId = $('#member_id').value;
    const password = $('#pwd').value;

    if (!memberId || !password) {
        alert('아이디 또는 패스워드를 입력하세요');
        return;
    }

    fetchManager({
        url: '/api/members/login',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            email: memberId,
            password: password
        }),
        onSuccess: successLoginCallback,
        onFailure: failLoginCallback
    })
}

function successLoginCallback(response) {
    document.location.href = '/';
}

function failLoginCallback(response) {
    $('.err_msg').innerHTML = '로그인에 실패했습니다. 아이디와 비밀번호를 다시 확인하세요.';
    $('#member_id').value = '';
    $('#pwd').value = '';
}