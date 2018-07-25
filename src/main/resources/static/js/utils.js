function $(selector) {
    return document.querySelector(selector);
}

function fetchManager({ url, method, body, headers, callback, errorCallback }) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((response) => {
        if (response.status === 200 || response.status === 201) {
            callback(response);
        }
        return response.json();
    }).then((result) => {
        errorCallback(result);
    })
}