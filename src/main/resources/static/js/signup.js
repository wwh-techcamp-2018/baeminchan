document.addEventListener("DOMContentLoaded", function (evt) {
    const email = getEmail()

    const submitButton = $(".btn.btn_mint.btn_big");
    submitButton.addEventListener("click", function (evt) {
        evt.preventDefault();
        const postObject = {
            "email": getEmail(),
            "password": getPassword(),
            "passwordConfirm": getPasswordConfirm(),
            "name": getName(),
            "phoneNumber": getPhoneNumber(),
        };

        fetchManager({
            url: "/users/signup",
            method: "POST",
            headers: {"content-type": "application/json"},
            body: JSON.stringify(postObject),
            callback: displayErrors
        });

    })

});

function displayErrors(result) {
    if(!result) {
        window.location.href = "/users/login";
    }

    let appendText = "";
    for(message of result.errors) {
        appendText += message.errorMessage + "<br />";
    }
    $(".error-message-holder").innerHTML = appendText;
}

function getEmail() {
    const emailUser = $_value("#email_id");
    const emailDomain = $_value("#email_domain");
    return emailUser + "@" + emailDomain;
}

function getPassword() {
    const password = $_value("#pw1");
    return password
}

function getPasswordConfirm() {
    const confirm = $_value("#pw2");
    return confirm;
}

function getName() {
    const name = $_value("#name");
    return name;
}

function getPhoneNumber() {
    return $_value("#cell1") + "-" + $_value("#cell2") + "-" + $_value("#cell3");
}