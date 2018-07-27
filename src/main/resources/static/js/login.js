document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const loginBtn = $('.login_form');

    loginBtn.addEventListener("click", loginHandler);
}

function loginHandler(evt) {
    evt.preventDefault();
    const email = $('#member_id').value;
    const password = $('#pwd').value;

    fetchManager({
        url: "/api/members/login",
        method: 'POST',
        headers: {'content-type': 'application/json'},
        body: JSON.stringify({email, password}),
        onSuccess: onSuccess,
        onFailure: onFailure
    })
}

function onSuccess(response) {
    window.location.href = "/";
}

function onFailure(response) {
    response.json().then((result) => { alert(result.message) });
}