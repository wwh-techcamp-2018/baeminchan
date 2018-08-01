function $(selector) {
    return document.querySelector(selector);
}

function fetchManager({ url, method, body, headers, onSuccess, onFailure }) {
    fetch(url, {
        method,
        body,
        headers,
 	    credentials: "same-origin"
    }).then((response) => {
        if (response.ok)
            onSuccess(response);
        else
            onFailure(response);
    });
}