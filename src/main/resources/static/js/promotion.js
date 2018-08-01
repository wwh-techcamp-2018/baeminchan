document.addEventListener('DOMContentLoaded', () => {

    const imgList = Array.from(document.querySelectorAll('.img-box > li'));
    const dotList = Array.from(document.querySelectorAll('.dot-btn-box > a'));
    imgList[0].classList.add('current');
    dotList[0].classList.add('on');
    initButtons(imgList);
    initDots(dotList);
})

function prevPromotion(imgList){
    let idx = imgList.indexOf($('.img-box > li.current'));
    idx = (idx === 0) ? imgList.length-1 : idx-1;

    moveCurrent(idx);
}

function nextPromotion(imgList){
    let idx = imgList.indexOf($('.img-box > li.current'));
    idx = (idx === imgList.length-1) ? 0 : idx+1;

    moveCurrent(idx);
}

function initButtons(imgList) {
    addEvent('.spr-btn-arrow-main-slide.prev', 'click', e => {
        prevPromotion(imgList);
    });

    addEvent('.spr-btn-arrow-main-slide.next', 'click', e => {
        nextPromotion(imgList);
    });
}

function initDots(dotList) {
    dotList.forEach(dot => {
        dot.addEventListener('click', e => {
            const idx = dotList.indexOf(e.target);
            moveCurrent(idx);
        });
    })
}

function moveCurrent(idx) {
    $('.img-box > li.current').classList.remove('current');
    $('.dot-btn-box > a.on').classList.remove('on');
    $(`.img-box > li:nth-child(${idx+1})`).classList.add('current');
    $(`.dot-btn-box > a:nth-child(${idx+1})`).classList.add('on');
}

