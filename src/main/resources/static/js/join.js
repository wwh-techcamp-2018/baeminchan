
function initEvents(){
    const signUpBtn = $("#submitSignUp");
    signUpBtn.addEventListener("click", handleSignUp);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function handleSignUp(evt){
    evt.preventDefault();

    const email = $("#email_id").value +'@'+ $("#email_domain").value;
    const password = $("#password").value;
    const confirmPassword = $("#confirmPassword").value;
    const name = $("#name").value;
    const phone = $("#cell1").value + '-' + $("#cell2").value + '-' + $("#cell3").value;

    fetchManager({
        url: "/users",
        method: "post",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        body: JSON.stringify({
            "email": email,
            "password": password,
            "confirmPassword": confirmPassword,
            "name": name,
            "phone": phone
        }),
        success: signSuccess,
        error: signFailed
    })
}

function signSuccess(response){
    console.log(response);
    if(response.status == 201) {
        location.href = '/'
    }
    // TODO error 처리
}

function signFailed(error){
    alert(error);
}