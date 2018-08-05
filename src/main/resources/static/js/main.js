document.addEventListener('DOMContentLoaded', () => {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: {'content-type': 'application/json'},
        callback: appendAnswer
    });
}, false);

const appendAnswer = (data) => {
    const outer = ({name}, subMenu) => `<li> <a href="#">${name}</a><ul class="sub-menu"> ${subMenu} </ul></li>`;
    const inner = ({name}) => `<li><a href="#">${name}</a></li>`;

    let resultHTML = data.reduce(function (p, c) {
        return p + outer(c, c.children.map((e) => inner(e)).join());
    }, "");
    $("#nav-menu").insertAdjacentHTML('afterbegin', resultHTML);
}

const addAllEvent = () => {
    registClickEvent($(".bm-icon.spr-btn-arrow-main-slide.prev"), movePrevious);
    registClickEvent($(".bm-icon.spr-btn-arrow-main-slide.next"), moveNext);
    Array.from($('#promotion-dot-box').children).forEach(child => registClickEvent(child, moveByDot));
};

const registClickEvent = (target, whatToDo) => {
    target.addEventListener("click", (event) => {
        event.preventDefault();
        whatToDo();
    });
};

const change = (target, className, callback) => {
    const current = $(target);
    const next = callback(current);
    current.classList.toggle(className, false);
    next.classList.toggle(className, true);
};

const movePrevious = () => {
    change(".current", "current", previousSibling);

    change("#promotion-dot-box .on", "on", previousSibling);
};

const moveNext = () => {
    change(".current", "current", nextSibling);

    change("#promotion-dot-box .on", "on", nextSibling);
};

const previousSibling = (element) => {
    return element.previousElementSibling || element.parentNode.lastElementChild;
};

const nextSibling = (element) => {
    return element.nextElementSibling || element.parentNode.firstElementChild;
};

const getEventTarget = () => {
    return event.target;
}

const moveByDot = () => {
    change('#promotion-dot-box .on', 'on', getEventTarget);

    const prevImage = $(".current");
    const dotIndex = Array.from(getEventTarget().parentNode.children).indexOf(getEventTarget());
    const currentImage = $All(".img-item")[dotIndex];
    prevImage.classList.toggle("current", false);
    currentImage.classList.toggle("current", true);
};

window.addEventListener('load', addAllEvent);