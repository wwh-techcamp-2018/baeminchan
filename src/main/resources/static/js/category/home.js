const CLASS_NAME_NOW = "now";
const side_dishes_cache = {};

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
    $("#best-categories").addEventListener("click", onClickBestCategory);
});

function initEvents() {
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
            // 추가
            const html1 = `<li>
                              <a href="./side-dishs.html">` + category.name + `</a>
                              <ul class="sub-menu">`;

            const html2 = category.subCategories.reduce((prevSubCategory, nextSubCategory) => { return prevSubCategory + `<li><a href="#">` + nextSubCategory.name + `</a></li>`}, ``);

            const html3 = `</ul></li>`;
            $("#menu").insertAdjacentHTML("beforeend", html1 + html2 + html3);
        });
    });
}

function onSuccessBestCategories(response) {
    response.json().then((result) => {
        const html = result.reduce((prev, next) => {
            return prev + `<li><a class="best-category-a" data-category-id=${next.id}>${next.name}</a></li>`;
        }, ``);

        $("#best-categories").insertAdjacentHTML("beforeend", html);

        const randomNumber = (generateRandomNumber(1, result.length));
        loadSideDishes(randomNumber);
        $("#best-categories").children[randomNumber - 1].querySelector("a").classList.toggle(CLASS_NAME_NOW);
    });
}

function onClickBestCategory(evt) {
    let {"target" : target} = evt;
    $(".now").classList.toggle(CLASS_NAME_NOW);
    target.classList.toggle(CLASS_NAME_NOW);
    if(!side_dishes_cache[target.dataset["categoryId"]])
        loadSideDishes(target.dataset["categoryId"]);
    else
        updateSideDishes(side_dishes_cache[target.dataset["categoryId"]]);
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
        side_dishes_cache[$(".now").dataset["categoryId"]] = result;
        updateSideDishes(result);
    });
}

function updateSideDishes(result) {
    const html = getSideDishesTemplete(result);
    $("#best-side-dish-box").innerHTML = '';
    $("#best-side-dish-box").insertAdjacentHTML("beforeend", html);
}

function getSideDishesTemplete(sideDishes) {
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

function generateRandomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}