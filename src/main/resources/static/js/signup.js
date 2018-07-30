document.addEventListener('DOMContentLoaded', () => {
    initEvents();
});

function initEvents() {
    const joinBtn = $('#joinFrm .btn_area .btn');

    if (joinBtn == null) {
        return;
    }

    joinBtn.addEventListener('click', registerJoinEventHandler);
}

function registerJoinEventHandler(evt) {
    evt.preventDefault();
    
    const request = {
        email: $_value('#email_id') + '@' + $_value('#email_domain'),
        password: $_value('#pw1'),
        passwordCheck: $_value('#pw2'),
        name: $_value('#name'),
        phoneNumber: `${$_value('#cell1')}-${$_value('#cell2')}-${$_value('#cell3')}`,
    }

    new FetchManager({
        url: '/api/users',
        method: 'POST',
        body: JSON.stringify(request),
    })
    .onSuccess(response => {
        location.href = '/login.html';
    })
    .onFailed(errors => {
        errors.error.forEach((item) => {
            alert(item);
        });
    })
    .fetch();
}