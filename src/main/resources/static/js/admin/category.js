document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccess,
        errCallback: alertError
    })

    $("#category-type").addEventListener("change", (e) => {
        if(e.target.value == 1) {
            $("#parent-select").classList.remove('invisible');
            return;
        }
        $("#parent-select").classList.add('invisible');
    })
    $("#category-table").addEventListener("click", (e) => {
        deleteCategory(e.target.value);
    })
}

function onSuccess(response) {
    response.json().then((response) => {
    console.log(response);
        response.forEach((category) => {
            // 추가
            const creatorName = (category.creator == null) ? "null" : category.creator.name;

            const parentCategoryHtml = `<tr>
                <td>${category.priority}</td>
                <td>${category.name}</td>
                <td></td>
                <td>${creatorName}</td>
                <td>${category.createdTime}</td>
                <td> <button type="button" value=${category.id}>삭제</button></td>
                </tr>`;

            const SubCategoriesHtml = category.subCategories.reduce((prevSubCategory, nextSubCategory) => {
                const subCategoryCreatorName = (nextSubCategory.creator == null) ? "null" : nextSubCategory.creator.name;
                return prevSubCategory
                    + `<tr>
                        <td>${nextSubCategory.priority}</td>
                        <td></td>
                        <td>${nextSubCategory.name}</td>
                        <td>${subCategoryCreatorName}</td>
                        <td>${nextSubCategory.createdTime}</td>
                        <td> <button type="button" value=${category.id}>삭제</button></td>
                    </tr>`;
            }, ``);

            const parentCategoriesOption = `<option value=${category.id}>${category.name}</option>`;

            $("#category-table").insertAdjacentHTML("beforeend", parentCategoryHtml + SubCategoriesHtml);
            $("#parent-name").insertAdjacentHTML("beforeend", parentCategoriesOption);
        });
    });
}

function alertError() {
    alert("관리자에게 문의바랍니다.");
}

function onClickCreateButton(form) {
    const parentId = (form.categoryType.value == 0) ? null : form.parentId.value;

    fetchManager({
        url: '/api/admin/categories',
        method: 'POST',

        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            "name" : form.name.value,
            "parentId" : parentId,
            "priority" : form.priority.value
        }),
        callback: onCreateCategory,
        errCallback: alertError
    })
}

function onCreateCategory(response) {
    alert("카테고리가 정상적으로 추가되었습니다.");
    location.reload();
}

function onDeleteCategory() {
    alert("카테고리가 정상적으로 삭제되었습니다.");
    location.reload();
}

function deleteCategory(categoryId) {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    fetchManager({
        url: '/api/admin/categories/' + categoryId,
        method: 'DELETE',
        headers: { 'content-type': 'application/json'},
        callback: onDeleteCategory,
        errCallback: alertError
    })
}