document.addEventListener('DOMContentLoaded', () => {
    initEvents();
})


function initEvents() {
    const submitBtn = selector('.btn.btn_mint.btn_big');
    submitBtn.addEventListener('click', signup);
}

function signup(evt) {
    evt.preventDefault();

    fetchManager({
        url: '/api/users',
        method: 'POST',
        headers: {"content-type": "application/json"},
        body: JSON.stringify({
            email: getEmail(),
            password: getPassword(),
            confirmPassword: getConfirmPassword(),
            name: getName(),
            phoneNumber: getPhoneNumber(),
        }),
        onSuccess: () => {
            location.href = '/login';
        },
        onFailed: ({json}) => {
            let errors = json.errors;
            errors.forEach(e => alert(e.message));
        },
        onError: (err) => {
            console.log(err);
            alert("문제가 발생하였습니다.");
        }
    });
}

function getEmail() {
    const emailId = selector('#email_id').value;
    const emailDomain = selector('#email_domain').value;
    return emailId + "@" + emailDomain;
}

function getPassword() {
    return selector('#pw1').value;
}

function getConfirmPassword() {
    return selector('#pw2').value;
}

function getName() {
    return selector('#name').value;
}

function getPhoneNumber() {
    const cell1 = selector('#cell1').value;
    const cell2 = selector('#cell2').value;
    const cell3 = selector('#cell2').value;
    return [cell1, cell2, cell3].join('-');
}