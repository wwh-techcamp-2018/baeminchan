document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const nextBtn = $("#arrow-main-slide-next");
    nextBtn.addEventListener("click", goToNextSlide);

    const prevBtn = $("#arrow-main-slide-prev");
    prevBtn.addEventListener("click", goToPrevSlide);

    const dotBtnBox = $(".dot-btn-box");
    dotBtnBox.addEventListener("click", goToSlide);
}

function goToNextSlide(evt) {
    evt.preventDefault();
    var currentNo = parseInt($(".current-item").classList[1].split("-")[1]);
    var imgCount = $All(".img-item").length;

    $(".img-" + (currentNo+2)%imgCount).classList.add("next-item");

    $(".img-" + (currentNo+1)%imgCount).classList.remove("next-item");
    $(".img-" + (currentNo+1)%imgCount).classList.add("current-item");

    $(".img-" + (currentNo)).classList.remove("current-item");
    $(".img-" + (currentNo)).classList.add("prev-item");

    $(".img-" + (currentNo-1+imgCount)%imgCount).classList.remove("prev-item");

    $(".on-item").classList.toggle("on-item");
    $(".dot-btn-box").children[(currentNo+1)%imgCount].classList.toggle("on-item");
}

function goToPrevSlide(evt) {
    evt.preventDefault();
    var currentNo = parseInt($(".current-item").classList[1].split("-")[1]);
    var imgCount = $All(".img-item").length;

    $(".img-" + (currentNo-2+imgCount)%imgCount).classList.add("prev-item");

    $(".img-" + (currentNo-1+imgCount)%imgCount).classList.remove("prev-item");
    $(".img-" + (currentNo-1+imgCount)%imgCount).classList.add("current-item");

    $(".img-" + (currentNo)).classList.remove("current-item");
    $(".img-" + (currentNo)).classList.add("next-item");

    $(".img-" + (currentNo+1)%imgCount).classList.remove("next-item");

    $(".on-item").classList.toggle("on-item");
    $(".dot-btn-box").children[(currentNo-1+imgCount)%imgCount].classList.toggle("on-item");

}

function removePosition() {
    $(".prev-item").classList.toggle("prev-item");
    $(".current-item").classList.toggle("current-item");
    $(".next-item").classList.toggle("next-item");
}

function goToSlide(evt) {
    evt.preventDefault();
    var currentNo = Array.prototype.indexOf.call(evt.target.parentNode.children, evt.target);
    let itemList = $(".img-box").children;
    let dotList = evt.target.parentNode.children;
    let imgCount = itemList.length;

    removePosition();

    itemList[(currentNo-1+imgCount)%imgCount].classList.add("prev-item");
    itemList[currentNo].classList.add("current-item");
    itemList[(currentNo+1)%imgCount].classList.add("next-item");

    $(".on-item").classList.toggle("on-item");
    dotList[currentNo].classList.toggle("on-item");
}