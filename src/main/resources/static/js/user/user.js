function handleErrors(body) {
    const errors = body.errors;
    errors.forEach(function (error) {
        const cautionField = $("#" + error.fieldName + "_caution");
        cautionField.style.display = "inline";
        cautionField.innerText = error.errorMessage;
    })
}


function clearCaution(fieldName) {
    $("#" + fieldName + "_caution").style.display = "none";
}

function clearCautions(fields) {
    fields.forEach(function (field) {
        clearCaution(field);
    })
}

function addChangeListener(elements, fieldName) {
    elements.forEach(function (element) {
        element.addEventListener("input", function () {
            clearCaution(fieldName);
        })
    });
}