const joinFields = ["email", "password", "passwordConfirm", "name", "phoneNumber"];

function onSuccessJoin(response) {
    console.log(response);
    window.location.href = "/";
}

function onFailureJoin(response) {
    response.json().then(handleErrors);
}

function signup() {
    clearCautions(joinFields);
    const body = {
        email: getEmail(),
        password: $_value("#pw1"),
        passwordConfirm: $_value("#pw2"),
        name: $_value("#name"),
        phoneNumber: getPhoneNumber()
    };

    fetchManager({
        url: "/users/signup",
        method: "POST",
        body: JSON.stringify(body),
        headers: {"Content-type": "application/json"},
        onSuccess: onSuccessJoin,
        onFailure: onFailureJoin
    })
}

function getEmail() {
    return $_value("#email_id") + "@" + $_value("#email_domain");
}

function getPhoneNumber() {
    return $_value("#cell1") + "-" + $_value("#cell2") + "-" + $_value("#cell3");
}

function init() {
    $('#join-button').addEventListener("click", function (evt) {
        evt.preventDefault();
        signup();
    });
    addChangeListener($_all(".email_section"), "email");
    addChangeListener($_all("#pw1"), "password");
    addChangeListener($_all("#pw2"), "passwordConfirm");
    addChangeListener($_all("#name"), "name");
    addChangeListener($_all(".phoneNumber_section"), "phoneNumber");

}

initialize(init);