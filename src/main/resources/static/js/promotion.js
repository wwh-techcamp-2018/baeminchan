let carouselTimer;

document.addEventListener("DOMContentLoaded", () => {
    carouselTimer = setInterval(moveNextCarousel, 3000);
    $('.dot-btn-box').addEventListener('click', moveCarousel);
    $('.bm-icon.prev').addEventListener('click', movePrevCarousel);
    $('.bm-icon.next').addEventListener('click', moveNextCarousel);
});

function moveCarousel(e) {
    e.preventDefault();

    const currentElement = e.target;

    if (!currentElement.classList.contains("dot")) {
        return;
    }

    removeDot();
    currentElement.classList.add('on');

    const currentCarouselElement = $('.img-item.current');
    currentCarouselElement.classList.remove('current');

    const dotIndex = getElementIndex(currentElement);
    $(`.img-item:nth-child(${dotIndex})`).classList.add('current');
}

function movePrevCarousel(e) {
    if (e) {
        e.preventDefault();
        resetCarouselTimer();
    }

    const currentElement = $('.img-item.current');
    const prevElement = currentElement.previousElementSibling || $(`.img-box > .img-item:last-child`);

    currentElement.classList.remove('current');
    prevElement.classList.add('current');

    updateDotOn(prevElement);
}

function moveNextCarousel(e) {
    if (e) {
        e.preventDefault();
        resetCarouselTimer();
    }

    const currentElement = $('.img-item.current');
    const nextElement = currentElement.nextElementSibling || $(`.img-box > .img-item:first-child`);

    currentElement.classList.remove('current');
    nextElement.classList.add('current');

    updateDotOn(nextElement);
}

function removeDot() {
    const prevDotElement = $('.dot-btn-box .dot.on');
    prevDotElement.classList.remove('on');
}

function updateDotOn(element) {
    removeDot();

    const carouselIndex = getElementIndex(element);
    $(`.dot-btn-box .dot:nth-child(${carouselIndex})`).classList.add('on');
}

function getElementIndex(element) {
    return [...element.parentElement.children].indexOf(element) + 1;
}

function resetCarouselTimer() {
    carouselTimer && clearInterval(carouselTimer);
    carouselTimer = setInterval(moveNextCarousel, 3000);
}