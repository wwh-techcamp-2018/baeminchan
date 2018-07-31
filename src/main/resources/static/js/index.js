document.addEventListener("DOMContentLoaded", () => {
    loadCategories();
})

function loadCategories() {
    fetchManager({
        url: "/api/category",
        method: 'GET',
        headers: {'content-type': 'application/json'},
        onSuccess: onSuccess,
    })
}

function onSuccess(response) {
  response.json().then((result) => setCategories(result.children));
}

function setCategories(parents) {
    var categorymenu = $("#category-menu");
    parents.forEach(banchan => {
        var subCategories = "<ul class=\"sub-menu\">";
        banchan.children.forEach(c => {
                subCategories += "<li> <a href=\"#\">" + c.name + "</a></li>";});
        subCategories += "</ul>";
        var code = "<li> <a href=\"./side-dishs.html\">" + banchan.name + "</a>" + subCategories+ "</li>";
        categorymenu.insertAdjacentHTML("beforeend", code);
    });

    var lastCategory = categorymenu.lastChild;
    lastCategory.className = "brand";
}

