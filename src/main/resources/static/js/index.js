function createCategoryDomString(e) {
    function brandClass() {
        return e.title === '브랜드관' ? 'class="brand" ' : '';
    }

    return `<li ${brandClass()}data-id="${e.id}"><a href="side-dishs.html">${e.title}</a><ul class="sub-menu"></ul></li>`;
}

function createBestProductTitleDomString(e) {
    return `
    <li>
        <a data-category-id="${e.id}" href="#">${e.name}</a>
    </li>
    `
}

function changeBestProductContent(productData, index) {
    const origin = $All('.thumbnail-box')[index];
    const {
        title, originalPrice,
        discountPercent, description, images
    } = productData;

    const image = images[0];

    origin.querySelector('img').src = image;
    origin.querySelector('img').alt = title;
    origin.querySelector('.title').textContent = title;
    origin.querySelector('.desc').textContent = description;
    origin.querySelector('.original-price').textContent = originalPrice;
    origin.querySelector('.final-price > .number').textContent = originalPrice - originalPrice * discountPercent / 100;
}

function initializeTopCategories() {
    fetchManager({
        url: "/api/category",
        method: "GET",
        onSuccess: ({json}) => {
            json.data.forEach((e) => {
                const menu = createCategoryDomString(e);
                $("#gnb ul.menu").insertAdjacentHTML("beforeend", menu);
            });
        }
    });

    $('#gnb ul.menu').addEventListener('mouseover', (e) => {
        const pNode = e.target.parentNode;
        if (pNode.hasAttribute("data-id")) loadSubMenus(pNode);
    });
}

function initializeBestProducts() {
    fetchManager({
        url: "/api/bestProducts",
        method: "GET",
        onSuccess: ({json}) => {
            json.data.forEach((e) => {
                const name = createBestProductTitleDomString(e);
                $(".tab-btn-box").insertAdjacentHTML('beforeend', name);
            });
            $All(".tab-btn-box > li > a")[Math.floor(Math.random() * json.data.length)].click();
        }
    });

    $(".tab-btn-box").addEventListener('click',loadBestProduct);
}

function loadBestProduct(evt) {
    console.log("called");
    const target = evt.target;
    if (!target.hasAttribute("data-category-id")) return;

    fetchManager({
        url: `/api/bestProducts/${target.getAttribute("data-category-id")}`,
        method: "GET",
        onSuccess: ({json}) => {
            json.data.forEach((product, index) => {
                changeBestProductContent(product, index);
            });
        }
    });
}

function loadSubMenus(pNode) {
    if (pNode.lastChild.innerHTML !== '') return;

    fetchManager({
        url: "/api/category/" + pNode.getAttribute("data-id"),
        method: "GET",
        onSuccess: ({json}) => {
            pNode.lastChild.innerHTML = '';
            json.data.forEach((c) => {
                let subMenu = `<li><a href="#">${c.title}</a></li>`;
                pNode.lastChild.insertAdjacentHTML("beforeend", subMenu);
            });
        }
    });
}

function initializePromotions() {
    $All('.img-item')[0].classList.add('shown');
    $All('.dot')[0].classList.add('on');

    $('.bm-icon.spr-btn-arrow-main-slide.prev').addEventListener('click', prevPage);
    $('.bm-icon.spr-btn-arrow-main-slide.next').addEventListener('click', nextPage);
    $All('.dot').forEach(dot => dot.addEventListener('click', jumpPage));
}

function getCurrentPromotionIdx() {
    let idx = -1;
    getPromotions().forEach((e, promotionIdx) => {
        if (e.classList.contains("shown")) {
            idx = promotionIdx;
        }
    });
    return idx;
}

function getClickedDotIndex(targetDot) {
    let idx = 0;
    $All(".dot").forEach((dot, dotIndex) => {
        if (dot === targetDot) {
            idx = dotIndex;
        }
    });
    return idx;
}

function getPromotionCount() {
    return getPromotions().length;
}

function getPromotions() {
    return $All('.img-item');
}

function getDots() {
    return $All('.dot');
}

function nextPage() {
    changePhase((getCurrentPromotionIdx() + 1) % getPromotionCount());
}

function prevPage() {
    const currentIndex = getCurrentPromotionIdx();
    changePhase(currentIndex - 1 < 0 ? getPromotionCount() - 1 : currentIndex - 1);
}


function jumpPage(e) {
    changePhase(getClickedDotIndex(e.target));
}

function changePhase(targetIndex) {
    const promotions = getPromotions();
    const currentIndex = getCurrentPromotionIdx();
    const dots = getDots();
    promotions[currentIndex].classList.toggle("shown");
    promotions[targetIndex].classList.toggle("shown");
    dots[currentIndex].classList.toggle("on");
    dots[targetIndex].classList.toggle("on");
}

document.addEventListener('DOMContentLoaded', () => {
    initializeTopCategories();
    initializePromotions();
    initializeBestProducts();
});
