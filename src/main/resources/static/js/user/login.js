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
        email: $_value("#member_id"),
        password: $_value("#pwd")
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
    addChangeListener($_all("#member_id"), "email");
    addChangeListener($_all("#pwd"), "password");
}

initialize(init);