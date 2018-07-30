
function $(selector) {
    return document.querySelector(selector);
}

$("#loginFrm").addEventListener('submit', loginHandler);


function loginHandler(evt) {
    evt.preventDefault();

    const email = $("#member_id").value;
    const password = $("#pwd").value;

    fetch('/users/login', {
        method: 'post',
        headers: {'content-type': 'application/json'},
        credentials: 'same-origin',
        body: JSON.stringify({
            email,
            password
        })
    })
    .then(response => {
        if (!response.ok) {
            return location.href = "/login.html";
        }
        alert("로그인을 축하합니다!");
        location.href = "/";
    })
    .catch(error => {
        location.reload();
    });
}
