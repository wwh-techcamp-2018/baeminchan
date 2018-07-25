function $(selector) {
    return document.querySelector(selector);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const joinBtn = $('#btnSubmit');
    if(joinBtn === null) return;
    joinBtn.addEventListener("click", registerUserHandler);
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
            if(response.status == 201) {
                alert("회원가입을 축하드립니다!");
                location.href = '/'
                return
            }
        return callback(response.json());
    })
}

function registerUserHandler(evt) {
    evt.preventDefault();
    const joinTable = $(".tb_join");

    let userId = joinTable.querySelector('#email_id').value;
    let emailDomain = joinTable.querySelector('#email_domain').value;
    let password = joinTable.querySelector('#pw1').value;
    let password2 = joinTable.querySelector('#pw2').value;
    let name = joinTable.querySelector('#name').value;
    let phoneNumber = joinTable.querySelector('#cell1').value +'-'+ joinTable.querySelector('#cell2').value + '-'+ joinTable.querySelector('#cell3').value;

    fetchManager({
        url: '/api/users',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            "userId" : userId,
            "emailDomain" : emailDomain,
            "password" : password,
            "password2" : password2,
            "name" : name,
            "phoneNumber" : phoneNumber
            }),
        callback: alertError
    })
}

function alertError(result) {
    result.then((response) => {
    let errorMessage = "";
        for (let error of response.errors) {
          errorMessage += error.errorMessage + "\n";
        }
    alert(errorMessage);
    })
}