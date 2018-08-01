
function initEvents(){
    getCategory();

    $('.form-create').addEventListener('submit', createCategory);
    $('.form-update').addEventListener('submit', updateCategory);
    $('.form-delete').addEventListener('submit', deleteCategory);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function deleteCategory(e){
    e.preventDefault();

    const id = $("#delete_category_id").value;

    fetchManager({
        url: `/admin/category/${id}`,
        method: "delete",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        success: deleteCategorySuccess,
        error: fetchFailed
    });
}

function deleteCategorySuccess(response){
    const id = $("#delete_category_id").value;
    $(`#category-${id}`).remove();
}

function updateCategory(e){
    e.preventDefault();

    const id = $("#update_category_id").value;
    const parentId = $("#update_parent_category_id").value;
    const name = $("#update_category_name").value;

    fetchManager({
        url: `/admin/category/${id}`,
        method: "put",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        body: JSON.stringify({
              parentId,
              name
          }),
        success: updateCategorySuccess,
        error: fetchFailed
    });
}

function updateCategorySuccess(response){
    const id = $("#update_category_id").value;
    const parentId = $("#update_parent_category_id").value;
    const name = $("#update_category_name").value;

    const updateNode = $(`#category-${id}`);
    updateNode.innerHTML = `${id} : ${name}`;

    updateNode.remove();

    const parentNode = $(`${parentId == 1 ? '.menu' : '#category-' + parentId + ' .sub-menu'}`);
    parentNode.appendChild(updateNode);
}

function getCategory(){
    fetchManager({
        url: "/category",
        method: "get",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        success: getCategorySuccess,
        error: fetchFailed
    });
}

function getCategorySuccess(response){
    if(response.status == 200) {
        createMenu(response);
    }
    // TODO error 처리
}

function createMenu(response){
    let html = ``;
    response.json()
        .then(({ children }) =>  {
            children.forEach(category => {
                html += categoryTemplate(category)
            });
            $(".menu").insertAdjacentHTML("afterbegin", html);
        });
}

function categoryTemplate({ id, name, children }){
    return `
    <li id="category-${id}">${id} : ${name}
        ${children ? '<ul class="sub-menu">' + children.map(categoryTemplate).join('') + '</ul>' : ''}
    </li>`;
}

function createCategory(e){
    e.preventDefault();

    const parentId = $("#create_parent_category_id").value;
    const name = $("#create_category_name").value;

    fetchManager({
        url: `/admin/category/${parentId}`,
        method: "post",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        body: JSON.stringify({
              name
          }),
        success: createCategorySuccess,
        error: fetchFailed
    });
}

function createCategorySuccess(response){
    const parentId = $("#create_parent_category_id").value;
    const name = $("#create_category_name").value;

    const uri = response.headers.get('Location');
    const categoryId = uri.split('/').pop();

    $(`${parentId == 1 ? '.menu' : '#category-' + parentId + ' .sub-menu'}`).innerHTML += categoryTemplate({ id: categoryId, name, children: [] });
}

function fetchFailed(error){
    alert(error);
}