function $(selector) {
    return document.querySelector(selector);
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        if(response.status === 200) {
            callback(response);
            return false;
        }
        return callback(response.json())
    })
}

function deleteFunction(response) {
    alert("삭제되었습니다.");
    location.href = "/admin/categories";
}

function onClickDeleteButton() {
    document.querySelectorAll('input[name="chk_info"]').forEach(function print(value) {
        if(value.checked === true) {
             let categoryDeleteUrl = '/api/categories/' + value.getAttribute('data-id');
             console.log(categoryDeleteUrl);
             fetchManager({
                         url: categoryDeleteUrl,
                         method: 'DELETE',
                         headers: { 'content-type': 'application/json'},
                         callback: deleteFunction
             })
        }
    });

}