function initCategories(callback, failCallback) {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: {'content-type': 'application/json'},
        body: {},
        callback: callback,
        failCallback: failCallback
    })
}

function getCategoryCallback(response) {
    const menu = $(".category-menu");
    response.body.forEach((parentCategory) => {
        let html =
            `<li>
                <a href="/side-dishs.html">${parentCategory.name}</a>
                <ul class="sub-menu">`;
        parentCategory.children.forEach((childCategory) => {
            html += `<li><a href="#">${childCategory.name}</a></li>`;
        });

        html += `</ui></li>`;
        menu.insertAdjacentHTML("beforeend", html);
    })
}

function failGetCategoryCallback() {
    alert('카테고리 정보를 가져오는데 실패했습니다.');
}

initCategories(getCategoryCallback, failGetCategoryCallback);