
// TODO
// 1. 값들 가져오기
// 2. 이메일, 핸드폰번호 포맷 맞추기
// 3. 전송하기
// 4. response 처리하기
function $(selector) {
    return document.querySelector(selector);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
//    let regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
//    let password = $("#password");
//
//    password.addEventListener("change", function () {
//        if (!regPassword.test(password))
//    });
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((response) => {
        if (response.status === 201) {
            alert("어서와~ 배민찬은 처음이지?");
            location.href = "/";
            return;
        }
        return response.json();
    }).then((result) => {
        callback(result)
    }).catch( (e) => {
        console.log(e);
    })
}

function join (form) {
    if(!checkValid(form)) return;

    form.userId.value = form.email_id.value + "@" + form.email_domain.value;
    form.phoneNumber.value = form.cell1.value + "-" + form.cell2.value + "-" + form.cell3.value;

    console.log(form.userId.value, form.password.value, form.rePassword.value, form.name.value, form.phoneNumber.value);
    fetchManager({
        url: '/api/users',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            "userId": form.userId.value,
            "password": form.password.value,
            "rePassword": form.rePassword.value,
            "name": form.name.value,
            "phoneNumber": form.phoneNumber.value
        }),
        callback: onJoinError
    })
}

function checkValid (form) {
    if (form.email_id.value.trim() === "" || form.email_domain.value.trim() === "") {
        alert("이메일을 확인해주세요.");
        return false;
    }

    if (form.password.value.trim() === "" || form.rePassword.value.trim() === "") {
        alert("비밀번호를 확인해주세요.");
        return false;
    }

    if (form.name.value.trim() === "") {
        alert("이름을 확인해주세요.");
        return false;
    }

    if (form.cell1.value.trim() === "" || form.cell2.value.trim() === "" || form.cell3.value.trim() === "") {
        alert("휴대 전화 번호를 확인해주세요.");
        return false;
    }

    return true;
}

function onJoinError (result) {
    alert(result.message);
}