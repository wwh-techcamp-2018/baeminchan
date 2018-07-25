function $(selector) {
    return document.querySelector(selector);
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((response) => {
        return response
    }).then((result) => {
        callback(result)
    })
}

function initEvents(){
    const signUpBtn = $("#submitSignUp");
    signUpBtn.addEventListener("click",signUpHandler);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function signUpHandler(evt){
    evt.preventDefault();
    const email = $("#email_id").value +'@'+ $("#email_domain").value;
    const password = $("#password").value;
    const confirmPassword = $("#confirmPassword").value;
    const name = $("#name").value;
    const phone = $("#cell1").value + '-' + $("#cell2").value + '-' + $("#cell3").value;

    fetchManager({
        url:"/users",
        method:"post",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        body: JSON.stringify({
            "email":email,
            "password":password,
            "confirmPassword":confirmPassword,
            "name":name,
            "phone":phone
        }),
        callback: signResult
    })
}

function signResult(response){
    console.log(response);
    if(response.status == 201) {
        location.href = '/'
    }
    // todo error 처리
}