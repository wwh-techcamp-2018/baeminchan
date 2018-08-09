import { AutoSearching } from "./AutoSearching.js";
const ENTER = 13;
const CLASS_INVISIBLE = "invisible";
const CLASS_NAME_NOW  = "now";
const SIDE_DISHES_CACHE = {};

function requestCategories() {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccess,
        errCallback: alertError
    });

    fetchManager({
        url: '/api/bestCategories',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccessBestCategories,
        errCallback: alertError
    });
}

function onSuccess(response) {
    response.json().then((response) => {
        response.forEach((category) => {
            const parentCategoryHTML = `<li><a href="./side-dishs.html">${category.name}</a><ul class="sub-menu">`;
            const subCategoriesHTML = category.subCategories.reduce((prevSubCategory, nextSubCategory) => { return prevSubCategory + `<li><a href="#">${nextSubCategory.name}</a></li>`}, ``);
            const closingHTML = `</ul></li>`;
            $("#menu").insertAdjacentHTML("beforeend", parentCategoryHTML + subCategoriesHTML + closingHTML);
        });
    });
}

function onSuccessBestCategories(response) {
    response.json().then((result) => {
        const html = result.reduce((prev, next) => {
            return prev + `<li><a class="best-category-a" data-category-id=${next.id}>${next.name}</a></li>`;
        }, ``);

        $("#best-categories").insertAdjacentHTML("beforeend", html);

        const randomNumber = (generateRandomNumber(0, result.length - 1));
        loadSideDishes(randomNumber);
        $("#best-categories").children[randomNumber].querySelector("a").classList.toggle(CLASS_NAME_NOW);
    });
}

function onClickBestCategory({target}) {
    $(".now").classList.toggle(CLASS_NAME_NOW);
    target.classList.toggle(CLASS_NAME_NOW);
    if(!SIDE_DISHES_CACHE[target.dataset["categoryId"]])
        loadSideDishes(target.dataset["categoryId"]);
    else
        updateSideDishes(SIDE_DISHES_CACHE[target.dataset["categoryId"]]);
}

function loadSideDishes(categoryId) {
    fetchManager({
        url: '/api/bestCategories/' + categoryId,
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccessBestSideDishes,
        errCallback: alertError
    });
}

function onSuccessBestSideDishes(response) {
    response.json().then((result) => {
        SIDE_DISHES_CACHE[$(".now").dataset["categoryId"]] = result;
        updateSideDishes(result);
    });
}

function updateSideDishes(result) {
    const html = getSideDishesTemplate(result);
    $("#best-side-dish-box").innerHTML = '';
    $("#best-side-dish-box").insertAdjacentHTML("beforeend", html);
}

function getSideDishesTemplate(sideDishes) {
    return sideDishes.reduce((prev, next) => {
        return prev + `<li>
                  <a class="thumbnail-box" href="#">
                    <div class="thumbnail">
                      <img src="./img/img-best-dish.jpg" alt=[${next.brand.name}]${next.name} />
                      <div class="overlay">
                        <p class="txt">새벽배송</p>
                        <p class="txt">전국택배</p>
                      </div>
                      <div class="badge-wrapper">
                        <i class="bm-icon badge-best">베스트</i>
                      </div>
                    </div>

                    <dl class="content">
                      <dt class="title">[${next.brand.name}]${next.name}</dt>
                      <dd class="desc">${next.description}</dd>
                      <dd class="price-wrapper">
                        <span class="original-price">${next.price}</span>
                        <span class="final-price">
                          <span class="number">${next.salePrice}</span>
                          <span class="unit">원</span>
                        </span>
                      </dd>
                    </dl>
                  </a>
                </li>`;
    }, ``);
}

function alertError() {
    alert("요기요를 이용해주세요...죄송합니다.");
}

document.addEventListener("DOMContentLoaded", () => {
    const autoSearching = new AutoSearching();
    requestCategories();
    addEventListeners(autoSearching);
});

function addEventListeners(autoSearching) {
    document.onkeydown = function(event) {
        if ($("." + CLASS_INVISIBLE) == null && event.keyCode === ENTER) event.preventDefault();
    };

    document.onclick = function({target}) {
        if(target.parentNode.id === "searching_box") {
            $("#searching_text").value = autoSearching.removeSpanTag(target.innerHTML);
        }
        $("#searching_box").classList.add(CLASS_INVISIBLE);
    };
    $("#best-categories").addEventListener("click", onClickBestCategory);
    $("#searching_text").addEventListener("keyup", autoSearching.onKeyUpSearchForm.bind(autoSearching));
}