document.addEventListener('DOMContentLoaded', () => {
    addEvent('#joinFrm .btn_area .btn', 'click', registerJoinEventHandler);
});

function registerJoinEventHandler(evt) {
    evt.preventDefault();
    
    const request = {
        email: `${valueOf('#email_id')}@${valueOf('#email_domain')}`,
        password: valueOf('#pw1'),
        passwordCheck: valueOf('#pw2'),
        name: valueOf('#name'),
        phoneNumber: `${valueOf('#cell1')}-${valueOf('#cell2')}-${valueOf('#cell3')}`,
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
        errors.error.forEach(item => {
            alert(item.message);
        });
    })
    .doFetch();
}

