function onSuccess(response) {
    window.location.reload();
}

function onFailure(response) {
    response.json().then((result) => alert("잘못된 요청입니다."));
}

function showCategories(categoryId) {
    let url = "/categories";
    if (!isNaN(categoryId)) {
        url = "/categories/" + categoryId;
    }

    fetchManager({
        url: url,
        method: "GET",
        onSuccess: onSuccessShowCategories,
        onFailure: onFailureShowCategories
    });
}

function createCategory(parentCategoryId, title) {
    let url = "/admin/categories";
    if (!isNaN(parentCategoryId)) {
        url = "/admin/categories/" + parentCategoryId;
    }
    const body = {title: title};

    fetchManager({
        url: url,
        method: "POST",
        body: JSON.stringify(body),
        onSuccess: onSuccess,
        onFailure: onFailure
    })
}

function updateCategory(categoryId, title) {
    const body = {title: title};

    fetchManager({
        url: "/admin/categories/" + categoryId,
        method: "PUT",
        body: JSON.stringify(body),
        onSuccess: onSuccess,
        onFailure: onFailure
    })
}

function deleteCategory(categoryId) {
    fetchManager({
        url: "/admin/categories/" + categoryId,
        method: "DELETE",
        onSuccess: onSuccess,
        onFailure: onFailure
    })
}

function handleCategories(object) {
    let appendString = "";

    let categories;
    if (object.data === undefined) {
        categories = object.children;
    }
    else if (object.children === undefined) {
        categories = object.data;
    }

    categories.forEach((category) => {
        appendString += "" +
            "<li id= " + "category-" + category.id + " class='category-item'>" +
            "<span>" + category.id + ". </span>" +
            "<span class='category-item-title'>" + category.title + "</span>" +
            "<button class='delete-button'>삭제</button>" +
            "</li>";

    });
    $(".category-container").innerHTML = appendString;
}

function onSuccessShowCategories(response) {
    response.json().then(handleCategories)
}

function onFailureShowCategories(response) {

}

function init() {
    $(".category-container").addEventListener("click", (evt) => {
        evt.preventDefault();
        if (evt.target.className === "delete-button") {
            deleteCategory(evt.target.parentElement.id.split("-").pop());
        }
        else if (evt.target.className === "category-item-title") {
            window.location.href = "/admin/categories/" + evt.target.parentElement.id.split("-").pop();
        }
    });

    $(".create-button").addEventListener("click", (evt) => {
        const parentId = parseInt(window.location.href.split("/").pop());
        const title = $_value("#create-title");
        createCategory(parentId, title);
    });

    $(".update-button").addEventListener("click", (evt) => {
        const categoryId = $_value("#update-id");
        const title = $_value("#update-title");
        updateCategory(categoryId, title);
    });

    showCategories(parseInt(window.location.href.split("/").pop()));
}

initialize(init);