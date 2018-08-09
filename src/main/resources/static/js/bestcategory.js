
function initBestCategoryEvents(){
    $('.tab-btn-box').addEventListener('click', clickBestCategory);

    fetchManager({
        url: "/category/best",
        method: "get",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        success: fetchBestCategorySuccess,
        error: failRequest
    })
}

document.addEventListener("DOMContentLoaded", () => {
    initBestCategoryEvents();
})

function clickBestCategory(e){
    e.preventDefault();

    const element = e.target;

    // attribute 접근 방법 2가지
    console.log(element.getAttribute("data-category-id"));
    fetchProducts(element.getAttribute("data-category-id"));
}

function fetchProducts(bestCategoryId){
    fetchManager({
        url: `/category/best/${bestCategoryId}/products`,
        method: "get",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        success: fetchProductsSuccess,
        error: failRequest
    })
}

function fetchProductsSuccess(response){
    if(response.status == 200) {
        renderProducts(response);
    }
}
function renderProducts(response){
    let resultHTML = '';
    response.json()
        .then(({data}) => {data.forEach(product => {
            resultHTML += banchanThumbnailTemplate(product);
        }) // forEach

        $('.tab-content-box').innerHTML = resultHTML;
    });
}

function banchanThumbnailTemplate({id, title, subtitle, brand, originalPrice, salesRate, badges}) {
    const titleText = `${brand && '[' + brand + ']'} ${title}`;
    const finalPriceText = `${(salesRate ? parseInt(originalPrice * salesRate / 100) : originalPrice).toLocaleString()}`;

    return `<li>
              <a class="thumbnail-box" href="#">
                <div class="thumbnail">
                  <img src="./img/img-best-dish.jpg" alt="${titleText}">
                  <div class="overlay">
                    <p class="txt">새벽배송</p>
                    <p class="txt">전국택배</p>
                  </div>
                  <div class="badge-wrapper">
                    ${badges && badges.map(badgeTemplate).join('')}
                  </div>
                </div>

                <dl class="content">
                  <dt class="title">${titleText}</dt>
                  <dd class="desc">${subtitle}</dd>
                  <dd class="price-wrapper">
                    ${salesRate ? originalPriceTemplate(originalPrice) : ''}
                    <span class="final-price">
                      <span class="number">${finalPriceText}</span>
                      <span class="unit">원</span>
                    </span>
                  </dd>
                </dl>
              </a>
            </li>`;
}

function originalPriceTemplate(originalPrice) {
    return `<span class="original-price">${originalPrice.toLocaleString()}</span>`;
}

function badgeTemplate({name}) {
    return `<i class="bm-icon badge-event">${name}</i>`;
}

function fetchBestCategorySuccess(response){
    if(response.status == 200) {
        createBestCategory(response);
    }
    // TODO error 처리
}

function failRequest(error){
    alert(error);
}

function createBestCategory(response) {
    let html = ``;
    response.json()
        .then(({data}) => {
            data.forEach(bestCategory => {
            html = html + `
                    <li>
                         <a data-category-id="${bestCategory.id}" href="#">${bestCategory.name}</a>
                    </li>
                    `;
            });

            $(".tab-btn-box").insertAdjacentHTML("afterbegin", html);

            let random = Math.floor((Math.random() * data.length)) + 1;
            fetchProducts(random);
    });
}