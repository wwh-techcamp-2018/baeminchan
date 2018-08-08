document.addEventListener("DOMContentLoaded", function(evt) {

    getRootCategories();

    getEventCategories();

    const searchBar = new SearchBar();


    const sideDishSlider = new Slider($("#side-dish"), "8", 4);
    const mainDishSlider = new Slider($("#main-dish"), "9", 4);



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

function getEventCategories() {
    getManager({
        url: "/api/categories/event",
        method: "GET",
        headers: {"Content-type": "application/json"},
        callback : drawEventCategories
    });
}

function getEventCategoryProducts(id) {
    getManager({
        url: "/api/categories/event/" + id,
        method: "GET",
        headers: {"Content-type": "application/json"},
        callback : drawEventCategoryProducts
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

function drawEventCategories(result) {
    $(".tab-btn-box").innerHTML = "";
    const template = Handlebars.templates["precompile/event_category_template"];
    for(category of result) {
        $(".tab-btn-box").innerHTML += template(category);
    }
    const categories = $_all(".tab-btn-box li");
    for(var i = 0; i < categories.length; ++i) {
        const elem = categories[i];
        elem.addEventListener("click", function(evt) {
            clickEventCategory(this.querySelector("a").getAttribute("type"));
        });
    }
    clickEventCategory(chooseRandomFromArray(categories).querySelector("a").getAttribute("type"));
}

function drawEventCategoryProducts(result) {
    $(".tab-content-box").innerHTML = "";
    const template = Handlebars.templates["precompile/event_category_product_template"];
    for(product of result) {
        $(".tab-content-box").innerHTML += template(product);
    }
}

function clickEventCategory(id) {
    if ($("a.now")) {
        $("a.now").classList.toggle("now");
    }
    $("#event-category-" + id).classList.toggle("now");
    getEventCategoryProducts(id);
}

function navigateRight() {
    const items = $_all(".img-item");
    navigateToIndex(((getCurrentDisplayIndex() + 1) % items.length))
}

function navigateLeft() {
    const items = $_all(".img-item");
    navigateToIndex(((getCurrentDisplayIndex() + items.length - 1) % items.length))
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