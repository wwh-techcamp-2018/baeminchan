
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
        body: JSON.stringify({
            email,
            password,
        })
    })
    .then(response => {
        if (!response.ok) {
            location.reload();
        }
        alert("로그인을 축하합니다!");
        location.href = "/";
    })
    .catch(error => {
        location.reload();
    });
}
