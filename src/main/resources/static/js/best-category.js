document.addEventListener("DOMContentLoaded", () => {
    initBestCategory();
})

function initBestCategory() {
    loadBestCategories();
    $('.tab-btn-box').addEventListener('click', bestCategoryHandler)
}

function loadBestCategories() {
    fetchManager({
        url: "/api/best-categories",
        method: 'GET',
        headers: {'content-type': 'application/json'},
        onSuccess: bestCategoryCallback,
    });
}

function bestCategoryHandler(evt) {
    evt.preventDefault();
    const target = evt.target.parentElement;
    const index = getOrder(target);
    $('.tab-btn-box > .on').classList.toggle('on');
    target.classList.toggle('on');
    $('.tab-content-group-box > .on').classList.toggle('on');
    $('.tab-content-group-box').children[index].classList.toggle('on');
}

function bestCategoryCallback(response) {
    response.json().then((bestCategoryList) => setBestCategory(bestCategoryList));
}

function setBestCategory(bestCategoryList) {
    const tabBtnBox = $(".tab-btn-box");
    const tabContentGroupBox = $(".tab-content-group-box");

    bestCategoryList.forEach(cat => {
        appendBtn(tabBtnBox, cat.categoryName);
        appendContents(tabContentGroupBox, cat.products);
    });

    toggleRandom(tabBtnBox, tabContentGroupBox);
}

function toggleRandom(tabBtnBox, tabContentGroupBox) {
    let random = Math.floor(Math.random() * tabBtnBox.children.length);
    tabContentGroupBox.children[random].classList.toggle('on');
    tabBtnBox.children[random].classList.toggle('on');
}

function appendBtn(tabBtnBox, name) {
    tabBtnBox.insertAdjacentHTML("beforeend",
        `<li>
          <a data-category-id="17011200" href="#">${name}</a>
        </li>`);
}

function appendContents(tabContentGroupBox, products) {
    tabContentGroupBox.insertAdjacentHTML("beforeend",
        `<li>
              <ul class="tab-content-box">

              </ul>
            </li>`
    );

    let tabContentBox = tabContentGroupBox.lastElementChild.firstElementChild;
    products.forEach(p => tabContentBox.insertAdjacentHTML("beforeend",
        `<li>
          <a class="thumbnail-box" href="#">
            <div class="thumbnail">
              <img src="${p.bannerUrls[0]}" alt="[집밥의완성] 1월 제철박스" />
              <div class="overlay">
                <p class="txt">새벽배송</p>
                <p class="txt">전국택배</p>
              </div>
              <div class="badge-wrapper">
                <i class="bm-icon badge-event">이벤트특가</i>
              </div>
            </div>

            <dl class="content">
              <dt class="title">${p.name}</dt>
              <dd class="desc">둘이서 한 끼 먹기 딱 좋아요. 둘이서 한 끼 먹기 딱 좋아요. 둘이서 한 끼 먹기 딱 좋아요</dd>
              <dd class="price-wrapper">
                <span class="original-price">${p.price}</span>
                <span class="final-price">
                  <span class="number">${p.price}</span>
                  <span class="unit">원</span>
                </span>
              </dd>
            </dl>
          </a>
        </li>`));
}
