const addAllEvent = () => {
    $(".bm-icon.spr-btn-arrow-main-slide.prev").addEventListener("click", (event) => {
        event.preventDefault();
        movePrevious();
    });

    $(".bm-icon.spr-btn-arrow-main-slide.next").addEventListener("click", (event) => {
        event.preventDefault();
        moveNext();
    });

    addDotBoxEvent();
};

const addDotBoxEvent = () => {
    Array.from($('#promotion-dot-box').children).forEach(child => {
        child.addEventListener('click', (event) => {
            event.preventDefault();
            moveByDot();
        });
    });
};


const moveByDot = () => {
    const currentDot = $('#promotion-dot-box .on');
    currentDot.classList.toggle("on", false);
    const prevImage = $(".current");
    prevImage.classList.toggle("current", false);

    const dotElement = event.target;
    const dotIndex = Array.from(dotElement.parentNode.children).indexOf(dotElement);
    dotElement.classList.toggle("on", true);
    const currentImage = $All(".img-item")[dotIndex];
    currentImage.classList.toggle("current", true);
};

function movePrevious() {
    const currentImage = $(".current");
    const nextImage =  currentImage.previousElementSibling || currentImage.parentNode.lastElementChild;
    nextImage.classList.toggle("current", true);
    currentImage.classList.toggle("current", false);

    const currentDot = $('#promotion-dot-box .on')
    const nextDot = currentDot.previousElementSibling || currentDot.parentNode.lastElementChild;
    currentDot.classList.toggle("on", false);
    nextDot.classList.toggle("on", true);
}

function moveNext() {
    const currentImage = $(".current");
    const nextImage =  currentImage.nextElementSibling || currentImage.parentNode.firstElementChild;
    currentImage.classList.toggle("current", false);
    nextImage.classList.toggle("current", true);

    const currentDot = $('#promotion-dot-box .on')
    const nextDot = currentDot.nextElementSibling || currentDot.parentNode.firstElementChild;
    currentDot.classList.toggle("on", false);
    nextDot.classList.toggle("on", true);
}

const registClickEvent = (target, whatToDo) => {
    target.addEventListener("click", (event) => {
        event.preventDefault();
        whatToDo();
    })
}

window.addEventListener('load', addAllEvent);