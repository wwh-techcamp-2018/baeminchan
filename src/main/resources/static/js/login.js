const requestURL = {
    login: '/api/users/login',
};

const inputElements = {
    email: 'member_id',
    password: 'pwd',
};

getElement($, idString, 'login_btn').onclick = () => {
    const loginInput = {
        email: getElement($, idString, inputElements.email).value,
        password: getElement($, idString, inputElements.password).value,
    };

    fetchManager({
        url: requestURL.login,
        method: METHOD.POST,
        body: JSON.stringify(loginInput),
        headers: ContentType.JSON,
        callback: resultProcess,
    });
};

const resultProcess = (status, data) => {
    if (status === 400) {
        getElement($, idString, 'login_caution').style.display = 'block';
        getElement($, idString, 'login_caution').innerHTML = data.error[0].message;
    } else {
        redirect(data.url);
    }
};