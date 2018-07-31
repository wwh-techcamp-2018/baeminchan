function $(selector) {
    return document.querySelector(selector);
}


function fetchManager({ url, method, body, headers, callback, errCallback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
           if(response.status == 200 || response.status == 201) {
           console.log(document.cookie);
                callback(response);
           } else {
                response.json().then(res => {
                    errCallback(res);
                })
           }
    })
}
