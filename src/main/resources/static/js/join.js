const joinFields = ["email", "password", "passwordConfirm", "name", "phoneNumber"];

function onSuccessJoin(response) {
    console.log(response);
    window.location.href = "/";
}

function onFailureJoin(response) {
    response.json().then(handleErrors);
}

function handleErrors(body) {
    const errors = body.errors;
    errors.forEach(function (error) {
        const cautionField = $("#" + error.fieldName + "_caution");
        cautionField.style.display = "inline";
        cautionField.innerText = error.errorMessage;

    });

}

function clearCaution(fieldName) {
    $("#" + fieldName + "_caution").style.display = "none";
}

function clearAllCaution() {
    joinFields.forEach(function (field) {
        clearCaution(field);
    })
}

function signup() {
    clearAllCaution();
    const body = {
        email: $("#email_id").value + "@" + $("#email_domain").value,
        password: $("#pw1").value,
        passwordConfirm: $("#pw2").value,
        name: $("#name").value,
        phoneNumber: $("#cell1").value + "-" + $("#cell2").value + "-" + $("#cell3").value
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

function addChangeListener(element, fieldName) {
    element.addEventListener("input", function () {
        clearCaution(fieldName);
    })

}

function init() {
    $('#join-button').addEventListener("click", function (evt) {
        evt.preventDefault();
        signup();
    });
    addChangeListener($("#email_id"), "email");
    addChangeListener($("#email_domain"), "email");
    addChangeListener($("#pw1"), "password");
    addChangeListener($("#pw2"), "passwordConfirm");
    addChangeListener($("#name"), "name");
    addChangeListener($("#cell1"), "phoneNumber");
    addChangeListener($("#cell2"), "phoneNumber");
    addChangeListener($("#cell3"), "phoneNumber");

}

initialize(init);