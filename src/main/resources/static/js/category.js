function initCategories() {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: {'content-type': 'application/json'},
        body: {},
        callback: getCategories,
        failCallback: failGetCategories
    })
}

function getCategories(response) {
    const menu = $(".category-menu");
    response.body.forEach((parentCategory) => {
        let html =
            `<li>
                <a href="/side-dishs.html">${parentCategory.name}</a>
                <ul class="sub-menu">`;
        parentCategory.children.forEach((childCategory) => {
            html += `<li><a href="#">${childCategory.name}</a></li>`;
        });

        html += `</ui>`;
        html += `</li>`;
        menu.insertAdjacentHTML("beforeend", html);
    })
}

function failGetCategories() {
    alert('카테고리 정보를 가져오는데 실패했습니다.');
}

initCategories();