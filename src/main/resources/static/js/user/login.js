const loginFields = ["email", "password"];

function onSuccessLogin(response) {
    window.location.href = "/";
}

function onFailureLogin(response) {
    response.json().then(handleErrors)
}

function login() {
    clearCautions(loginFields);
    const body = {
        email: $("#member_id").value,
        password: $("#pwd").value
    };

    fetchManager({
        url: "/users/login",
        body: JSON.stringify(body),
        method: "post",
        headers: {"Content-type": "application/json"},
        onSuccess: onSuccessLogin,
        onFailure: onFailureLogin
    })
}

function init() {
    $("#login-button").addEventListener("click", function(evt) {
        evt.preventDefault();
        login();
    });
    addChangeListener($("#member_id"), "email");
    addChangeListener($("#pwd"), "password");
}

initialize(init);