document.addEventListener("DOMContentLoaded", initEvents);

const ARRAY_LENGTH = 5;
const CLASS_NAME_PREV = "prev";
const CLASS_NAME_NEXT = "next";
const CLASS_NAME_CUR  = "current";

const liList = $("#slide-box").children;
let currentIndex = 0;

function initEvents() {
    $("#slide-next-arrow-btn").addEventListener("click", onClickNextButton);
    $("#slide-prev-arrow-btn").addEventListener("click", onClickPrevButton);
}

function onClickNextButton(e) {
    // 현재가 흐려지고 다음이 환해진다.

    // 이전
    liList[currentIndex].classList.remove(CLASS_NAME_CUR);
    liList[currentIndex].classList.add(CLASS_NAME_PREV);
    liList[(currentIndex === 0) ? ARRAY_LENGTH - 1 : currentIndex - 1].classList.remove(CLASS_NAME_PREV);

    // 현재
    currentIndex = (currentIndex + 1) % ARRAY_LENGTH;
    liList[currentIndex % ARRAY_LENGTH].classList.remove(CLASS_NAME_NEXT);
    liList[currentIndex % ARRAY_LENGTH].classList.add(CLASS_NAME_CUR);
    liList[(currentIndex + 1) % ARRAY_LENGTH].classList.add(CLASS_NAME_NEXT);
}

function onClickPrevButton(e) {
    // 현재가 흐려지고 그 전이 환해진다.
    liList[(currentIndex + 1 + ARRAY_LENGTH) % ARRAY_LENGTH].classList.remove(CLASS_NAME_NEXT);
    liList[(currentIndex + ARRAY_LENGTH) % ARRAY_LENGTH].classList.remove(CLASS_NAME_CUR);
    liList[(currentIndex + ARRAY_LENGTH) % ARRAY_LENGTH].classList.add(CLASS_NAME_NEXT);

    currentIndex = (currentIndex + ARRAY_LENGTH - 1) % 5;
    liList[(currentIndex + ARRAY_LENGTH) % ARRAY_LENGTH].classList.remove(CLASS_NAME_PREV);
    liList[(currentIndex + ARRAY_LENGTH) % ARRAY_LENGTH].classList.add(CLASS_NAME_CUR);
    liList[(currentIndex + ARRAY_LENGTH - 1) % ARRAY_LENGTH].classList.add(CLASS_NAME_PREV);
}