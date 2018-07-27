document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const joinBtn = $('.join_form');

    joinBtn.addEventListener("click", joinHandler);
}

function joinHandler(evt) {
    evt.preventDefault();
    const email = $('#email_id').value + '@' + $('#email_domain').value;
    const password = $('#pw1').value;
    const password2 = $('#pw2').value;
    const username = $('#name').value;
    const phoneNumber = $('#cell1').value + $('#cell2').value + $('#cell3').value;

    if (passwordChecking(password, password2)) {
        fetchManager({
            url: "/api/members",
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify({email, password, username, phoneNumber}),
            onSuccess: onSuccess,
            onFailure: onFailure
        })
    }
}

function passwordChecking(password, password2) {
    if (password !== password2) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
    return true;
}

function onSuccess(response) {
    window.location.href = "/";
}

function onFailure(response) {
    response.json().then((result) => { alert(result.message) });
}