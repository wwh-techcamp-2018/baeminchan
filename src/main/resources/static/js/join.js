function $(selector) {
    return document.querySelector(selector);
}

$("#joinFrm").addEventListener('submit', joinHandler);


function joinHandler(evt) {
    evt.preventDefault();

    const email = `${$("#email_id").value}@${$("#email_domain").value}`;
    const name = $("#name").value;
    const password = $("#pw1").value;
    const confirmPassword = $("#pw2").value;
    const phone = `${$("#cell1").value}-${$("#cell2").value}-${$("#cell3").value}`;


    fetch('/users', {
        method: 'post',
        headers: {'content-type': 'application/json'},
        body: JSON.stringify({
            email,
            name,
            password,
            confirmPassword,
            phone
        })
    })
    .then(response => {
        if (!response.ok) {
            location.reload();
        }
        alert("회원가입을 축하합니다!");
        location.href = "/";
    })
    .catch(error => {
        location.reload();
    });
}
