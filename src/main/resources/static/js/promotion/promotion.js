document.addEventListener("DOMContentLoaded", onLoadDocument);

const CLASS_NAME_CUR = "current";
const CLASS_NAME_DOT_ON = "on";

const dotList = Array.from($("#dot-box").children);

let currentPromotion = $("#slide-box").firstElementChild;
let currentDot = $("#dot-box").firstElementChild;

let interval;

function onLoadDocument() {
    $("#slide-next-arrow-btn").addEventListener("click", onClickNextButton);
    $("#slide-prev-arrow-btn").addEventListener("click", onClickPrevButton);
    $("#dot-box").addEventListener("click", onClickDotButton);
    autoSlide();
}

function onClickNextButton() {
    clearInterval(interval);
    moveNextPromotion(currentPromotion.nextElementSibling || currentPromotion.parentNode.firstElementChild);
    moveNextDot();
    autoSlide();
}

function onClickPrevButton() {
    clearInterval(interval);
    moveNextPromotion(currentPromotion.previousElementSibling || currentPromotion.parentNode.lastElementChild);
    moveNextDot();
    autoSlide();
}

function autoSlide() {
    interval = setInterval(function () {
        moveNextPromotion(currentPromotion.nextElementSibling || currentPromotion.parentNode.firstElementChild);
        moveNextDot();
    }, 3000);
}

function onClickDotButton(e) {
    const index = dotList.indexOf(e.target);
    if (index === -1) return;

    currentDot.classList.toggle(CLASS_NAME_DOT_ON);
    currentDot = e.target;
    currentDot.classList.toggle(CLASS_NAME_DOT_ON);

    moveNextPromotion(currentPromotion.parentNode.children[index]);
}

function moveNextPromotion(nextPromotion) {
    currentPromotion.classList.toggle(CLASS_NAME_CUR);
    currentPromotion = nextPromotion || currentPromotion.nextElementSibling || currentPromotion.parentNode.firstElementChild;
    currentPromotion.classList.toggle(CLASS_NAME_CUR);
}

function moveNextDot() {
    currentDot.classList.toggle(CLASS_NAME_DOT_ON);
    currentDot = currentDot.nextElementSibling || currentDot.parentNode.firstElementChild;
    currentDot.classList.toggle(CLASS_NAME_DOT_ON);
}