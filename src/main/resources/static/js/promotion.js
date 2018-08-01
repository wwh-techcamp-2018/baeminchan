document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const dotBtnList = document.querySelectorAll('.dot');
    dotBtnList.forEach((btn) => { btn.addEventListener("click", dotClickHandler)});
    const slideArrowList = document.querySelectorAll(".spr-btn-arrow-main-slide");
    slideArrowList.forEach((arrow) => { arrow.addEventListener("click", arrowClickHandler)});
}

function dotClickHandler(evt) {
    evt.preventDefault();
    switchClass('on', evt.target);
    switchClass('current', $('.img-box').children[getOrder(evt.target)]);
}

function arrowClickHandler(evt) {
    evt.preventDefault();
    const arrow = evt.target;
    let changeElement;
    if (arrow.className.includes("prev")) {
        changeElement = $(".current").previousElementSibling
        console.log(changeElement);
        if (!changeElement) {
            changeElement = $(".img-box").lastElementChild;
        }
    }

    if (arrow.className.includes("next")) {
        changeElement = $(".current").nextElementSibling
        console.log(changeElement);
        if (!changeElement) {
            changeElement = $(".img-box").firstElementChild;
        }
    }
    switchClass('current', changeElement);
    switchClass('on', $('.dot-btn-box').children[getOrder(changeElement)]);
}

function getOrder(element) {
    return Array.prototype.indexOf.call(element.parentElement.children, element);
}


function switchClass(className, element) {
    $("." + className).classList.remove(className);
    element.classList.add(className);
}