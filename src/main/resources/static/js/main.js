function $(selector) {
    return document.querySelector(selector);
}

function $_value(selector) {
    return $(selector).value;
}

function $_all(selector) {
    return document.querySelectorAll(selector);

    
}
function initialize(init) {
    document.addEventListener("DOMContentLoaded", () => {
        init();
    })
}

function fetchManager({url, method, body, headers, onSuccess, onFailure}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            if (response.status === 200)
                onSuccess(response);
            else onFailure(response);
        })
}