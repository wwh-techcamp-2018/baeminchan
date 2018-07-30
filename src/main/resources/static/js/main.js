function $(selector) {
    return document.querySelector(selector);
}

<<<<<<< HEAD
function $_all(selector) {
    return document.querySelectorAll(selector);
}

=======
>>>>>>> upstream/team3_pair2
function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            const value = response;
            if(value.status == 200) {
                return;
            } else {
                return value.json();
            }

        }).then((result) => {
            callback(result);
    });
<<<<<<< HEAD
}

function getManager({url, method, headers, callback}) {
    fetch(url, {method, headers, credentials: "same-origin"})
        .then((response) => {
            const value = response;
            return value.json();
        }).then((result) => {
        callback(result);
    });
=======
>>>>>>> upstream/team3_pair2
}