function $(selector) {
    return document.querySelector(selector);
}

let loopInterval;
const RIGHT = 1;
const LEFT = -1;
const CURRENT = "current";
const ON = "on";

function clearLoopInterval() {
    window.clearInterval(loopInterval);
}

function getCurrentPromotion() {
    return $('#main-visual .img-box .current');
}

function getCurrentDot() {
    return $('#main-visual .dot-btn-box .on');
}

function getDotByIndex(index) {
    return $('#main-visual .dot-btn-box').children[index-1];
}

function getPromotionByIndex(index) {
    return $('#main-visual .img-box').children[index-1];
}

function onClickNextButton() {
    move(RIGHT);
}

function onClickPreButton() {
    move(LEFT);
}

function onClickDot(index) {
    const currentPromotion = getCurrentPromotion();
    const currentDot = getCurrentDot();
    const newCurrentPromotion = getPromotionByIndex(index);
    const newCurrentDot = getDotByIndex(index);

    toggleOnAndCurrent([currentDot, currentPromotion]);
    toggleOnAndCurrent([newCurrentDot, newCurrentPromotion]);

    clearLoopInterval();
    loopPromotions();
}

function move(direction) {
    const currentPromotion = getCurrentPromotion();
    const currentDot = getCurrentDot();

    toggleOnAndCurrent([currentDot, currentPromotion]);

    let newCurrentPromotion;
    let newCurrentDot;

    if(direction == RIGHT) {
        newCurrentPromotion = currentPromotion.nextElementSibling || currentPromotion.parentElement.firstElementChild;
        newCurrentDot = currentDot.nextElementSibling || currentDot.parentElement.firstElementChild;
    } else {
        newCurrentPromotion = currentPromotion.previousElementSibling || currentPromotion.parentElement.lastElementChild;
        newCurrentDot = currentDot.previousElementSibling || currentDot.parentElement.lastElementChild;
    }

    toggleOnAndCurrent([newCurrentDot, newCurrentPromotion]);

    clearLoopInterval();
    loopPromotions();
}

function loopPromotions() {
    loopInterval = window.setInterval(() => {
    		move(RIGHT);
        }, 3000);
}

function toggleCurrent(promotion) {
    promotion.classList.toggle(CURRENT);
}

function toggleOn(dot) {
    dot.classList.toggle(ON);
}

function toggleOnAndCurrent(dotAndPromotion) {
    toggleOn(dotAndPromotion[0]);
    toggleCurrent(dotAndPromotion[1]);
}

function showPromotions() {
    const firstPromotion = $('#main-visual .img-box .img-1');
    const firstDot = $('#main-visual .dot-btn-box .dot');
    toggleOnAndCurrent([firstDot, firstPromotion]);
    loopPromotions();
}

document.addEventListener("DOMContentLoaded", () => {
    showPromotions();
})




