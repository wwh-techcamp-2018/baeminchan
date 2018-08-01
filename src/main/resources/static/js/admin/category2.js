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
    const categoryClass = ".category-" + response.url.split("/").pop(-1);
    let categoryTables = document.querySelectorAll(categoryClass);
    categoryTables.forEach(function deleteTables(child) {
        child.parentNode.removeChild(child);
    });
}

function deleteCategory({message, answer}) {
    if (message !== "ok") {
        alert(message);
        return;
    }
    var article = document.getElementById('answer-' + answer.id);
    var countElement = document.getElementById('comment-count');
    countElement.firstElementChild.innerHTML = Number(countElement.firstElementChild.innerHTML) - 1;
    article.parentNode.removeChild(article);
}

function onClickDeleteButton(categoryId) {
    let categoryDeleteUrl = '/api/categories/' + categoryId;

    fetchManager({
             url: categoryDeleteUrl,
             method: 'DELETE',
             headers: { 'content-type': 'application/json'},
             callback: deleteFunction
    })
}

