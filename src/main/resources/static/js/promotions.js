function $(selector) {
    return document.querySelector(selector);
}

function getCurrentPromotion() {
    return document.querySelector('#main-visual .img-box .current')
}

function getCurrentDot() {
    return document.querySelector('#main-visual .dot-btn-box .on')
}

function getDotByIndex(index) {
    return document.querySelector('#main-visual .dot-btn-box').children[index-1];
}

function getPromotionByIndex(index) {
    return document.querySelector('#main-visual .img-box').children[index-1];
}

function onClickNextButton() {
    let currentPromotion = getCurrentPromotion();
    let currentDot = getCurrentDot();
    console.log("Dot : " + currentDot);
        console.log("Promo : " + currentPromotion);
    moveToRight([currentPromotion, currentDot]);
}

function onClickPreButton() {
    let currentPromotion = getCurrentPromotion();
    let currentDot = getCurrentDot();
    moveToLeft([currentPromotion, currentDot]);
}

function onClickDot(index) {
    let currentDot = getDotByIndex(index);
    let currentPromotion = getPromotionByIndex(index);
    moveToHere([currentPromotion, currentDot]);
}

function moveToHere(currentPromotionAndDot) {
    let prePromotion = getCurrentPromotion();
    let preDot = getCurrentDot();
    let currentPromotion = currentPromotionAndDot[0];
    let currentDot = currentPromotionAndDot[1];

    prePromotion.classList.remove("current");
    currentPromotion.classList.add("current");
    preDot.classList.remove("on");
    currentDot.classList.add("on");
}

function moveToLeft(currentPromotionAndDot) {
    let currentPromotion = currentPromotionAndDot[0];
    let currentDot = currentPromotionAndDot[1];
    currentPromotion.classList.remove("current");
    const newCurrentPromotion = currentPromotion.previousElementSibling || currentPromotion.parentElement.lastElementChild;
    newCurrentPromotion.classList.add("current");
    currentDot.classList.remove("on");
    const newCurrentDot = currentDot.previousElementSibling || currentDot.parentElement.lastElementChild;
    newCurrentDot.classList.add("on");
    return [newCurrentPromotion, newCurrentDot];
}

function moveToRight(currentPromotionAndDot) {
    let currentPromotion = currentPromotionAndDot[0];
    let currentDot = currentPromotionAndDot[1];
    currentPromotion.classList.remove("current");
    const newCurrentPromotion = currentPromotion.nextElementSibling || currentPromotion.parentElement.firstElementChild;
    newCurrentPromotion.classList.add("current");
    currentDot.classList.remove("on");
    const newCurrentDot = currentDot.nextElementSibling || currentDot.parentElement.firstElementChild;
    newCurrentDot.classList.add("on");
    return [newCurrentPromotion, newCurrentDot];
}

function loopPromotions(currentPromotionAndDot) {
    setTimeout(() => {
    		const newCurrentPromotionAndDot = moveToRight(currentPromotionAndDot);
    		loopPromotions(newCurrentPromotionAndDot);
        }, 3000);
}

function showPromotions() {
    const firstPromotion = $('#main-visual .img-box .img-1');
    const firstDot = $('#main-visual .dot-btn-box .dot');
    firstDot.classList.add("on");
    firstPromotion.classList.add("current");
    loopPromotions([firstPromotion, firstDot]);
}

document.addEventListener("DOMContentLoaded", () => {
    showPromotions();
})




