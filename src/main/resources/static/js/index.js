function $(selector) {
    return document.querySelector(selector);
}

$('#logout').addEventListener('click', e => {
    e.preventDefault();

    fetch('/logout')
    .then(response => {
        // doesn't matter
        location.href = '/';
    })
    .catch(error => {
        location.href = '/';
    });

});