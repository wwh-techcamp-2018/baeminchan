const requestURL = {
    users: '/api/users',
};

const inputElements = {
    emailID: 'email_id',
    emailDomain: 'email_domain',
    password: 'pw1',
    passwordCheck: 'pw2',
    name: 'name_input',
    phoneNumber1: 'cell1',
    phoneNumber2: 'cell2',
    phoneNumber3: 'cell3',
};

const requestForm = (url, method, body, callback) => {
    return {
        url: url,
        method: method,
        headers: ContentType.JSON,
        body: JSON.stringify(body),
        callback: callback,
    }
};

getElement($, idString, 'btn_submit').onclick = () => {
    const data = {
        email: getElement($, idString, inputElements.emailID).value
                + '@' + getElement($, idString, inputElements.emailDomain).value,
        password: getElement($, idString, inputElements.password).value,
        passwordCheck: getElement($, idString, inputElements.passwordCheck).value,
        name: getElement($, idString, inputElements.name).value,
        phoneNumber: getElement($, idString, inputElements.phoneNumber1).value
                + '-' + getElement($, idString, inputElements.phoneNumber2).value
                + '-' + getElement($, idString, inputElements.phoneNumber3).value,
    };
    fetchManager(requestForm(requestURL.users,
        'POST',
        data,
        resultProcess,
    ));
};

const resultProcess = (status, data) => {
    if (status === 201) {
        success(data);
    } else {
        error(data);
    }
};

const success = (result) => {
    redirect(result.url);
};

const error = (result) => {
    const errors = result.error;
    getElement($All, classString, 'caution')
        .forEach((caution) => {
            caution.style.display = 'none';
        });
    errors.forEach((error) => {
        $(postfixMaker(idString, error.field, '_caution')).style.display = 'block';
    });
};



