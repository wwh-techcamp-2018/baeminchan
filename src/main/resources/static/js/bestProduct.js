let bestProducts;
let bestProductContents = {};

const bestBtnBoxSelector = '#best-seller .tab-btn-box';

document.addEventListener('DOMContentLoaded', () => {
    loadBestProductTitles();
    addEvent(bestBtnBoxSelector, 'click', onClickBestProductTitle);
    
});

function templateBestProductTitle(e) {
    return `<li><a data-category-id=${e.id} href="#">${e.title}</a></li>`
}

function updateBestProductTitles({data}){
    $(bestBtnBoxSelector).innerHTML = data
        .map(templateBestProductTitle)
        .join('\n');
    
    loadBestProductContents($(`a[data-category-id="${data[0].id}"]`));
}

function loadBestProductTitles() {
    new FetchManager({
        url: "/api/bestproducts",
        method: "GET"
    })
    .onSuccess(updateBestProductTitles)
    .onFailed((errors) => {})
    .doFetch();
}

function templateBestProduct({title, description, originalPrice, discountPercent, images}) {
    return `<li>
    <a class="thumbnail-box" href="#">
      <div class="thumbnail">
        <img src="${images[0]}" alt="[집밥의완성] 1월 제철박스" />
        <div class="badge-wrapper">
          <i class="bm-icon badge-event">이벤트특가</i>
        </div>
      </div>

      <dl class="content">
        <dt class="title">${title}</dt>
        <dd class="desc">${description}</dd>
        <dd class="price-wrapper">
          <span class="original-price">${originalPrice}</span>
          <span class="final-price">
            <span class="number">${originalPrice * discountPercent / 100}</span>
            <span class="unit">원</span>
          </span>
        </dd>
      </dl>
    </a>
  </li>`;
}

function drawBestProducts(products) {
    $('.tab-content-box').innerHTML = products.map(templateBestProduct).join('\n')
}

function onClickBestProductTitle(evt) {
    evt.preventDefault();

    if (evt.target.tagName != "A") {
        return;
    }

    loadBestProductContents(evt.target);
} 

function loadBestProductContents(target) {
    // 있으면
    const category_id = target.getAttribute('data-category-id');
    if (bestProductContents[category_id]) {
        return drawBestProducts(bestProductContents[category_id]);
    }
    
    // 없으면
    new FetchManager({
        url: "/api/bestproducts/" + category_id,
        method: "GET"
    })
    .onSuccess(({data}) => {
        bestProductContents[category_id] = data;
        drawBestProducts(data);
    })
    .onFailed((errors) => {})
    .doFetch();
}