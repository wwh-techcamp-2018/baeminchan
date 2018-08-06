document.addEventListener("DOMContentLoaded", () => {
    initEvents();
    $("#best-categories").addEventListener("click", onClickBestCategory);
});

function onClickBestCategory(evt) {
    fetchManager({
        url: '/api/bestCategories/' + evt.target.dataset["categoryId"],
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccessBestSideDishes,
        errCallback: alertError
    });
}

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
            return prev + `<li><a data-category-id=${next.id}>${next.name}</a></li>`;
        }, ``);


        $("#best-categories").insertAdjacentHTML("beforeend", html);
    });
}

function onSuccessBestSideDishes(response) {
    response.json().then((result) => {
        const html = result.reduce((prev, next) => {
            return prev + `<li>
                  <a class="thumbnail-box" href="#">
                    <div class="thumbnail">
                      <img src="./img/img-best-dish.jpg" alt="[집밥의완성] 1월 제철박스" />
                      <div class="overlay">
                        <p class="txt">새벽배송</p>
                        <p class="txt">전국택배</p>
                      </div>
                      <div class="badge-wrapper">
                        <i class="bm-icon badge-best">베스트</i>
                      </div>
                    </div>

                    <dl class="content">
                      <dt class="title">${next.name}</dt>
                      <dd class="desc">둘이서 한 끼 먹기 딱 좋아요</dd>
                      <dd class="price-wrapper">
                        <span class="original-price">22,900</span>
                        <span class="final-price">
                          <span class="number">20,500</span>
                          <span class="unit">원</span>
                        </span>
                      </dd>
                    </dl>
                  </a>
                </li>`;
        }, ``);
        $("#best-side-dish-box").innerHTML = '';
        $("#best-side-dish-box").insertAdjacentHTML("beforeend", html);
    });
}

function alertError() {
    alert("요기요를 이용해주세요...죄송합니다.");
}