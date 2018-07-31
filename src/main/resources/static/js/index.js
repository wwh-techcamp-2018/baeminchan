document.addEventListener("DOMContentLoaded", function(evt) {

    getRootCategories();
});

function getRootCategories() {
    getManager({
        url: "/api/categories/root",
        method: "GET",
        headers: {"Content-type": "application/json"},
        callback : drawCategories
    });
}

function getSubCategories(id) {
    getManager({
        url: "/api/categories/" + id,
        method: "GET",
        headers: {"Content-type": "application/json"},
        callback : drawSubCategories
    });
}

function drawCategories(result) {

    var template = Handlebars.templates["precompile/title_template"];
    for(const category of result) {
        $(".category-menu").innerHTML += template(category);

    }
    const categoryTypes = $_all(".category-list-title");
    for(var i = 0; i < categoryTypes.length; ++i) {
        const elem = categoryTypes[i];
        elem.addEventListener("mouseenter", function(evt) {
            const id = this.getAttribute("type");
            getSubCategories(id);
        });
    }
}

function drawSubCategories(result) {
    $("#category-title-" + result.id).querySelector(".sub-menu").innerHTML = "";
    var template = Handlebars.templates["precompile/subtitle_template"];
    for(const category of result.categories) {
        $("#category-title-" + result.id).querySelector(".sub-menu").innerHTML += template(category);

    }
}