const cached = {};

const menu = $("#gnb .menu");
const eventMenu = $(".tab-btn-box");

function initCategories() {
    menu.addEventListener('mouseover', showSubCategories);
    eventMenu.addEventListener('click', handleEventBanchanClicked);

    loadMainCategories();
    loadEventCategories();
}

function loadMainCategories() {
    fetch('/categories')
        .then(validateResponse)
        .then(({data}) => {
            menu.innerHTML += data.map(category => categoryTemplate(category, true)).join('');
        })
        .catch(handleError);
}

function loadEventCategories() {
    fetch('/categories/event')
        .then(validateResponse)
        .then(({data}) => {
            eventMenu.innerHTML += data.map(eventCategoryTemplate).join('');
            return parseInt(Math.random() * data.length) + 1;
        })
        .then(showEventBanchan)
        .catch(handleError);
}

function handleEventBanchanClicked(e) {
    e.preventDefault();

    const {target} = e;
    const id = target.closest('li').id;
    const eventCategoryId = id.split('-').pop();

    showEventBanchan(eventCategoryId);
}

function showEventBanchan(eventCategoryId) {
    $(`#event-category-${eventCategoryId} > a`).focus();

    if ($(`#banchan-list-${eventCategoryId}`)) {
        return toggleBanchanList(eventCategoryId);
    }

    fetch(`/categories/event/${eventCategoryId}/banchans`)
        .then(validateResponse)
        .then(({data}) => {
            $('.tab-content-group-box').innerHTML += banchanListTemplate(data, eventCategoryId);
            return eventCategoryId;
        })
        .then(toggleBanchanList)
        .catch(handleError);
}

function toggleBanchanList(eventCategoryId) {
    const onBox = $('.tab-content-group-box .on');
    onBox && onBox.classList.remove('on');

    const banchanList = $(`#banchan-list-${eventCategoryId}`);
    if (banchanList) {
        banchanList.classList.add('on');
    }
}

function showSubCategories(e) {
    const {target} = e;
    const id = target.closest('li').id;
    const category_id = id.split('-').pop();

    if (cached[category_id]) {
        return;
    }

    fetch(`/categories/${category_id}`)
        .then(validateResponse)
        .then(({data}) => {
            cached[category_id] = true;
            $(`#${id} .sub-menu`).innerHTML += data.map(category => categoryTemplate(category, false)).join('');
        })
        .catch(handleError);
}

function categoryTemplate({id, name}, has_submenu) {
    return `<li id="category-${id}">
                <a>${name}</a>
                ${has_submenu ? '<ul class="sub-menu"></ul>' : ''}
            </li>`;
}

function eventCategoryTemplate({id, name}) {
    return `<li id="event-category-${id}">
              <a href="#">${name}</a>
            </li>`;
}

function banchanListTemplate(data, eventCategoryId) {
    const banchanData = data.slice(0, 3);
    return `<li id="banchan-list-${eventCategoryId}">
              <ul class="tab-content-box">
                ${banchanData && banchanData.map(banchanThumbnailTemplate).join('')}
              </ul>
            </li>`;
}

function banchanThumbnailTemplate({id, title, subTitle, brand, originalPrice, salesRate, badges}) {
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
                  <dd class="desc">${subTitle}</dd>
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

function validateResponse(response) {
    if (!response.ok) {
        throw new Error("페이지 로딩중에 에러가 발생했습니다. 새로고침 해주세요.");
    }
    return response.json();
}

function handleError(err) {
    console.error(err);
}

document.addEventListener('DOMContentLoaded', initCategories);
