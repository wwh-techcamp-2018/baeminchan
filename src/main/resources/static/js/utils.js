function $(selector) {
    return document.querySelector(selector);
}

function fetchManager({ url, method, body, headers, success, error }) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((result) => {
        success(result)
    }).catch(e => {
        error(e);
    })
}

const logout_btn = $('.logout');
logout_btn && logout_btn.addEventListener('click', e => {
    e.preventDefault();
    fetch('/users/logout', { credentials: 'same-origin' })
    .then(redirectHome)
    .then(redirectHome)
});

function redirectHome() {
    location.href = '/';
}