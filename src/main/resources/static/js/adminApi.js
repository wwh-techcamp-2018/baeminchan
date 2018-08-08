document.write("<script src='/js/common.js'></script>")

function toIntElseNull(strValue) {
    return strValue.trim() === "" ? null : parseInt(strValue);
}

function createCategoryHandler() {
    fetchManager({
        url: "/api/admin/category",
        method: "POST",
        body: JSON.stringify({
            parentId: toIntElseNull($("#add-input-parent-id").value),
            title: $("#add-input-category-title").value
        }),
        headers: {"content-type": "application/json"},
        callback: refreshIfOk
    });
}

function deleteCategoryHandler() {
    fetchManager({
        url:
            "/api/admin/category/" +
            toIntElseNull($("#delete-input-category-id").value),
        method: "DELETE",
        headers: {"content-type": "application/json"},
        callback: refreshIfOk
    });
}

function updateCategoryHandler() {
    fetchManager({
        url: "/api/admin/category",
        method: "PUT",
        headers: {"content-type": "application/json"},
        body: JSON.stringify({
            parentId: toIntElseNull($("#update-input-parent-id").value),
            categoryId: toIntElseNull($("#update-input-category-id").value),
            title: $("#update-input-category-title").value
        }),
        callback: refreshIfOk
    });
}

function refreshIfOk(response) {
    if (response.status === 200 || response.status === 201)
        window.location.reload();
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents("#category-add-button", createCategoryHandler);
    initEvents("#category-delete-button", deleteCategoryHandler);
    initEvents("#category-update-button", updateCategoryHandler);
});
