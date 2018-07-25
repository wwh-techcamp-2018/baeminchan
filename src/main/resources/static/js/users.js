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

function fetchManager({ url, method, body, headers, errorFunction }) {
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
        errorFunction(result)
    })
}

function onClickJoinButton (form) {
    if(!checkValid(form)) return;

    let userId = form.email_id.value + "@" + form.email_domain.value;
    let phoneNumber = form.cell1.value + "-" + form.cell2.value + "-" + form.cell3.value;

    fetchManager({
        url: '/api/users',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            "userId": userId,
            "password": form.password.value,
            "rePassword": form.rePassword.value,
            "name": form.name.value,
            "phoneNumber": phoneNumber
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