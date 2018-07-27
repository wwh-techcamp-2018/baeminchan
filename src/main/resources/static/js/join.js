function successJoin(response) {
    document.location.href = '/';
}

function failJoin(response) {
    const errors = response.errors;
    $All('.caution').forEach((el) => {
        el.style.display = 'none';
    });
    errors.forEach((error) => {
        let errorMessage = error.errorMessage;
        let errorEl = $('#' + error.fieldName + '_caution');
        errorEl.innerHTML = errorMessage;
        errorEl.style.display = 'block';
    });
}

function checkPassword() {
    const password = $('#pw1').value;
    const password2 = $('#pw2').value;
    const joinCheck = $('#join_check_password2');

    if (password !== password2) {
        joinCheck.innerHTML = '비밀번호가 일치하지 않습니다.';
        joinCheck.style.color = 'red';
    }
    else {
        joinCheck.innerHTML = '비밀번호가 일치합니다.';
        joinCheck.style.color = 'green';
    }
}

function registerMemberHandler(evt) {
    evt.preventDefault();
    const emailId = $('#email_id').value;
    const emailDomain = $('#email_domain').value;
    const password = $('#pw1').value;
    const username = $('#name').value;
    const phoneNumber = $('#cell1').value +  $('#cell2').value +  $('#cell3').value;

    fetchManager({
        url: '/api//members',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            email: emailId + '@' + emailDomain,
            password: password,
            username: username,
            phoneNumber: phoneNumber
        }),
        callback: successJoin,
        failCallback: failJoin
    })
}


function initEvents() {
    const submitBtn = $(".submit-btn");
    submitBtn.addEventListener("click", registerMemberHandler);
    const passwordCheckField = $('#pw2');
    passwordCheckField.addEventListener("keyup", checkPassword);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})