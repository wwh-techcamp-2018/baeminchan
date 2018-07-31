function onSuccess(response) {
    window.location.reload();
}

function onFailure(response) {

}

function showCategories(categoryId) {
    let url = "/categories";
    if (!isNaN(categoryId)) {
        url = "/categories/" + categoryId;
    }

    fetchManager({
        url: url,
        method: "GET",
        headers: {"Content-type": "application/json"},
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
        headers: {"Content-type": "application/json"},
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
        headers: {"Content-type": "application/json"},
        body: JSON.stringify(body),
        onSuccess: onSuccess,
        onFailure: onFailure
    })
}

function deleteCategory(categoryId) {
    fetchManager({
        url: "/admin/categories/" + categoryId,
        method: "DELETE",
        headers: {"Content-type": "application/json"},
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
    $(".category-container").addEventListener("click", function (evt) {
        evt.preventDefault();
        if (evt.target.className === "delete-button") {
            deleteCategory(evt.target.parentElement.id.split("-").pop());
        }
        else if (evt.target.className === "category-item-title") {
            window.location.href = "/admin/categories/" + evt.target.parentElement.id.split("-").pop();
        }
    });

    $(".create-button").addEventListener("click", function (evt) {
        const parentId = parseInt(window.location.href.split("/").pop());
        const title = $_value("#create-title");
        createCategory(parentId, title);
    });

    $(".update-button").addEventListener("click", function (evt) {
        const categoryId = $_value("#update-id");
        const title = $_value("#update-title");
        updateCategory(categoryId, title);
    });

    showCategories(parseInt(window.location.href.split("/").pop()));
}

initialize(init);