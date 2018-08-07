function $(selector) {
    return document.querySelector(selector);
}

function appendCategories(categories) {
   let categoryHtml = ``;

    categories.forEach(function(mainCategory) {
        categoryHtml += `<li><a href="./side-dishs.html">` + mainCategory.name + `</a><ul class="sub-menu">`;

        mainCategory.subCategories.forEach(function(subCategory) {
                categoryHtml += `<li><a href="#">`;
                categoryHtml += subCategory.name
                categoryHtml += `</a></li>`
            });

        categoryHtml += `</ul></li>`;
    });

    $("#categoryMenu").insertAdjacentHTML("afterbegin", categoryHtml);
}

function renderSideDish(prevDish, currDish) {
            return prevDish + `
                          <li>
                            <a class="thumbnail-box" href="#">
                                <div class="thumbnail">
                                    <img src="https://cdn.bmf.kr/_data/product/I4DEC/ebab7a5c6f31b59c1d0ffda25f0c82a3.jpg" alt="${currDish.name}" />
                                    <div class="overlay">
                                        <p class="txt">새벽배송</p>
                                        <p class="txt">전국택배</p>
                                    </div>
                                    <div class="badge-wrapper">
                                        <i class="bm-icon badge-event">이벤트특가</i>
                                    </div>
                                </div>

                                <dl class="content">
                                    <dt class="title">${currDish.name}</dt>
                                    <dd class="desc">${currDish.description}</dd>
                                    <dd class="price-wrapper">
                                        <span class="original-price">${currDish.price}</span>
                                        <span class="final-price">
                                          <span class="number">${currDish.salePrice}</span>
                                          <span class="unit">원</span>
                                        </span>
                                    </dd>
                                </dl>
                            </a>
                        </li>
                                        `;

            }

function appendBestCategories(bestcategories) {
    var bestCategoryHtml= '';
    var sideDishHtml = '';

    bestcategories.forEach(function(bestcategory) {
        bestCategoryHtml += `<li><a data-category-id="${bestcategory.id}" href="#">${bestcategory.name}</a>`;
        bestCategoryHtml += `</li>`;

        sideDishHtml += `<li class="" id="bestMenu-${bestcategory.id}"><ul class="tab-content-box">`;
        sideDishHtml += bestcategory.sideDishes.reduce(renderSideDish, '');
        sideDishHtml += `</ul></li>`;
    });

    $("#bestCategoryBox").insertAdjacentHTML("beforeend", bestCategoryHtml);
    $("#bestCategorySideDishBox").insertAdjacentHTML("beforeend", sideDishHtml);

    makeOnByIndex(makeRandomNum());
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        return response.json()
    }).then((result) => {
        callback(result)
    })
}

function renderCategories() {
    fetchManager({
            url: '/api/categories',
            method: 'GET',
            headers: { 'content-type': 'application/json'},
            callback: appendCategories
        })
}

function renderBestCategories() {
    fetchManager({
            url: '/api/best/categories',
            method: 'GET',
            headers: { 'content-type': 'application/json'},
            callback: appendBestCategories
        })
}

function makeRandomNum() {
    return Math.floor(Math.random() * 5);
}

function deleteOnAll(){
    Array.from($('#bestCategorySideDishBox').children).forEach((child) => child.classList.remove("on"));
    Array.from($('#bestCategoryBox').children).forEach((child) => child.classList.remove("on"));
}

function makeOnByIndex(index){
    $('#bestCategorySideDishBox').children[index].classList.add("on");
    $('#bestCategoryBox').children[index].classList.add("on");
}

document.addEventListener("DOMContentLoaded", () => {
    init();
    renderCategories();
    renderBestCategories();
})

function init() {
    $("#bestCategoryBox").addEventListener("click", (e) => {
        e.preventDefault();
        const index = e.target.getAttribute("data-category-id");
        deleteOnAll();
        makeOnByIndex(index-1);
    });
}

function onClickLoginButton(form) {
    if(!checkValidForLogin(form)) return;

    fetchManager({
        url: '/api/users/login',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({
            "userId": form.userId.value,
            "password": form.password.value
        }),
        callback: goHome,
        errorCallback: onError
    })
}