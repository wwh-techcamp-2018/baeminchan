function $(selector) {
    return document.querySelector(selector);
}

$("#joinFrm").addEventListener('submit', joinHandler);


function joinHandler(evt) {
    evt.preventDefault();

    const email = `${$("#email_id").value}@${$("#email_domain").value}`;
    const name = $("#name").value;
    const password = $("#pw1").value;
    const password_check = $("#pw2").value;
    const phone = `${$("#cell1").value}-${$("#cell2").value}-${$("#cell3").value}`;


    fetch('/users', {
        method: 'post',
        headers: {'content-type': 'application/json'},
        credentials:"same-origin",
        body: JSON.stringify({
            email,
            name,
            password,
            "password-check": password_check,
            phone
        })
    })
    .then(response => {
        if (!response.ok) {
            return location.reload();
        }
        alert("회원가입을 축하합니다!");
        location.href = "/";
    })
    .catch(error => {
        location.reload();
    });
}