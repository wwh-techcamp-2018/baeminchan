function $(selector) {
    return document.querySelector(selector);
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        return response.json()
    }).then((result) => {
        callback(result)
    })
}

function customAjax(u,d) {
    return $.ajax({
                type: 'post',
                data: JSON.stringify(d),
                url: u,
                dataType : 'json',
                contentType : 'application/json',
    });
}