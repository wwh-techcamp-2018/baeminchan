const visible_image_class = "visible-image";
const on_dot_class = "on";

let interval;


function activateAnimation() {
    interval = window.setInterval(() => {
        moveNext();
    }, 3000);
}

function deactivateAnimation() {
    window.clearInterval(interval);
}

function changePhase(current, next) {
    const dots = $_all(".dot");
    const images = $_all(".img-item");

    images[current].classList.toggle(visible_image_class);
    images[next].classList.toggle(visible_image_class);

    dots[current].classList.toggle(on_dot_class);
    dots[next].classList.toggle(on_dot_class);

    deactivateAnimation();
    activateAnimation();
}

function currentFocusIndex() {
    const images = $_all(".img-item");
    let current = -1;

    images.forEach((image, index) => {
        if (image.classList.contains("visible-image"))
            current = index;
    });
    return current;
}

function movePrevious() {
    const images = $_all(".img-item");
    const current = currentFocusIndex();
    const next = (current + images.length - 1) % images.length;
    changePhase(current, next);
}

function moveNext() {
    const images = $_all(".img-item");
    const current = currentFocusIndex();
    const next = (current + 1) % images.length;
    changePhase(current, next);
}

function clickDot(clickDot) {
    const current = currentFocusIndex();
    let next = dotIndex(clickDot);
    changePhase(current, next);
}

function dotIndex(targetDot) {
    const dots = $_all(".dot");
    let next = -1;
    dots.forEach((dot, idx) => {
        if (dot === targetDot)
            next = idx;
    });
    return next;
}

function init() {
    $(".bm-icon.spr-btn-arrow-main-slide.prev").addEventListener("click", (evt) => {
        evt.preventDefault();
        movePrevious();
    });

    $(".bm-icon.spr-btn-arrow-main-slide.next").addEventListener("click", (evt) => {
        evt.preventDefault();
        moveNext();
    });

    $(".dot-btn-box").addEventListener("click", (evt) => {
        evt.preventDefault();
        if (evt.target.className === "dot")
            clickDot(evt.target);
    });
    new Search();

    activateAnimation();
    getEventCategories();

}

initialize(init);

function getEventCategories() {
    getManager({
        url: "/eventcategories",
        method: "GET",
        callback: drawEventCategories
    })

}

function drawEventCategories(result) {
    var template = Handlebars.templates["precompile/event_title_template"];
    for (const category of result) {
        $(".tab-btn-box").innerHTML += template(category);

    }
    $_all(".tab-box .tab-btn-box li a").forEach((v) => {
        v.addEventListener("click", (evt) => {
            evt.preventDefault();
            clickCategoryTab(evt.target);
        })
    });

    initChoiceCategory();
}

function initChoiceCategory() {
    const selectCategories = $_all(".event_category_title");
    const randomCategory = selectCategories[Math.floor(Math.random() * selectCategories.length)];
    randomCategory.classList.toggle("on");
    getCategory(randomCategory.getAttribute("type"));
}

function getCategory(categoryId) {
    getManager({
        url: "/eventcategories/" + categoryId,
        method: "GET",
        callback: drawProducts
    })
}

function drawProducts(result) {
    $(".tab-content-box").innerHTML = "";
    var template = Handlebars.templates["precompile/product_template"];
    const products = result.products;
    for (const product of products) {
        $(".tab-content-box").innerHTML += template(product);
    }
}

function clickCategoryTab(target) {
    $(".tab-box .tab-btn-box li.on").classList.toggle("on");
    const categoryId = target.getAttribute("data-category-id");
    $("#event_category_title-" + categoryId).classList.toggle("on");
    getCategory(categoryId);
}