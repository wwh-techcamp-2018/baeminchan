document.addEventListener("DOMContentLoaded", function(evt) {

    getRootCategories();

    $(".direction-btn-box .next").addEventListener("click", function (evt) {
        evt.preventDefault();
        navigateRight();
    });
    $(".direction-btn-box .prev").addEventListener("click", function (evt) {
        evt.preventDefault();
        navigateLeft();
    });

    $(".dot-btn-box").addEventListener("click", function (evt) {
        evt.preventDefault();
        if(evt.target.classList.contains("dot")) {
            const dots = $_all(".dot");
            let i;
            for (i = 0; i < dots.length ; i++) {
                if(dots[i] == evt.target ) {
                   break;
                }
            }
            navigateToIndex(i);
        }
    });
    startAuto($(".direction-btn-box .next"));
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

function navigateRight() {
    const items = $_all(".img-item");
    navigateToIndex(((getCurrentDisplayIndex() + 1) % items.length))
}

function navigateLeft() {
    const items = $_all(".img-item");
    navigateToIndex(((getCurrentDisplayIndex() + 7) % items.length))
}

function navigateToIndex(index) {
    const items = $_all(".img-item");
    const dots = $_all(".dot");
    let i, j;
    for (i = 0; i < items.length; i++) {
        if(items[i].classList.contains("visible-banner")) {
            break;
        }
    }
    for (j = 0; j < dots.length; j++) {
        if(dots[j].classList.contains("on")) {
            break;
        }
    }
    items[i].classList.toggle("visible-banner");
    dots[j].classList.toggle("on");
    items[index].classList.toggle("visible-banner");
    dots[index].classList.toggle("on");

    startAuto($(".direction-btn-box .next"));

}

function getCurrentDisplayIndex() {
    const items = $_all(".img-item");
    let i;
    for (i = 0; i < items.length; i++) {
        if(items[i].classList.contains("visible-banner")) {
            break;
        }
    }
    return i;
}

let auto;

function startAuto(elem) {
    if(auto) clearInterval(auto);
    auto = setInterval(function() {
        elem.click();
    }, 4000);

}