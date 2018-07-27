document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const joinBtn = $('#btnSubmit');
    const emailDomainBtn = $('#email_domain_select');
    if(joinBtn === null) return;
    joinBtn.addEventListener("click", registerUserHandler);
    emailDomainBtn.addEventListener("change", emailDomainHandler);
}

function emailDomainHandler(event) {
    $(".tb_join").querySelector('#email_domain').value = event.target.value;
}

function onSuccess() {
    alert("회원가입을 축하드립니다!");
    location.href = '/';
}

function checkBlank(joinTable, selectors) {
    for(let selector of selectors) {
        let target = joinTable.querySelector(selector).value;
        if(target === '') {
            alert('빈칸을 채워주세요.'); joinTable.querySelector(selector).focus(); return false;
        }
    }
    return true;
}

function registerUserHandler(evt) {
    evt.preventDefault();
    const joinTable = $(".tb_join");

    let userId = joinTable.querySelector('#email_id').value + "@" + joinTable.querySelector('#email_domain').value;
    let password = joinTable.querySelector('#pw1').value;
    let password2 = joinTable.querySelector('#pw2').value;
    let name = joinTable.querySelector('#name').value;
    let phoneNumber = joinTable.querySelector('#cell1').value +'-'+ joinTable.querySelector('#cell2').value + '-'+ joinTable.querySelector('#cell3').value;

    const selectors = ['#email_id', '#pw1', '#pw2', '#name', '#cell1', '#cell2', '#cell3'];

    if(!checkBlank(joinTable, selectors)) {
        return;
    }


    fetchManager({
        url: '/api/users',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            "userId" : userId,
            "password" : password,
            "password2" : password2,
            "name" : name,
            "phoneNumber" : phoneNumber
            }),
        callback: onSuccess,
        errCallback: alertError
    })
}

function alertError(result) {
    result.then((response) => {
    let errorMessage = "";
        for (let error of response.errors) {
          errorMessage += error.errorMessage + "\n";
        }
    alert(errorMessage);
    })
}