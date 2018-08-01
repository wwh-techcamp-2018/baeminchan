document.addEventListener('DOMContentLoaded', () => {
    initEvents();
})


function initEvents() {
    const submitBtn = $('.btn.btn_mint.btn_big');
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
    const emailId = $('#email_id').value;
    const emailDomain = $('#email_domain').value;
    return emailId + "@" + emailDomain;
}

function getPassword() {
    return $('#pw1').value;
}

function getConfirmPassword() {
    return $('#pw2').value;
}

function getName() {
    return $('#name').value;
}

function getPhoneNumber() {
    const cell1 = $('#cell1').value;
    const cell2 = $('#cell2').value;
    const cell3 = $('#cell2').value;
    return [cell1, cell2, cell3].join('-');
}