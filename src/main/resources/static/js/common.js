function selector(query) {
    return document.querySelector(query);
}

function fetchManager({url, method, body, headers, onSuccess, onFailed, onError}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            let callback;
            const status = response.status;
            if (status >= 400) {
                callback = onFailed;
            } else callback = onSuccess;
            response.json().then(json => callback({status, json}))
        }).catch(onError)
}