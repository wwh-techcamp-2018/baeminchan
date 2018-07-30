document.addEventListener('DOMContentLoaded', () => {
    addClickEvent('.btn.btn_mint.btn_login', loginEventHandler);
});

function loginEventHandler(evt) {
    evt.preventDefault();

    const request = {
        email: valueOf('#member_id'),
        password: valueOf('#pwd')
    }

    new FetchManager({
        url: '/api/users/login',
        method: 'POST',
        body: JSON.stringify(request),
    })
    .onSuccess(response => {
        alert('로그인에 성공했습니다.');
        location.href = '/';
    })
    .onFailed(errors => {
        errors.error.forEach(item => {
            alert(item.message);
        });
    })
    .fetch();
}