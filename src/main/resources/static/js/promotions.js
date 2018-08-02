function $(selector) {
    return document.querySelector(selector);
}

function getCurrentPromotion() {
    return document.querySelector('#main-visual .img-box .current')
}

function onClickNextButton() {
    moveToRight(getCurrentPromotion());
}

function onClickPreButton() {
    moveToLeft(getCurrentPromotion());
}

function moveToLeft(currentPromotion) {
    currentPromotion.classList.remove("current");
    const newCurrentPromotion = currentPromotion.previousElementSibling || currentPromotion.parentElement.lastElementChild;
    newCurrentPromotion.classList.add("current");
    return newCurrentPromotion;
}

function moveToRight(currentPromotion) {
    currentPromotion.classList.remove("current");
    const newCurrentPromotion = currentPromotion.nextElementSibling || currentPromotion.parentElement.firstElementChild;
    newCurrentPromotion.classList.add("current");
    return newCurrentPromotion;
}

function loopPromotions(currentPromotion) {
    setTimeout(() => {
    		const newCurrentPromotion = moveToRight(currentPromotion);
    		loopPromotions(newCurrentPromotion);
        }, 3000);
}

function showPromothins() {
    const firstPromotion = $('#main-visual .img-box .img-1');
    firstPromotion.classList.add("current");
    loopPromotions(firstPromotion);
}

document.addEventListener("DOMContentLoaded", () => {
    showPromothins();
})




