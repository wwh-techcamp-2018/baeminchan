function checkValidForJoin(form) {
    if (form.email_id.value.trim() === "" || form.email_domain.value.trim() === "") {
        alert("이메일을 확인해주세요");
        return false;
    }

    if (form.password.value.trim() === "" || form.rePassword.value.trim() === "") {
        alert("비밀번호를 확인해주세요");
        return false;
    }

    if (form.name.value.trim() === "") {
        alert("이름을 확인해주세요");
        return false;
    }

    if (form.cell1.value.trim() === "" || form.cell2.value.trim() === "" || form.cell3.value.trim() === "") {
        alert("휴대 전화 번호를 확인해주세요");
        return false;
    }
    return true;
}

function onClickJoinButton(form) {
    if(!checkValidForJoin(form)) return;

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
        callback: onSuccessJoin,
        errorCallback: onError
    })
}

function goHome() {
    location.href = "/";
}

function onSuccessJoin() {
    alert("어서와~ 배민찬은 처음이지?");
    goHome();
}

function onError(result) {
    let message = result.message;
    if (result.hasOwnProperty("errors")) {
        message = result.errors[0].errorMessage;
    }
    alert(message);
}

function checkValidForLogin(form) {
    if (form.userId.value.trim() === "") {
        alert("아이디를 입력하세요");
        form.userId.focus();
        return false;
    }

    if (form.password.value.trim() === "") {
        alert("비밀번호를 입력하세요");
        form.password.focus();
        return false;
    }
    return true;
}

function onClickLoginButton(form) {
    if(!checkValidForLogin(form)) return;

    fetchManager({
        url: '/api/users/login',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            "userId": form.userId.value,
            "password": form.password.value
        }),
        callback: goHome,
        errorCallback: onError
    })
}