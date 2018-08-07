document.addEventListener("DOMContentLoaded", () => {
    initBestCategories(getBestCategoryCallback, failGetBestCategoryCallback);
})

function initBestCategories(successCallback, failCallback) {
    var tabBtnBox = $('.tab-btn-box');
    tabBtnBox.addEventListener('click', clickBestProductTab);

    fetchManager({
        url: 'api/best-categories',
        method: 'GET',
        headers: {'content-type': 'application/json'},
        body: {},
        onSuccess: successCallback,
        onFailure: failCallback
    })
}

function clickBestProductTab(evt) {
    evt.preventDefault();
    var currentNo = Array.prototype.indexOf.call($('.tab-btn-box').children, evt.target.closest('li'));
    bestProductToggle(currentNo);
}

function bestProductToggle(currentNo) {
    var prevBestCategoryNodes = $All('#best-seller .on');
    prevBestCategoryNodes.forEach((node) => node.classList.toggle("on"));

    var selectBestCategoryNodes = $All('.best-category-' + currentNo);
    selectBestCategoryNodes.forEach((node) => node.classList.toggle("on"));
}

function getBestCategoryCallback(bestCategoryList) {
    bestCategoryList.forEach((bestCategory, index) => {
        let name = bestCategory.name;
        let products = bestCategory.products;

        loadName(name, index);
        loadProducts(products, index);
    });

    var firstBestCategoryNodes = $All('.best-category-0');
    firstBestCategoryNodes.forEach((node) => node.classList.toggle("on"));
}

function loadName(name, index) {
    var tabBtnBox = $('.tab-btn-box');
    let html = `<li class='best-category-${index}'><a> ${name} </a></li>`;
    tabBtnBox.insertAdjacentHTML("beforeend", html);
}

function loadProducts(products, index) {
    var tabContentGroupBox = $('.tab-content-group-box');
    let html = `<li class='best-category-${index}'><ul class="tab-content-box">`;
    products.forEach((product) => {
        html += loadProduct(product);
    });
    html += '</ul></li>';

    tabContentGroupBox.insertAdjacentHTML("beforeend", html);
}

function loadProduct(product) {
    let finalPrice = getFinalPrice(product);
    let html = `<li>
                  <a class="thumbnail-box" href="#">
                    <div class="thumbnail">
                      <img src="${product.bannerUrls[0]}" alt="[집밥의완성] 1월 제철박스" />
                      <div class="overlay">
                        <p class="txt">새벽배송</p>
                        <p class="txt">전국택배</p>
                      </div>
                      <div class="badge-wrapper">
                        <i class="bm-icon badge-event">이벤트특가</i>
                      </div>
                    </div>

                    <dl class="content">
                      <dt class="title">${product.name}</dt>
                      <dd class="desc">둘이서 한 끼 먹기 딱 좋아요. 둘이서 한 끼 먹기 딱 좋아요. 둘이서 한 끼 먹기 딱 좋아요</dd>
                      <dd class="price-wrapper">
                        <span class="original-price">${product.price}</span>
                        <span class="final-price">
                          <span class="number">${finalPrice}</span>
                          <span class="unit">원</span>
                        </span>
                      </dd>
                    </dl>
                  </a>
                </li>`;

    return html;
}

function getFinalPrice(product) {
    return parseInt(product.price) * (1 - parseInt(product.discountRate));
}

function failGetBestCategoryCallback() {
    alert('best category fail!');
}