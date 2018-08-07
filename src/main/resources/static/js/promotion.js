document.addEventListener('DOMContentLoaded', initEvents);

function initEvents() {
    $('.img-item:first-child').classList.add('current');
    $('.dot-btn-box > .dot:first-child').classList.add('on');

    $('.bm-icon.spr-btn-arrow-main-slide.next').addEventListener('click', navigateRight);
    $('.bm-icon.spr-btn-arrow-main-slide.prev').addEventListener('click', navigateLeft);
    $('.dot-btn-box').addEventListener('click', moveToDot);
}

function navigateRight(e) {
    e.preventDefault();

    const current = $('.img-item.current');
    const next = current.nextElementSibling || $('.img-item:first-child');

    current.classList.remove('current');
    next.classList.add('current');

    moveDotRight();
}

function navigateLeft(e) {
    e.preventDefault();

    const current = $('.img-item.current');
    const prev = current.previousElementSibling || $('.img-item:last-child');

    current.classList.remove('current');
    prev.classList.add('current');

    moveDotLeft();
}

function moveDotRight() {
    const current = $('.dot-btn-box .dot.on');
    var index = getElementIndex(current);

    current.classList.remove('on');
    (current.nextElementSibling || $('.dot-btn-box .dot:first-child')).classList.add('on');
}

function moveDotLeft() {
    const current = $('.dot-btn-box .dot.on');
    var index = getElementIndex(current);

    current.classList.remove('on');
    (current.previousElementSibling || $('.dot-btn-box .dot:last-child')).classList.add('on');
}

function moveToDot(e) {
    e.preventDefault();

    const index = getElementIndex(e.target);

    $('.dot-btn-box .dot.on').classList.remove('on');
    $(`.dot-btn-box .dot:nth-child(${index})`).classList.add('on');

    navigateToDot(index);
}

function navigateToDot(index){
    const current = $('.img-item.current');
    current.classList.remove('current');
    $(`.img-item:nth-child(${index})`).classList.add('current');
}

function getElementIndex(element){
    return [...element.parentElement.children].indexOf(element) + 1;
}