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
    console.log(e.target.value)
        if(e.target.value == 1) {
            $("#parent-select").style = "display:visible";
            return;
        }
        $("#parent-select").style = "display:none";
    })

}

function onSuccess(response) {
    response.json().then((response) => {
    console.log(response);
        response.forEach((category) => {
            // 추가
            const html1 = `<tr><td>` + category.priority
                         + `</td><td>` + category.name
                         + `</td><td>`
                         + `</td><td>` + ((category.creator == null) ? "null" : category.creator.name)
                         + `</td><td>` + category.createdTime
                         + `</td><td> <button type="button" onclick=deleteCategory(` + category.id + `)>삭제</button>`
                         + `</td></tr>`;

            const html2 = category.subCategories.reduce((prevSubCategory, nextSubCategory) => {
                return prevSubCategory
                              + `<tr><td>` + nextSubCategory.priority
                              + `</td><td>`
                              + `</td><td>` + nextSubCategory.name
                              + `</td><td>` + ((nextSubCategory.creator == null) ? "null" : nextSubCategory.creator.name)
                              + `</td><td>` + nextSubCategory.createdTime
                              + `</td><td> <button type="button" onclick=deleteCategory(` + nextSubCategory.id + `)>삭제</button>`
                              + `</td></tr>`;
            }, ``);

            const html3 = ` <option value=` + category.id + `>` + category.name + `</option>`;

            $("#category-table").insertAdjacentHTML("beforeend", html1 + html2);
            $("#parent-name").insertAdjacentHTML("beforeend", html3);

        });
    });
}

function alertError() {
    alert("관리자에게 문의바랍니다.");
}

function onClickCreateButton(form) {
    const parentId = (form.categoryType.value == 0) ? null : form.parentId.value;

    fetchManager({
        url: '/api/categories',
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
        url: '/api/categories/' + categoryId,
        method: 'DELETE',
        headers: { 'content-type': 'application/json'},
        callback: onDeleteCategory,
        errCallback: alertError
    })
}