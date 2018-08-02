
function initEvents() {
    loadMainCategories();

    $('#create-form').addEventListener('submit', createCategory);
    $('#update-form').addEventListener('submit', updateCategory);
    $('#delete-form').addEventListener('submit', deleteCategory);
}


const cached = {};

document.addEventListener('DOMContentLoaded', initEvents);

function createCategory(e) {
    e.preventDefault();

    const parentId = $('#create-parent-id').value;
    const name = $('#create-category-name').value;

    fetchManager({
        url: `/admin/categories/${parentId}`,
        method: "post",
        headers: {'content-type' : 'application/json; charset=utf-8'},
        body: JSON.stringify({
            name
        }),
        success: createCategorySuccess,
        error: handleError
    })
}

function createCategorySuccess(response){
    const parentId = $('#create-parent-id').value;
    const name = $('#create-category-name').value;
    const url = response.headers.get('Location');
    const id = url.split('/').pop();

    if(parentId == 0)
        $('.menu').innerHTML += categoryTemplate({id, name}, true);
    else {
        if(!cached[parentId]){
            return;
        }
        const category = "#category-" + parentId;
        const submenu = $(`${category} .sub-menu`);
        submenu.innerHTML += categoryTemplate({id, name}, false);
    }

    $('#create-parent-id').value = "";
    $('#create-category-name').value = "";
}

function deleteCategory(e) {
    e.preventDefault();

    const id = $('#delete-id').value;

    fetchManager({
        url: `/admin/categories/${id}`,
        method: "delete",
        headers: {'content-type' : 'application/json; charset=utf-8'},
        success: deleteCategorySuccess,
        error: handleError
    })
}

function deleteCategorySuccess(response) {
    const id = $('#delete-id').value;

    const category = $(`#category-${id}`);
    if(category)
        category.remove();
}

function updateCategory(e){
    e.preventDefault();

    const id = $('#update-id').value;
    const name = $('#update-category-name').value;

    fetchManager({
        url: `/admin/categories/${id}`,
        method: "put",
        headers: {'content-type' : 'application/json; charset=utf-8'},
        body: JSON.stringify({
            name
        }),
        success: updateCategorySuccess,
        error: handleError
    })
}

function updateCategorySuccess(response){
    const id = $('#update-id').value;
    const name = $('#update-category-name').value;

    const category = $(`#category-${id}`);

    if (category)
        category.firstElementChild.innerText = `${id} ${name}`;

    $('#update-id').value = "";
    $('#update-category-name').value = "";
}

function loadMainCategories() {
    //fetch('/admin/categories'),
    fetch('/categories', {
        credentials: 'same-origin'
    })
        .then(validateResponse)
        .then(({data}) => {
            const menu = $(".menu");
            data.forEach(category => {
                menu.innerHTML += categoryTemplate(category, true);
            });
        })
        .catch(handleError);
}

function showSubCategories(targetId) {
    console.log("targetId : " + targetId);

    const id = "category-"+targetId;
    const category_id = targetId;

    if (cached[category_id]) {
        return;
    }

    fetch(`/categories/${category_id}`)
        .then(validateResponse)
        .then(({data}) => {
            cached[category_id] = true;

            const submenu = $(`#${id} .sub-menu`);
            data.forEach(category => {
                submenu.innerHTML += categoryTemplate(category, false);
            });
        })
        .catch(handleError);
}

function categoryTemplate({id, name}, has_submenu) {
    return `<li id="category-${id}">
                <button onclick="showSubCategories(${id})"  >${id} ${name}</button>
                ${has_submenu ? '<ul class="sub-menu"></ul>' : ''}
            </li>`;
}

function validateResponse(response) {
    console.log(response);
    if (!response.ok) {
        throw new Error("페이지 로딩중에 에러가 발생했습니다. 새로고침 해주세요.");
    }
    return response.json();
}

function handleError(err) {
    console.error(err);
}