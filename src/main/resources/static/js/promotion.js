document.addEventListener("DOMContentLoaded", () => {
    initPromotionEvents();
})

function initPromotionEvents() {
    loadPromotion();
    const dotBtnBox = $(".dot-btn-box");
    dotBtnBox.addEventListener("click", dotClickHandler);
    const slideArrowList = document.querySelectorAll(".spr-btn-arrow-main-slide");
    slideArrowList.forEach((arrow) => { arrow.addEventListener("click", arrowClickHandler)});
}

function loadPromotion() {
    fetchManager({
            url: "/api/promotion",
            method: 'GET',
            headers: {'content-type': 'application/json'},
            onSuccess: promotionCallback,
    });
}

function promotionCallback(response) {
    response.json().then((promotionList) => setPromotion(promotionList));
}

function setPromotion(promotionList) {
    const imgBox = $(".img-box");
    const dotBox = $(".dot-btn-box");
    promotionList.forEach((promotion) => {
        console.log(promotion);
    	imgBox.insertAdjacentHTML('beforeend', promotionTemplateWriter(promotion.bannerUrl, promotion.description));
        dotBox.insertAdjacentHTML('beforeend', dotTemplateWriter());
    });
    imgBox.firstElementChild.classList.add('current');
    dotBox.firstElementChild.classList.add('on');
}

function dotTemplateWriter() {
    return `<a class="dot" href="#"></a>`;
}


function promotionTemplateWriter(bannerUrl, description) {
    return `<li class="img-item img" style="background-image: url('${bannerUrl}');">
              <a href="#">
                <span class="blind">${description}</span>
              </a>
            </li>`;
}

function dotClickHandler(evt) {
    evt.preventDefault();
    if (evt.target.className !== 'dot') {
        return;
    }
    
    switchClass('on', evt.target);
    switchClass('current', $('.img-box').children[getOrder(evt.target)]);
}

function arrowClickHandler(evt) {
    evt.preventDefault();
    const arrow = evt.target;
    let changeElement;
    if (arrow.className.includes("prev")) {
        changeElement = getPreviousSibling("img-box", $(".current"));
    }

    if (arrow.className.includes("next")) {
        changeElement = getNextSibling("img-box", $(".current"));
    }
    switchClass('current', changeElement);
    switchClass('on', $('.dot-btn-box').children[getOrder(changeElement)]);
}

function getPreviousSibling(parentClassName, targetElement) {
    let siblingElement = targetElement.previousElementSibling
    if (!siblingElement) {
        siblingElement = $("." + parentClassName).lastElementChild;
    }
    return siblingElement;
}

function getNextSibling(parentClassName, targetElement) {
    let siblingElement = targetElement.nextElementSibling
    if (!siblingElement) {
        siblingElement = $("." + parentClassName).firstElementChild;
    }
    return siblingElement;
}



function getOrder(element) {
    return Array.prototype.indexOf.call(element.parentElement.children, element);
}


function switchClass(className, element) {
    $("." + className).classList.remove(className);
    element.classList.add(className);
}