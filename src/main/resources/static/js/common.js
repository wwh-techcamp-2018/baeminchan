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

function fetchManager({url, method, body, onSuccess, onFailure}) {
    fetch(url, {method, body, headers: {"Content-type": "application/json"}, credentials: "same-origin"})
        .then((response) => {
            if (100 <= response.status && response.status < 400)
                onSuccess(response);
            else onFailure(response);
        });
}