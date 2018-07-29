// $(document).ready(function () {
//     $('#btn_submit').click(() => {
//         const data = {
//             email: $('#email_id').val() + '@' + $('#email_domain').val(),
//             password: $('#pw1').val(),
//             passwordCheck: $('#pw2').val(),
//             name: $('#name_input').val(),
//             phoneNumber: $('#cell1').val() + '-' + $('#cell2').val() + '-' + $('#cell3').val(),
//         }
//
//         $.ajax({
//             type: 'POST',
//             url: '/api/users',
//             data: JSON.stringify(data),
//             success: function (result) {
//                 document.location = result.url;
//             },
//             error: function (result) {
//                 const errors = result.responseJSON.error;
//                 console.log(errors);
//                 errors.forEach((error) => {
//                     const field = error.field;
//                     $('#' + field + '> .caution').css("display", "block")
//
//                 })
//                 ;
//                 alert(result.responseJSON.error.join('\n'));
//             },
//             dataType: 'json',
//             contentType: 'application/json',
//         });
//
//     })
//     ;
// });

const inputElements = {
    emailID : 'email_id',
    emailDomain : 'email_domain',
    password : 'pw1',
    passwordCheck : 'pw2',
    name : 'name_input',
    phoneNumber1 : 'cell1',
    phoneNumber2 : 'cell2',
    phoneNumber3 : 'cell3',
}

const requestForm = (url, method, body, callback) => {
    return {
        url: url,
        method: method,
        headers: {'content-type': 'application/json'},
        body : JSON.stringify(body),
        callback : callback,
    }
}

const success = (result) => {
    document.location = result.url;
}

const error = (result) => {
    const errors = result.error;
    document.querySelectorAll(classString('caution'))
        .forEach((caution) => {
            console.log(caution);
            caution.style.display = 'none';

        });
    console.log(errors);
    errors.forEach((error) => {
        const field = error.field;
        console.log(field);
        $(idString(field)+'_caution').style.display = 'block';
    });
}


$(idString('btn_submit')).onclick = (event) => {
    const data = {
        email: getElementValue($, idString, inputElements.emailID) + '@' + getElementValue($, idString, inputElements.emailDomain),
        password: getElementValue($, idString, inputElements.password),
        passwordCheck: getElementValue($, idString, inputElements.passwordCheck),
        name: getElementValue($, idString, inputElements.name),
        phoneNumber: getElementValue($, idString, inputElements.phoneNumber1) + '-' + getElementValue($, idString, inputElements.phoneNumber2)  + '-' + getElementValue($, idString, inputElements.phoneNumber3),
    }
    console.log(data);

    fetchManager(requestForm('/api/users',
        'POST',
        data,
        resultProcess,
        ));
}

const resultProcess = (status, data) => {
    if(status === 201){
        success(data);
    } else {
        error(data);
    }
}