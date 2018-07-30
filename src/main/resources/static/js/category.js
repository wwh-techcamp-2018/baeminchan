const cached = {};

document.addEventListener('DOMContentLoaded', loadMainCategories);

function loadMainCategories() {
    fetch('/categories')
        .then(validateResponse)
        .then(({data}) => {
            const menu = $("#gnb .menu");
            menu.addEventListener('mouseover', showSubCategories);

            data.forEach(category => {
                menu.innerHTML += categoryTemplate(category, true);
            });
        })
        .catch(handleError);
}

function showSubCategories(e) {
    const {target} = e;
    const id = target.id || target.parentNode.id;
    const category_id = id.split('-').pop();

    if (cached[category_id]) {
        return;
    }

    fetch(`/categories/${category_id}`)
        .then(validateResponse)
        .then(({data}) => {
            cached[category_id] = true;

            const submenu = $(`#${id} .sub-menu`);
            data.forEach(category => {
                submenu.innerHTML += categoryTemplate(category);
            });
        })
        .catch(handleError);
}

function categoryTemplate({id, name}, has_submenu) {
    return `<li id="category-${id}">
                <a>${name}</a>
                ${has_submenu ? '<ul class="sub-menu"></ul>' : ''}
            </li>`;
}

function validateResponse(response) {
    if (!response.ok) {
        throw new Error("페이지 로딩중에 에러가 발생했습니다. 새로고침 해주세요.");
    }
    return response.json();
}

function handleError(err) {
    console.error(err);
}