function $(selector) {
    return document.querySelector(selector);
}

function $All(selector) {
    return document.querySelectorAll(selector);
}

function fetchManager({ url, method, body, headers, callback, failCallback}) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((response) => {
        return response.json();
    }).then((result) => {
        if(result.status === 200 || result.status === 201) {
            console.log(result);
            callback(result)
        } else {
            console.log(result);
            failCallback(result);
            // error.errors.forEach((val) => {
            //     const emailCautionEl = $('#' + val.field + '_caution');
            //     emailCautionEl.innerText = val.defaultMessage;
            //     emailCautionEl.style.display = 'block';
            // });
        }
    });
}