$(idString('login_btn')).onclick = (event) => {
    const login_input = {
        email : getElementValue($, idString, 'member_id'),
        password : getElementValue($, idString, 'pwd'),
    };

    fetchManager({
        url : '/api/users/login',
        method : 'POST',
        body : JSON.stringify(login_input),
        headers : {'content-type': 'application/json'},
        callback : resultProcess,
    });
};

const resultProcess = (status, data) => {
    if(status === 400){
        $(idString('login_caution')).style.display = 'block';
        $(idString('login_caution')).innerHTML = data.error[0].message;
    }
    else{
        document.location = data.url;
    }
};