function $(selector) {
    return document.querySelector(selector);
}

function $_value(selector) {
    return $(selector).value;
}

class FetchManager {
    constructor({url, method, body = {}, headers = { 'content-type': 'application/json' }}) {
        this.url = url;
        this.method = method;
        this.body = body;
        this.headers = headers;
    }

    fetch() {
        const request = {
            method: this.method,
            body: this.body,
            headers: this.headers,
            credentials: 'same-origin'
        }

        fetch(this.url, request)
            .then(response => {
                if (!FetchManager.isSuccess(response.status)) {
                    response.json().then(this.fail);
                    return;
                }
                response.json().then(this.success);
            })
            .catch(error => {
                console.log(error)
            });
    }

    onSuccess(callback) {
        this.success = callback;
        return this;
    }

    onFailed(callback) {
        this.fail = callback;
        return this;
    }

    static isSuccess(status) {
        return 200 <= status && status < 400;
    }
}
