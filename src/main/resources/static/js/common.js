const $ = (selector) => {
    return document.querySelector(selector);
}

const $All = (selector) => {
    return document.querySelectorAll(selector);
}

const fetchManager = ({ url, method, body, headers, callback }) => {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        return response.json()
    }).then((result) => {
        callback(result)
    })
}

const customAjax = (u,d) => {
    return $.ajax({
                type: 'post',
                data: JSON.stringify(d),
                url: u,
                dataType : 'json',
                contentType : 'application/json',
    });
};