function $(selector) {
    return document.querySelector(selector);
}

const logoutElement = $('#logout');
logoutElement && logoutElement.addEventListener('click', e => {
    e.preventDefault();

    fetch('/logout')
    .then(moveDocumentRoot)
    .catch(moveDocumentRoot);
});

function moveDocumentRoot() {
    location.href = '/';
}