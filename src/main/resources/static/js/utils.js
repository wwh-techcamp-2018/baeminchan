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
    fetchManager({
        url: '/users/logout',
        success: redirectHome,
        error: redirectHome
    });
});

function redirectHome() {
    location.href = '/';
}