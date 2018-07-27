function $(selector) {
    return document.querySelector(selector);
}


function fetchManager({ url, method, body, headers, callback, errCallback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
           if(response.status == 200 || response.status == 201) {
                callback(response);
                throw "finish";
           }

            return response.json();
    }).then((result) => {
        errCallback(result);
    }).catch((result) => {
    })
}
