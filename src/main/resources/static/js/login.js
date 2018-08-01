document.addEventListener('DOMContentLoaded', () => {
    initEvents();
});


function initEvents() {
    const submitBtn = $('.btn.btn_mint.btn_login');
    submitBtn.addEventListener('click', login);
}

function login(evt) {
    evt.preventDefault();
    fetchManager({
        url: '/api/users/login',
        method: 'POST',
        headers: {'content-type': 'application/json'},
        body: JSON.stringify({
            email: getEmail(),
            password: getPassword()
        }),
        onSuccess: () => {
            location.href = '/';
        },
        onFailed: ({json}) => {
            let errors = json.errors;
            errors.forEach(e => alert(e.message));
        },
        onError: (err) => {
            console.log(err);
            alert("문제가 발생하였습니다.");
        }
    })

}

function getEmail() {
    return $('#member_id').value;
}

function getPassword() {
    return $('#pwd').value;
}